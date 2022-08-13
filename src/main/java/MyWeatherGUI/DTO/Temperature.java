package MyWeatherGUI.DTO;

public record Temperature(double temperature)
{

    @Override
    public String toString() {
        return String.format("Temperature is %s celsius degrees.", this.temperature());
    }
}
