package de.idvk.flutapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annika
 */
public class PropertiesMain {
    
    public static String readValue(String key) {

        String pathProperties = "flutApp.properties";
        File file = new File(pathProperties);
        InputStream inputStream;
        
        String value = null;
        try {
            inputStream = new FileInputStream(pathProperties);
            Properties properties = new Properties();
            properties.load(inputStream);
            value = (String) properties.get(key);
        } catch (IOException e) {
            Logger.getLogger(PropertiesMain.class.getName()).log(Level.SEVERE, "Datei nicht gefunden", e);
        }
        return value;
    }
    
}

