package MyWeatherGUI.Provider;

import java.io.IOException;
import java.util.Properties;

public interface ApplicationConfigProviderInterface
{
    Properties getConfig() throws IOException;
}
