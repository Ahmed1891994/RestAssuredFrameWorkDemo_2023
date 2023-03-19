package com.spotify.oauth2.api.clients;

import com.spotify.oauth2.api.manager.RestResource;
import com.spotify.oauth2.api.pojos.playlistpojo.PlayListRoot;
import com.spotify.oauth2.utils.configurations.PlayListConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.routes.PlayListRoute.PLAYLISTS;
import static com.spotify.oauth2.api.routes.PlayListRoute.USERS;
import static com.spotify.oauth2.api.manager.TokenManager.GetToken;

public class PlayListClient {
    @Step("posting")
    public static Response Post(PlayListRoot requestbody)
    {
        return RestResource.Post(requestbody,GetToken(),USERS+ PlayListConfigLoader.getInstance().getUserId() +PLAYLISTS);
    }
    @Step("posting using token {1}")
    public static Response Post(PlayListRoot requestbody,String token)
    {
        return RestResource.Post(requestbody,token,USERS+ PlayListConfigLoader.getInstance().getUserId() +PLAYLISTS);
    }
    @Step("Getting playlistid {0}")
    public static Response Get(String playlistid)
    {
        return RestResource.Get(GetToken(),PLAYLISTS + playlistid);
    }
    @Step("Updating playlistid {1}")
    public static Response Update(PlayListRoot requestbody,String playlistid)
    {
        return RestResource.Update(requestbody,GetToken(),PLAYLISTS+playlistid);
    }
}
