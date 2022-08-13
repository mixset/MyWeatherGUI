package MyWeatherGUI.DTO;

public class Wind
{
    private final double speed;
    private final String direction;

    public Wind(double speed, String direction)
    {

        this.speed = speed;
        this.direction = direction;
    }

    public double getSpeed()
    {
        return speed;
    }

    public String getDirection()
    {
        return direction;
    }

    @Override
    public String toString()
    {
        return String.format(
            "Wind has %f kilometres per hour with %s direction.",
            this.getSpeed(),
            this.getDirection()
        );
    }
}
