package com.spotify.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class PlayListConfigLoader {
    private final Properties prop;
    private static PlayListConfigLoader configloader;
    private PlayListConfigLoader()
    {
        try {
            PropertiesFileHandler prophandler = new PropertiesFileHandler();
            prop = prophandler.loadProperties("/src/test/resources/configuration.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PlayListConfigLoader getInstance()
    {
        if(configloader == null)
            configloader = new PlayListConfigLoader();
        return configloader;
    }

    public String getClientId()
    {
        String result = prop.getProperty("client_id");
        if(result == null)
            throw new RuntimeException("Property client_id is not specified in config file");
        return result;
    }
    public String getClientSecret()
    {
        String result = prop.getProperty("client_secret");
        if(result == null)
            throw new RuntimeException("Property client_secret is not specified in config file");
        return result;
    }
    public String getRefreshToken()
    {
        String result = prop.getProperty("refresh_token");
        if(result == null)
            throw new RuntimeException("Property refresh_token is not specified in config file");
        return result;
    }
    public String getGrantType()
    {
        String result = prop.getProperty("grant_type");
        if(result == null)
            throw new RuntimeException("Property grant_type is not specified in config file");
        return result;
    }
    public String getUserId()
    {
        String result = prop.getProperty("userid");
        if(result == null)
            throw new RuntimeException("Property userid is not specified in config file");
        return result;
    }
}
