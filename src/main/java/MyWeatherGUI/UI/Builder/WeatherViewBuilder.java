package MyWeatherGUI.UI.Builder;

import MyWeatherGUI.DTO.Weather;
import MyWeatherGUI.UI.Translation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class WeatherViewBuilder
{
    public JPanel build(Weather weather) throws IOException
    {
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(new MatteBorder(0, 0, 0, 0, Color.LIGHT_GRAY));

        container.add(this.buildCityPanel(weather), BorderLayout.NORTH);
        container.add(this.buildWeatherPanel(weather), BorderLayout.WEST);
        container.add(this.buildConditionPanel(weather), BorderLayout.EAST);

        return container;
    }

    private JPanel buildCityPanel(Weather weather)
    {
        JLabel cityLabel = new JLabel(
            String.format(
                Translation.CITY_FORMAT,
                weather.location().city(),
                weather.location().latency(),
                weather.location().longitude()
            )
        );

        JPanel cityPanel = new JPanel();
        cityPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        cityPanel.add(cityLabel);

        return cityPanel;
    }

    private JPanel buildWeatherPanel(Weather weather)
    {
        JLabel temperatureLabel = new JLabel(
            String.format(Translation.TEMPERATURE_FORMAT, weather.temperature().temperature()),
            SwingConstants.CENTER
        );

        JLabel windLabel = new JLabel(
            String.format(Translation.WIND_FORMAT, weather.wind().speed(), weather.wind().direction()),
            SwingConstants.CENTER
        );

        JPanel weatherPanel = new JPanel();

        weatherPanel.setLayout(new GridLayout(2, 1));
        weatherPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

        weatherPanel.add(temperatureLabel);
        weatherPanel.add(windLabel);

        return weatherPanel;
    }

    private JPanel buildConditionPanel(Weather weather)
    {
        JLabel conditionLabel = new JLabel();

        ImageIcon icon = null;

        try {
            URL url = new URL("https:" + weather.condition().icon());
            icon = new ImageIcon(ImageIO.read(url));
        } catch (IOException e) {
            URL imageUrl = getClass().getResource("/weather-placeholder.png");
            if (imageUrl != null) {
                icon = new ImageIcon(imageUrl);
            }
        }
        conditionLabel.setIcon(icon);

        JPanel conditionPanel = new JPanel();
        conditionPanel.setLayout(new BoxLayout(conditionPanel, BoxLayout.Y_AXIS));
        conditionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        conditionPanel.add(Box.createVerticalGlue());
        conditionPanel.add(conditionLabel);
        conditionPanel.add(Box.createVerticalGlue());

        return conditionPanel;
    }
}
