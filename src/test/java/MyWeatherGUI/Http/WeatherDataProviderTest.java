package MyWeatherGUI.Http;

import MyWeatherGUI.DTO.Request;
import MyWeatherGUI.Exception.WeatherNotFoundException;
import MyWeatherGUI.Builder.RequestUrlBuilder;
import MyWeatherGUI.DTO.Weather;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class WeatherDataProviderTest
{
    @Mock
    private RequestUrlBuilder requestUrlBuilder;

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private WeatherDataProvider weatherDataProvider;

    @Test
    public void executeWillThrowExceptionForNotFoundCity() throws URISyntaxException, IOException, InterruptedException
    {
        HttpResponse<Object> response = new HttpResponse<>() {
            @Override
            public int statusCode() {
                return 400;
            }

            @Override
            public HttpRequest request() {
                return null;
            }

            @Override
            public Optional<HttpResponse<Object>> previousResponse() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }

            @Override
            public String body() {
                return null;
            }

            @Override
            public Optional<SSLSession> sslSession() {
                return Optional.empty();
            }

            @Override
            public URI uri() {
                return null;
            }

            @Override
            public HttpClient.Version version() {
                return null;
            }
        };

        Mockito.when(requestUrlBuilder.build("Example")).thenReturn(new URI("http://example.com/some/url/Example"));
        Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

        Assertions.assertThrows(WeatherNotFoundException.class, () -> weatherDataProvider.execute(new Request("Example")));
    }

    @Test
    public void executeWillReturnWeatherObject() throws URISyntaxException, IOException, InterruptedException, WeatherNotFoundException, ParseException {
        HttpResponse<Object> response = new HttpResponse<>() {
            @Override
            public int statusCode() {
                return 200;
            }

            @Override
            public HttpRequest request() {
                return null;
            }

            @Override
            public Optional<HttpResponse<Object>> previousResponse() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }

            @Override
            public String body() {
                return "{\"location\":{\"name\":\"Zamość\",\"region\":\"\",\"country\":\"Pologne\",\"lat\":50.72,\"lon\":23.25,\"tz_id\":\"Europe/Warsaw\",\"localtime_epoch\":1660247588,\"localtime\":\"2022-08-11 21:53\"},\"current\":{\"last_updated_epoch\":1660247100,\"last_updated\":\"2022-08-11 21:45\",\"temp_c\":15.3,\"temp_f\":59.5,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":6.0,\"wind_kph\":9.7,\"wind_degree\":24,\"wind_dir\":\"NNE\",\"pressure_mb\":1022.0,\"pressure_in\":30.17,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":77,\"cloud\":43,\"feelslike_c\":15.3,\"feelslike_f\":59.5,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":1.0,\"gust_mph\":12.8,\"gust_kph\":20.5}}\n";
            }

            @Override
            public Optional<SSLSession> sslSession() {
                return Optional.empty();
            }

            @Override
            public URI uri() {
                return null;
            }

            @Override
            public HttpClient.Version version() {
                return null;
            }
        };

        Mockito.when(requestUrlBuilder.build("Example")).thenReturn(new URI("http://example.com/some/url/Example"));
        Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

        Assertions.assertInstanceOf(
            Weather.class,
            weatherDataProvider.execute(new Request("Example"))
        );
    }

    @Test
    public void executeWillParseComplexCityName() throws URISyntaxException, IOException, InterruptedException, WeatherNotFoundException, ParseException {
        HttpResponse<Object> response = new HttpResponse<>() {
            @Override
            public int statusCode() {
                return 200;
            }

            @Override
            public HttpRequest request() {
                return null;
            }

            @Override
            public Optional<HttpResponse<Object>> previousResponse() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }

            @Override
            public String body() {
                return "{\"location\":{\"name\":\"Palma de Mallorca\",\"region\":\"\",\"country\":\"Spain\",\"lat\":50.72,\"lon\":23.25,\"tz_id\":\"Europe/Warsaw\",\"localtime_epoch\":1660247588,\"localtime\":\"2022-08-11 21:53\"},\"current\":{\"last_updated_epoch\":1660247100,\"last_updated\":\"2022-08-11 21:45\",\"temp_c\":15.3,\"temp_f\":59.5,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":6.0,\"wind_kph\":9.7,\"wind_degree\":24,\"wind_dir\":\"NNE\",\"pressure_mb\":1022.0,\"pressure_in\":30.17,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":77,\"cloud\":43,\"feelslike_c\":15.3,\"feelslike_f\":59.5,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":1.0,\"gust_mph\":12.8,\"gust_kph\":20.5}}\n";
            }

            @Override
            public Optional<SSLSession> sslSession() {
                return Optional.empty();
            }

            @Override
            public URI uri() {
                return null;
            }

            @Override
            public HttpClient.Version version() {
                return null;
            }
        };

        Mockito.when(requestUrlBuilder.build("Palma+De+Mallorca")).thenReturn(new URI("http://example.com/some/url/Example"));
        Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

        Assertions.assertInstanceOf(
            Weather.class,
            weatherDataProvider.execute(new Request("Palma De Mallorca"))
        );
    }
}