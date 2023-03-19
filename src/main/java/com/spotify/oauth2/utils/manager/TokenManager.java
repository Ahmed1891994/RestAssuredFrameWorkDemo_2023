package com.spotify.oauth2.utils.manager;

import com.spotify.oauth2.utils.configurations.PlayListConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String accesstoken;
    private static Instant expirytime;
    public synchronized static String GetToken()
    {
        try
        {
            if(accesstoken == null || Instant.now().isAfter(expirytime))
            {
                Response response = RenewToken();
                accesstoken = response.path("access_token");
                int timetoexpire = response.path("expires_in");
                expirytime = Instant.now().plusSeconds(timetoexpire-60);
            }
            else;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Abort !! can't get token");
        }
        return accesstoken;
    }
    public static Response RenewToken() {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("grant_type", PlayListConfigLoader.getInstance().getGrantType());
        parameters.put("refresh_token", PlayListConfigLoader.getInstance().getRefreshToken());
        parameters.put("client_id", PlayListConfigLoader.getInstance().getClientId());
        parameters.put("client_secret", PlayListConfigLoader.getInstance().getClientSecret());

        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(parameters).
                log().all().
            when().post("/api/token").
            then().spec(SpecBuilder.getResponseSpec()).
                extract().
                response();

        if(response.statusCode() != 200)
        {
            throw new RuntimeException("Abort !! can't renew token");
        }

        return response;
    }
}
