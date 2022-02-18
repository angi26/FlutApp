package de.idvk.flutapp;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


/**
 *
 * @author annika
 */
public class PdfErstellen {
    
    String pathImage = PropertiesMain.readValue("pathImage");
    String pdfDatei = PropertiesMain.readValue("pdfDatei");
    String betreff = PropertiesMain.readValue("betreff");
    String inhalt = PropertiesMain.readValue("inhalt");
    String fusszeile = PropertiesMain.readValue("fusszeile");
    
    //Dokument Erstellung und Seite hinzufügen
    private static final PDDocument document = new PDDocument();
    private static final PDPage page = new PDPage();
    private static PDPageContentStream contentStream;
    
    //Variablen für Pdf anlegen (in OberflaecheFlutApp befüllt)
    public static String rechnungsnr;
    public static String betrag;
    public static String datum;
    public static String name;

    public PdfErstellen() throws IOException {
        document.addPage(page);
        contentStream = new PDPageContentStream(document, page);
        

        //Bilder definieren
        PDImageXObject pdImage = PDImageXObject.createFromFile(pathImage, document);

        //Schriftarten
        PDFont font = PDType1Font.HELVETICA_BOLD;
        PDFont font2 = PDType1Font.HELVETICA;

        //Inhalt pdf  
        //Betreff
        hinzufuegen(betreff, font, 12, 50, 575);

        //Kopfzeile
        contentStream.drawImage(pdImage, 450, 630);

        hinzufuegen("Rechnungsnr: ", font2, 12, 50, 525);
        hinzufuegen(rechnungsnr, font, 12, 130, 525);
        hinzufuegen("Name: ", font2, 12, 300, 525);
        hinzufuegen(name, font, 12, 345, 525);
        hinzufuegen("Betrag: ", font2, 12, 50, 500);
        hinzufuegen(betrag, font, 12, 130, 500);
        hinzufuegen("Datum: ", font2, 12, 300, 500);
        hinzufuegen(datum, font, 12, 345, 500);

        //Inhalt
        hinzufuegen(inhalt, font2, 12, 50, 375);

        //Fußzeile
        hinzufuegen(fusszeile, font2, 12, 50, 150);

        //Content Stream schließen
        contentStream.close();

        //Abspeichern
        document.save(pdfDatei);
        document.close();
    }

    private static void hinzufuegen(String text, PDFont font, int size, int x, int y) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, size);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }
    
}
