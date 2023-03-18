package com.spotify.oauth2.utils.Fakers;

import com.github.javafaker.Faker;

public class FakerPlayList {
    public static String getName() {
        Faker faker = new Faker();
        return "PlayList " + faker.regexify("[A-Za-z0-9 ,_-]{10}");
    }

    public static String getDescription() {
        Faker faker = new Faker();
        return "Description " + faker.regexify("[A-Za-z0-9 ,_-@#$%^&*]{50}");
    }
}
