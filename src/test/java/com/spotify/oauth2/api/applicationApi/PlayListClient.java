package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojos.playlistpojo.PlayListRoot;
import com.spotify.oauth2.utils.PlayListConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.GetToken;

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
