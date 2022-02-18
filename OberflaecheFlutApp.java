package de.idvk.flutapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author annika
 */
public class OberflaecheFlutApp {
    
    private TextField rechnungsnrTF;
    private TextField datumTF;
    private TextField nachnameTF;
    private TextField vornameTF;
    private TextField strasseTF;
    private TextField hausnrTF;
    private TextField plzTF;
    private TextField ortTF;
    private TextField emailTF;
    private TextField betragTF;
    

    private Button absendenBtn;
    private Button anzeigenBtn;
    private Button hinzufuegenBtn;
    private Button speichernBtn;

    private GridPane flutspendenGrid;

    private AnchorPane anchorPane;

    private final ObservableList<Flutspenden> flutspendenListe;

    private TableView<Flutspenden> flutspendenTab;
    
    private TableColumn<Flutspenden, String> rechnungsnrCol;
    private TableColumn<Flutspenden, String> datumCol;
    private TableColumn<Flutspenden, String> nachnameCol;
    private TableColumn<Flutspenden, String> vornameCol;
    private TableColumn<Flutspenden, String> strasseCol;
    private TableColumn<Flutspenden, String> hausnrCol;
    private TableColumn<Flutspenden, String> plzCol;
    private TableColumn<Flutspenden, String> ortCol;
    private TableColumn<Flutspenden, String> emailCol;
    private TableColumn<Flutspenden, String> betragCol;
    

