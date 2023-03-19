package com.spotify.oauth2.utils.manager;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.utils.manager.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response Post(Object requestbody , String accesstoken , String Path)
    {
        return
            given(getRequestSpec()).
                    auth().oauth2(accesstoken).
                    body(requestbody).
            when().post(Path).
            then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response PostAccount(HashMap<String,String> parameters)
    {
        return
                given(getAccountRequestSpec()).
                        formParams(parameters).
                        log().all().
                        when().post().
                        then().spec(getResponseSpec()).
                        extract().
                        response();
    }

    public static Response Get(String accesstoken , String Path)
    {
        return
            given(getRequestSpec()).
                auth().oauth2(accesstoken).
            when().get(Path).
            then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response Update(Object requestbody,String accesstoken , String Path)
    {
        return
            given(getRequestSpec()).
                auth().oauth2(accesstoken).
                body(requestbody).
            when().put(Path).
            then().spec(getResponseSpec()).
                extract().
                response();
    }
}
