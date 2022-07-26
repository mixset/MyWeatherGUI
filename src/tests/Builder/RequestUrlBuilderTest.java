package tests.Builder;

import main.Builder.RequestUrlBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

class RequestUrlBuilderTest
{
    private final RequestUrlBuilder requestUrlBuilder = new RequestUrlBuilder();

    @Test
    public void queryWillContainCityInUrl() throws URISyntaxException
    {
        String city = "Warsaw";

        URI url = this.requestUrlBuilder.build(city);

        Assertions.assertTrue(url.toString().contains(city));
    }

    @Test
    public void builderWillReturnURIObject() throws URISyntaxException
    {
        String city = "Warsaw";

        var url = this.requestUrlBuilder.build(city);

        Assertions.assertInstanceOf(url.getClass(), url);
    }
}