    public OberflaecheFlutApp() {

        flutspendenListe = FXCollections.observableArrayList();

        initTab();
        initGui();
        initGrid();
        initAnchorPane();
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public GridPane getFlutspendenGrid() {
        return flutspendenGrid;
    }

    private void initAnchorPane() {
        anchorPane = new AnchorPane();
        AnchorPane.setBottomAnchor(flutspendenTab, 10.0);
        AnchorPane.setLeftAnchor(flutspendenTab, 10.0);
        AnchorPane.setRightAnchor(flutspendenTab, 10.0);
        AnchorPane.setTopAnchor(flutspendenTab, 150.0);
        anchorPane.getChildren().addAll(flutspendenGrid, flutspendenTab);
    }

    private void initGui() {
        rechnungsnrTF = new TextField();
        datumTF = new TextField();
        nachnameTF = new TextField();
        vornameTF = new TextField();
        strasseTF = new TextField();
        hausnrTF = new TextField();
        plzTF = new TextField();
        ortTF = new TextField();
        emailTF = new TextField();
        betragTF = new TextField();
        
        absendenBtn = new Button("absenden");
        absendenBtnEreignis();

        anzeigenBtn = new Button("anzeigen");
        anzeigenBtnEreignis();
        
        hinzufuegenBtn = new Button("hinzufügen");
        hinzufuegenBtnEreignis();
        
        speichernBtn = new Button("speichern");
        speichernBtnEreignis();
    }

    private void initGrid() {
        flutspendenGrid = new GridPane();
        
        flutspendenGrid.add(new Label("Nachname: "), 0, 0);        
        flutspendenGrid.add(nachnameTF, 1, 0);
        
        flutspendenGrid.add(new Label("Vorname: "), 0, 1);
        flutspendenGrid.add(vornameTF, 1, 1);
        
        flutspendenGrid.add(new Label("Rechnungsnr: "), 0, 2);        
        flutspendenGrid.add(rechnungsnrTF, 1, 2);
        
        flutspendenGrid.add(new Label("Datum: "), 0, 3);
        flutspendenGrid.add(datumTF, 1, 3);
        
        flutspendenGrid.add(new Label("Betrag: "), 0, 4);
        flutspendenGrid.add(betragTF, 1, 4);
        
        flutspendenGrid.add(new Label("Straße: "), 2, 0);
        flutspendenGrid.add(strasseTF, 3, 0);
        
        flutspendenGrid.add(new Label("Hausnr: "), 2, 1);
        flutspendenGrid.add(hausnrTF, 3, 1);
        
        flutspendenGrid.add(new Label("Plz: "), 2, 2);
        flutspendenGrid.add(plzTF, 3, 2);
        
        flutspendenGrid.add(new Label("Ort: "), 2, 3);
        flutspendenGrid.add(ortTF, 3, 3);
        
        flutspendenGrid.add(new Label("E-Mail: "), 2, 4);
        flutspendenGrid.add(emailTF, 3, 4);
        
        

        flutspendenGrid.add(anzeigenBtn, 4, 0);
        GridPane.setColumnSpan(anzeigenBtn, 2);
        GridPane.setHalignment(anzeigenBtn, HPos.CENTER);

        flutspendenGrid.add(absendenBtn, 4, 1);
        GridPane.setColumnSpan(absendenBtn, 2);
        GridPane.setHalignment(absendenBtn, HPos.CENTER);
        
        flutspendenGrid.add(hinzufuegenBtn, 4, 2);
        GridPane.setColumnSpan(hinzufuegenBtn, 2);
        GridPane.setHalignment(hinzufuegenBtn, HPos.CENTER);
        
        flutspendenGrid.add(speichernBtn, 4, 3);
        GridPane.setColumnSpan(speichernBtn, 2);
        GridPane.setHalignment(speichernBtn, HPos.CENTER);

        flutspendenGrid.add(flutspendenTab, 0, 7);
        GridPane.setColumnSpan(flutspendenTab, 3);
        GridPane.setHalignment(flutspendenTab, HPos.CENTER);

        flutspendenGrid.setHgap(3);
        flutspendenGrid.setVgap(3);
        flutspendenGrid.setPadding(new Insets(10, 10, 10, 10));
    }

    private void initTab() {
        flutspendenTab = new TableView<>();

        //Tabelle editierbar machen
        flutspendenTab.setEditable(true);

        initRechnungsnrCol();
        initDatumCol();
        initNachnameCol();
        initVornameCol();
        initStrasseCol();
        initHausnrCol();
        initPlzCol();
        initOrtCol();
        initEmailCol();
        initBetragCol();

        flutspendenTab.getColumns().addAll(rechnungsnrCol, nachnameCol, vornameCol, strasseCol, hausnrCol, plzCol, ortCol, emailCol, betragCol, datumCol);
        flutspendenTab.setItems(flutspendenListe);
    }
    
    private void initRechnungsnrCol() {
        rechnungsnrCol = new TableColumn<>();
        rechnungsnrCol.setText("Nr");
        rechnungsnrCol.setCellValueFactory(new PropertyValueFactory<>("rechnungsnr"));

        //um Einträge bei Doppelklick bearbeiten zu können (mit Enter bestätigen)
        rechnungsnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
        rechnungsnrCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setRechnungsnr(t.getNewValue());
        });
        
