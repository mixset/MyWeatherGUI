package MyWeatherGUI.Provider;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ApplicationConfigProvider implements ApplicationConfigProviderInterface
{
    private final String configurationFile;

    public ApplicationConfigProvider()
    {
        this.configurationFile = "application.properties";
    }

    public Properties getConfig() throws IOException
    {
        URL url = ClassLoader.getSystemResource(this.configurationFile);

        Properties prop = new Properties();
        prop.load(url.openStream());

        return prop;
    }
}
