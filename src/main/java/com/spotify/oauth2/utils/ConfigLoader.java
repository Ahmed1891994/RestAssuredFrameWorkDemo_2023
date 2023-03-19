package com.spotify.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private final Properties prop;
    private static ConfigLoader configloader;
    private ConfigLoader()
    {
        try {
            PropertiesFileHandler prophandler = new PropertiesFileHandler();
            prop = prophandler.loadProperties("/src/main/resources/" + System.getProperty("env", "dev") + ".properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigLoader getInstance()
    {
        if(configloader == null)
            configloader = new ConfigLoader();
        return configloader;
    }

    public String getBaseURI()
    {
        String result = prop.getProperty("uri.base");
        if(result == null)
            throw new RuntimeException("Property client_id is not specified in config file");
        return result;
    }
    public String getAccountBaseURI()
    {
        String result = prop.getProperty("account.uri.base");
        if(result == null)
            throw new RuntimeException("Property account.uri.base is not specified in config file");
        return result;
    }
}
