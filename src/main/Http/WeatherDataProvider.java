package main.Http;

import main.Builder.RequestUrlBuilder;
import main.DTO.*;
import main.Exception.WeatherNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;

public class WeatherDataProvider
{
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private final RequestUrlBuilder requestUrlBuilder = new RequestUrlBuilder();

    public Weather execute(Request request) throws IOException, InterruptedException, ParseException, URISyntaxException, WeatherNotFoundException
    {
        URI url = requestUrlBuilder.build(request.getCity());

        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(url).build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (Objects.equals(400, response.statusCode())) {
            throw new WeatherNotFoundException("Weather could not be fetched for given city.");
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.body());
        JSONObject location = (JSONObject) jsonObject.get("location");
        JSONObject current = (JSONObject) jsonObject.get("current");

        return new Weather(
            new Location(
                location.get("name").toString(),
                location.get("tz_id").toString(),
                Double.parseDouble(location.get("lat").toString()),
                Double.parseDouble(location.get("lon").toString())
            ),
            new Temperature(
                Double.parseDouble(current.get("temp_c").toString())
            ),
            new Wind(
                Double.parseDouble(current.get("wind_kph").toString()),
                current.get("wind_dir").toString()
            )
        );
    }
}
