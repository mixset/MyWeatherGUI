package main.DTO;

public class Weather
{
    private final Location location;
    private final Temperature temperature;
    private final Wind wind;

    public Weather(Location location, Temperature temperature, Wind wind)
    {

        this.location = location;
        this.temperature = temperature;
        this.wind = wind;
    }

    public Location getLocation()
    {
        return location;
    }

    public Temperature getTemperature()
    {
        return temperature;
    }

    public Wind getWind()
    {
        return wind;
    }
}
