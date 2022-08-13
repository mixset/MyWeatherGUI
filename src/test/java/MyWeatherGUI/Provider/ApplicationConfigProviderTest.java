package MyWeatherGUI.Provider;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ApplicationConfigProviderTest
{
    @Test
    public void providerWillReturnPropertiesObject() throws IOException
    {
        ApplicationConfigProvider applicationConfigProvider = new ApplicationConfigProvider("test.properties");

        assertInstanceOf(Properties.class, applicationConfigProvider.getConfig());
    }

    @Test
    public void providerWilLReturnValidDataFromFile() throws IOException
    {
        ApplicationConfigProvider applicationConfigProvider = new ApplicationConfigProvider("test.properties");
        Properties properties = applicationConfigProvider.getConfig();

        assertEquals("http://api.example.com", properties.getProperty("weatherapi.domain"));
        assertEquals("1", properties.getProperty("weatherapi.version"));
        assertEquals("123456abcxed", properties.getProperty("weatherapi.key"));
    }
}
