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
        // Given

        // When
        ApplicationConfigProvider applicationConfigProvider = new ApplicationConfigProvider();

        // Then
        assertInstanceOf(Properties.class, applicationConfigProvider.getConfig());
    }

    @Test
    public void providerWillReturnValidDataFromFile() throws IOException
    {
        // Given

        // When
        ApplicationConfigProvider applicationConfigProvider = new ApplicationConfigProvider();
        Properties properties = applicationConfigProvider.getConfig();

        // Then
        assertEquals("http://api.example.com/", properties.getProperty("weatherapi.domain"));
        assertEquals("1", properties.getProperty("weatherapi.version"));
        assertEquals("123456abcxed", properties.getProperty("weatherapi.key"));
    }
}
