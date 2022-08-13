package MyWeatherGUI.DTO;

public record Location(String city, String timezone, double latency, double longitude)
{
    @Override
    public String toString() {
        return String.format(
                "Current weather for %s(%.3f, %.3f) with timezone %s.",
                this.city(),
                this.latency(),
                this.longitude(),
                this.timezone()
        );
    }
}
