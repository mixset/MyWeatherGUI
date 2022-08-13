package MyWeatherGUI.Builder;

import MyWeatherGUI.Provider.ApplicationConfigProvider;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class RequestUrlBuilder
{
    private final ApplicationConfigProvider applicationConfigProvider;

    public RequestUrlBuilder()
    {
        this.applicationConfigProvider = new ApplicationConfigProvider();
    }

    public URI build(String city) throws URISyntaxException, IOException
    {
        String domain = this.applicationConfigProvider.getConfig().getProperty("weatherapi.domain");
        Integer version = Integer.valueOf(this.applicationConfigProvider.getConfig().getProperty("weatherapi.version"));
        String apiKey = this.applicationConfigProvider.getConfig().getProperty("weatherapi.key");

        String uri = domain + String.format("v%d", version) +
            "/current.json" +
            String.format("?key=%s", apiKey) +
            "&api=no" +
            String.format("&q=%s", city);

        return new URI(uri);
    }
}
