package MyWeatherGUI.Builder;

import java.net.URI;
import java.net.URISyntaxException;

public class RequestUrlBuilder
{
    private static final String domain = "http://api.weatherapi.com/";
    private static final int API_VERSION = 1;
    private static final String API_KEY = "13aea8d1591843dd930151938221906";

    public URI build(String city) throws URISyntaxException
    {
        String uri = domain + String.format("v%d", API_VERSION) +
            "/current.json" +
            String.format("?key=%s", API_KEY) +
            "&api=no" +
            String.format("&q=%s", city);

        return new URI(uri);
    }
}
