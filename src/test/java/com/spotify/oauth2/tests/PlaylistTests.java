package com.spotify.oauth2.tests;


import com.spotify.oauth2.api.clients.PlayListClient;
import com.spotify.oauth2.pojos.playlisterror.PlayListError;
import com.spotify.oauth2.pojos.playlistpojo.PlayListRoot;
import com.spotify.oauth2.utils.configurations.DataLoader;
import com.spotify.oauth2.utils.Fakers.FakerPlayList;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Playlist Scenae")
@Feature("Playliest Tests")
public class PlaylistTests {

    @Step
    public PlayListRoot PlaylistBuilder(String name,String describtion,boolean ispublic)
    {
        return PlayListRoot.builder().
                name(name).
                description(describtion).
                _public(ispublic).
                build();
    }
    @Story("Handling Playlist Endpoint")
    @Description("Create a new Playlist")
    @Test
    public void CreatePlaylist()
    {
        PlayListRoot playlistrequest =PlaylistBuilder(FakerPlayList.getName(),FakerPlayList.getDescription(),false);
        Response response = PlayListClient.Post(playlistrequest);
        assertThat(response.statusCode(),equalTo(201));

        PlayListRoot responseplaylist = response.as(PlayListRoot.class);

        assertThat(responseplaylist.getName(),equalTo(playlistrequest.getName()));
        assertThat(responseplaylist.getDescription(),equalTo(playlistrequest.getDescription()));
        assertThat(responseplaylist.get_public(),equalTo(playlistrequest.get_public()));

    }
    @Story("Handling Playlist Endpoint")
    @Description("Get a specific Playlist")
    @Test
    public void GetPlaylist()
    {
        Response response = PlayListClient.Get(DataLoader.getInstance().getPlayListId());
        assertThat(response.statusCode(),equalTo(200));

        PlayListRoot responseplaylist = response.as(PlayListRoot.class);

        assertThat(responseplaylist.getName(),equalTo("Updated Playlist Name2"));
        assertThat(responseplaylist.getDescription(),equalTo("Updated playlist description2"));
        assertThat(responseplaylist.get_public(),equalTo(false));
    }
    @Story("Handling Playlist Endpoint")
    @Description("Updating a specific Playlist")
    @Test
    public void UpdatePlaylist()
    {
        PlayListRoot playlistrequest =PlaylistBuilder("Updated " + FakerPlayList.getName(),"Updated " +  FakerPlayList.getDescription(),false);
        Response response = PlayListClient.Update(playlistrequest,DataLoader.getInstance().getUpdatedPlayListId());
        assertThat(response.statusCode(),equalTo(200));
    }
    @Story("Handling Playlist Endpoint")
    @Description("Creating Playlist Using EmptyName it should return 400 as status code")
    @Test
    public void VerifyStatusCode400WhenCreatingPlaylistUsingEmptyName()
    {
        PlayListRoot playlistrequest =PlaylistBuilder(null,FakerPlayList.getDescription(),true);
        Response response = PlayListClient.Post(playlistrequest);
        assertThat(response.statusCode(),equalTo(400));

        PlayListError responseplaylist = response.as(PlayListError.class);
        assertThat(responseplaylist.getErrormessage().getMessage(),equalTo("Missing required field: name"));
    }
    @Story("Handling Playlist Endpoint")
    @Description("Creating Playlist Using Wrong Access Token it should return 401 as status code")
    @Test
    public void VerifyStatusCode401WhenCreatingPlaylistUsingWrongAccessToken()
    {
        PlayListRoot playlistrequest =PlaylistBuilder(FakerPlayList.getName(),FakerPlayList.getDescription(),false);
        Response response = PlayListClient.Post(playlistrequest , "12345");
        assertThat(response.statusCode(),equalTo(401));

        PlayListError responseplaylist = response.as(PlayListError.class);
        assertThat(responseplaylist.getErrormessage().getMessage(),equalTo("Invalid access token"));
    }
}
