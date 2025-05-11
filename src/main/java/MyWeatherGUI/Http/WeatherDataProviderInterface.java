package MyWeatherGUI.Http;

import MyWeatherGUI.DTO.Request;
import MyWeatherGUI.DTO.Weather;
import MyWeatherGUI.Exception.WeatherNotFoundException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface WeatherDataProviderInterface
{
    public Weather execute(Request request) throws IOException, InterruptedException, ParseException, URISyntaxException, WeatherNotFoundException;
}
