package Builder;

import java.net.URI;
import java.net.URISyntaxException;

public class RequestUrlBuilder
{
    private static final String domain = "http://api.weatherapi.com/";
    private static final int API_VERSION = 1;
    private static final String API_KEY = "13aea8d1591843dd930151938221906";

    public URI build(String city) throws URISyntaxException
    {
        StringBuilder uri = new StringBuilder(domain);
        uri.append(String.format("v%d", API_VERSION));
        uri.append("/current.json");
        uri.append(String.format("?key=%s", API_KEY));
        uri.append("&api=no");
        uri.append(String.format("&q=%s", city));

        return new URI(uri.toString());
    }
}
