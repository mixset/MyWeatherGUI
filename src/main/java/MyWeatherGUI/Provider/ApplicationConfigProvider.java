package MyWeatherGUI.Provider;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ApplicationConfigProvider
{
    private final String filename;

    public ApplicationConfigProvider()
    {
        this.filename = "application.properties";
    }

    public ApplicationConfigProvider(String filename)
    {
        this.filename = filename;
    }

    public Properties getConfig() throws IOException
    {
        URL url = ClassLoader.getSystemResource(this.filename);

        Properties prop = new Properties();
        prop.load(url.openStream());

        return prop;
    }
}
