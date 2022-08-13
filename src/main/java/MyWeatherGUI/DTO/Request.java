package MyWeatherGUI.DTO;

public class Request
{
    private final String city;

    public Request(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
}
