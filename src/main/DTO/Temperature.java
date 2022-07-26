package main.DTO;

public class Temperature
{
    private final double temperature;

    public Temperature(double temperature)
    {
        this.temperature = temperature;
    }

    public double getTemperature()
    {
        return temperature;
    }

    @Override
    public String toString()
    {
        return String.format("Temperature is %s celsius degrees.", this.getTemperature());
    }
}
