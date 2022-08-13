package MyWeatherGUI.DTO;

public record Wind(double speed, String direction)
{
    @Override
    public String toString() {
        return String.format(
                "Wind has %f kilometres per hour with %s direction.",
                this.speed(),
                this.direction()
        );
    }
}
