package de.idvk.flutapp;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author annika
 */
public class FlutEmail {
    
    public static void email(String empfaenger) throws Exception {
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String user = "***"; //E-Mail Adresse, von der die PDFs verschickt werden
        String password = "***"; //Passwort zu obengenannter E-Mail Adresse
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        
        Message message = prepareMessage(session, user, empfaenger);
        
        System.out.println("E-Mail wird versendet");        
        Transport.send(message);
        System.out.println("Ihre E-Mail wurde erfolgreich versendet!");
    }
    
    private static Message prepareMessage(Session session, String user, String empfaenger) throws MessagingException, IOException {
        
        PdfErstellen pdf = new PdfErstellen();
        
        String filename = "/home/annika/NetBeansProjects/FlutApp/Rechnung.pdf";        

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
            message.setSubject("Spendenquittung Flutkatastrophe");
            
            MimeMultipart mimeMultipart = new MimeMultipart();
 
            MimeBodyPart text = new MimeBodyPart();
            text.setText("""
                         Sehr geehrte Damen und Herren, 
                         
                         im Anhang Ihre Spendenquittung f\u00fcr Ihre Spende an die Flutkatastrophenopfer.
                         
                         Vielen Dank f\u00fcr Ihre Spende!
                         """);
            text.setDisposition(MimeBodyPart.INLINE);

            File file = new File(filename);
            MimeBodyPart attachement = new MimeBodyPart();
            attachement.setDataHandler(new DataHandler(new FileDataSource(file)));
            attachement.setFileName(filename);
            attachement.setDisposition(MimeBodyPart.ATTACHMENT);
            mimeMultipart.addBodyPart(text);
            mimeMultipart.addBodyPart(attachement);

            message.setContent(mimeMultipart);
            
            return message;
            
        } catch (AddressException ex) {
            Logger.getLogger(FlutEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
