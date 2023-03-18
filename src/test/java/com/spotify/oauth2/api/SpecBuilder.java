package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.PropertiesFileHandler;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;
import java.util.Properties;

import static com.spotify.oauth2.api.Route.*;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec()
    {
        return new RequestSpecBuilder().
                setBaseUri(ConfigLoader.getInstance().getBaseURI()).
                setBasePath(BASEVERSION).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getAccountRequestSpec()
    {
        return new RequestSpecBuilder().
                setBaseUri(ConfigLoader.getInstance().getAccountBaseURI()).
                setBasePath(API+TOKEN).
                setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec()
    {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
