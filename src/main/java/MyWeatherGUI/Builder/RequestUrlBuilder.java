package MyWeatherGUI.Builder;

import MyWeatherGUI.Provider.ApplicationConfigProviderInterface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class RequestUrlBuilder
{
    private final ApplicationConfigProviderInterface applicationConfigProvider;

    public RequestUrlBuilder(ApplicationConfigProviderInterface applicationConfigProvider)
    {
        this.applicationConfigProvider = applicationConfigProvider;
    }

    public URI build(String city) throws URISyntaxException, IOException
    {
        Properties config = this.applicationConfigProvider.getConfig();

        String domain = config.getProperty("weatherapi.domain");
        Integer version = Integer.valueOf(config.getProperty("weatherapi.version"));
        String apiKey = config.getProperty("weatherapi.key");

        String uri = domain + String.format("v%d", version) +
            "/current.json" +
            String.format("?key=%s", apiKey) +
            "&api=no" +
            String.format("&q=%s", city);

        return new URI(uri);
    }
}
