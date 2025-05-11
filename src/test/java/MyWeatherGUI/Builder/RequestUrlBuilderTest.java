package MyWeatherGUI.Builder;

import MyWeatherGUI.Provider.ApplicationConfigProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class RequestUrlBuilderTest
{
    private final RequestUrlBuilder requestUrlBuilder = new RequestUrlBuilder(
        new ApplicationConfigProvider()
    );

    @Test
    public void queryWillContainCityInUrl() throws URISyntaxException, IOException
    {
        // Given
        String city = "Warsaw";

        // When
        URI url = this.requestUrlBuilder.build(city);

        // Then
        Assertions.assertTrue(url.toString().contains(city));
    }

    @Test
    public void builderWillReturnURIObject() throws URISyntaxException, IOException
    {
        // Given
        String city = "Warsaw";

        // When
        var url = this.requestUrlBuilder.build(city);

        // Then
        String expectedUrl = "http://api.example.com/v1/current.json?key=123456abcxed&api=no&q=Warsaw";

        Assertions.assertInstanceOf(url.getClass(), url);
        Assertions.assertEquals(expectedUrl, url.toString());
    }
}