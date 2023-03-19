package com.spotify.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class DataLoader {
    private final Properties prop;
    private static DataLoader dataloader;
    private DataLoader()
    {
        try {
            PropertiesFileHandler prophandler = new PropertiesFileHandler();
            prop = prophandler.loadProperties("/src/main/resources/data.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataLoader getInstance()
    {
        if(dataloader == null)
            dataloader = new DataLoader();
        return dataloader;
    }

    public String getPlayListId()
    {
        String result = prop.getProperty("PLAYLISTID");
        if(result == null)
            throw new RuntimeException("Property PLAYLISTID is not specified in config file");
        return result;
    }
    public String getUpdatedPlayListId()
    {
        String result = prop.getProperty("UPDATEDPLAYLISTID");
        if(result == null)
            throw new RuntimeException("Property UPDATEDPLAYLISTID is not specified in config file");
        return result;
    }
}