        rechnungsnrCol.setMinWidth(10);
        rechnungsnrCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(20));
    }

    private void initNachnameCol() {
        nachnameCol = new TableColumn<>();
        nachnameCol.setText("Nachname");
        nachnameCol.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        nachnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nachnameCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNachname(t.getNewValue());
        });

        nachnameCol.setMinWidth(50);
        nachnameCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(10));
    }

    private void initVornameCol() {
        vornameCol = new TableColumn<>();
        vornameCol.setText("Vorname");
        vornameCol.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        vornameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        vornameCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setVorname(t.getNewValue());
        });

        vornameCol.setMinWidth(50);
        vornameCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(10));
    }

    private void initStrasseCol() {
        strasseCol = new TableColumn<>();
        strasseCol.setText("Straße");
        strasseCol.setCellValueFactory(new PropertyValueFactory<>("strasse"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        strasseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        strasseCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStrasse(t.getNewValue());
        });

        strasseCol.setMinWidth(50);
        strasseCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(5));
    }

    private void initHausnrCol() {
        hausnrCol = new TableColumn<>();
        hausnrCol.setText("Hausnr.");
        hausnrCol.setCellValueFactory(new PropertyValueFactory<>("hausnr"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        hausnrCol.setCellFactory(TextFieldTableCell.forTableColumn());
        hausnrCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setHausnr(t.getNewValue());
        });

        hausnrCol.setMinWidth(20);
        hausnrCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(20));
    }

    private void initPlzCol() {
        plzCol = new TableColumn<>();
        plzCol.setText("PLZ");
        plzCol.setCellValueFactory(new PropertyValueFactory<>("plz"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        plzCol.setCellFactory(TextFieldTableCell.forTableColumn());
        plzCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPlz(t.getNewValue());
        });

        plzCol.setMinWidth(30);
        plzCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(10));
    }

    private void initOrtCol() {
        ortCol = new TableColumn<>();
        ortCol.setText("Ort");
        ortCol.setCellValueFactory(new PropertyValueFactory<>("ort"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        ortCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ortCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOrt(t.getNewValue());
        });

        ortCol.setMinWidth(40);
        ortCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(10));
    }

    private void initEmailCol() {
        emailCol = new TableColumn<>();
        emailCol.setText("E-Mail");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
        });

        emailCol.setMinWidth(50);
        emailCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(5));
    }

    private void initBetragCol() {
        betragCol = new TableColumn<>();
        betragCol.setText("Betrag");
        betragCol.setCellValueFactory(new PropertyValueFactory<>("betrag"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        betragCol.setCellFactory(TextFieldTableCell.forTableColumn());
        betragCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setBetrag(t.getNewValue());
        });

        betragCol.setMinWidth(30);
        betragCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(10));
    }
    
    private void initDatumCol() {
        datumCol = new TableColumn<>();
        datumCol.setText("Datum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        
        //um Einträge bei Doppelklick bearbeiten zu können
        datumCol.setCellFactory(TextFieldTableCell.forTableColumn());
        datumCol.setOnEditCommit((CellEditEvent<Flutspenden, String> t) -> {
            ((Flutspenden) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDatum(t.getNewValue());
        });

        datumCol.setMinWidth(30);
        datumCol.prefWidthProperty().bind(flutspendenTab.widthProperty().divide(10));
    }
        
    
    //generiert pdf mit Daten aus den Textfields und sendet dieses per E-Mail an Empfänger
    private void absendenBtnEreignis() {
        absendenBtn.setOnAction(e -> {
            
            try {
                //Pdf mit Daten aus selektierter Reihe befüllen
                Flutspenden selectedFlutspende = flutspendenTab.getSelectionModel().getSelectedItem();
                
                PdfErstellen.rechnungsnr = selectedFlutspende.getRechnungsnr();
                PdfErstellen.name = selectedFlutspende.getVorname() + " " + selectedFlutspende.getNachname();
                PdfErstellen.betrag = selectedFlutspende.getBetrag();
                PdfErstellen.datum = selectedFlutspende.getDatum();
             
                FlutEmail fe = new FlutEmail();
                fe.email(selectedFlutspende.getEmail()); //schickt die E-Mail an die Adresse aus der ausgeählten Zeile
            } catch (Exception ex) {
                Logger.getLogger(OberflaecheFlutApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    //zeigt die Daten aus der Datei in der Tabelle an
    private void anzeigenBtnEreignis() {
        anzeigenBtn.setOnAction(e -> {
            flutspendenTab.getItems().clear();
            fileReading();
        });
    }
    
    //speichert die neuen Daten in der Tabelle
    private void hinzufuegenBtnEreignis() {
        hinzufuegenBtn.setOnAction(e -> {
            
            tabelleBefuellenTF();
            felderLeeren();
        });
    }

    //speichert die geänderten und neuen Daten in der Datei
    private void speichernBtnEreignis() {
        speichernBtn.setOnAction(e -> {
            fileWriting();

        });
    }
    
    public void fileReading() {

        FileReader fr = null;
        try {
            String pathListe = PropertiesMain.readValue("pathListe");
            fr = new FileReader(pathListe);
            try (BufferedReader reader = new BufferedReader(fr)) {
                String textFromFile;
                while ((textFromFile = reader.readLine()) != null) {
                    String[] parts = textFromFile.split("#");

                    //Daten aus Datei splitten
                    //letzte Daten in Textfields
                    String rechnungsnr = parts[0];
                    String nachname = parts[1];
                    String vorname = parts[2];
                    String strasse = parts[3];
                    String hausnr = parts[4];
                    String plz = parts[5];
                    String ort = parts[6];
                    String email = parts[7];
                    String betrag = parts[8];
                    String datum = parts[9];

                    //gesplittete Daten in Tabelle einfügen
                    Flutspenden inhalt = new Flutspenden();

                    inhalt.setRechnungsnr(rechnungsnr);
                    inhalt.setNachname(nachname);
                    inhalt.setVorname(vorname);
                    inhalt.setStrasse(strasse);
                    inhalt.setHausnr(hausnr);
                    inhalt.setPlz(plz);
                    inhalt.setOrt(ort);
                    inhalt.setEmail(email);
                    inhalt.setBetrag(betrag);
                    inhalt.setDatum(datum);

                    flutspendenListe.add(inhalt);
                }
            } catch (IOException ex) {
                Logger.getLogger(OberflaecheFlutApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OberflaecheFlutApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(OberflaecheFlutApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fileWriting() {
        
        FileWriter fr = null;
        BufferedWriter writer = null;
        
        String pathListe = PropertiesMain.readValue("pathListe");
            
        try {
            fr = new FileWriter(pathListe);
            writer = new BufferedWriter(fr);
            ObservableList<Flutspenden> flutspendenListe = flutspendenTab.getItems();
            for (int i = 0; i < flutspendenListe.size(); i++) {
                Flutspenden x = flutspendenListe.get(i);
                String rechnungsnr = x.getRechnungsnr();
                String datum = x.getDatum();
                String vorname = x.getVorname();
                String nachname = x.getNachname();
                String strasse = x.getStrasse();
                String hausnr = x.getHausnr();
                String plz = x.getPlz();
                String ort = x.getOrt();
                String email = x.getEmail();
                String betrag = x.getBetrag();

                String textToWrite = rechnungsnr + "#" + nachname + "#" + vorname + "#" + strasse + "#" + hausnr + "#"
                        + plz + "#" + ort + "#" + email + "#" + betrag + "#" + datum + System.getProperty("line.separator");

                writer.write(textToWrite);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(OberflaecheFlutApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(OberflaecheFlutApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void felderLeeren() {
        
        rechnungsnrTF.clear();
        nachnameTF.clear();
        vornameTF.clear();
        strasseTF.clear();
        hausnrTF.clear();
        plzTF.clear();
        ortTF.clear();
        emailTF.clear();
        betragTF.clear();
        datumTF.clear();
    }
    
    public void tabelleBefuellenTF() {
        
        Flutspenden neuerInhalt = new Flutspenden();

        neuerInhalt.setRechnungsnr(rechnungsnrTF.getText());
        neuerInhalt.setNachname(nachnameTF.getText());
        neuerInhalt.setVorname(vornameTF.getText());
        neuerInhalt.setStrasse(strasseTF.getText());
        neuerInhalt.setHausnr(hausnrTF.getText());
        neuerInhalt.setPlz(plzTF.getText());
        neuerInhalt.setOrt(ortTF.getText());
        neuerInhalt.setEmail(emailTF.getText());
        neuerInhalt.setBetrag(betragTF.getText());
        neuerInhalt.setDatum(datumTF.getText());
        
        flutspendenListe.add(neuerInhalt);
    }

}
