package org.qagile.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropReader {


    public static String getPropValue(String key) {
        Properties prop = new Properties();
        String value = null;
        try {
            prop.load(new FileInputStream("src//qagile.properties"));

            value = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
