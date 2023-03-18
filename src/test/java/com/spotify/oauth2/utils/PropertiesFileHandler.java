package com.spotify.oauth2.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileHandler {
    //load properties file from folder
    public Properties loadProperties(String path) throws IOException
    {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+path);
        prop.load(fis);
        return prop;
    }
}
