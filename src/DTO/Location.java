package DTO;

public class Location
{
    private final String city;
    private final String timezone;
    private final double latency;
    private final double longitude;

    public Location(String city, String timezone, double latency, double longitude)
    {
        this.city = city;
        this.timezone = timezone;
        this.latency = latency;
        this.longitude = longitude;
    }

    public String getCity()
    {
        return city;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public double getLatency()
    {
        return latency;
    }

    public double getLongitude()
    {
        return longitude;
    }

    @Override
    public String toString()
    {
        return String.format(
            "Current weather for %s(%.3f, %.3f) with timezone %s.",
            this.getCity(),
            this.getLatency(),
            this.getLongitude(),
            this.getTimezone()
        );
    }
}
