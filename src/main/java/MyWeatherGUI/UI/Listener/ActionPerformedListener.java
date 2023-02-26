package MyWeatherGUI.UI.Listener;

import MyWeatherGUI.DTO.Request;
import MyWeatherGUI.DTO.Weather;
import MyWeatherGUI.Http.WeatherDataProvider;
import MyWeatherGUI.UI.Translation;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import MyWeatherGUI.Exception.WeatherNotFoundException;

public class ActionPerformedListener implements ActionListener
{
    private JLabel cityLabel;
    private JLabel temperatureLabel;
    private JLabel windLabel;
    private final JPanel bottomPanel;
    private final JFrame frame;
    private final JTextField cityTextField;
    private final WeatherDataProvider weatherDataProvider = new WeatherDataProvider();

    public ActionPerformedListener(
        JLabel cityLabel,
        JLabel temperatureLabel,
        JLabel windLabel,
        JPanel bottomPanel,
        JFrame frame,
        JTextField cityTextField
    ) {
        this.cityLabel = cityLabel;
        this.temperatureLabel = temperatureLabel;
        this.windLabel = windLabel;
        this.bottomPanel = bottomPanel;
        this.frame = frame;
        this.cityTextField = cityTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();

        if (action.equals(Translation.BUTTON_NAME)) {
            bottomPanel.removeAll();

            try {
                String city = cityTextField.getText().trim();

                Weather weather = weatherDataProvider.execute(new Request(city));

                cityLabel = new JLabel(
                        String.format(
                            Translation.CITY_FORMAT,
                            weather.location().city(),
                            weather.location().latency(),
                            weather.location().longitude()
                        )
                );
                temperatureLabel = new JLabel (String.format(Translation.TEMPERATURE_FORMAT, weather.temperature().temperature()));
                windLabel = new JLabel (String.format(Translation.WIND_FORMAT, weather.wind().speed(), weather.wind().direction()));

                bottomPanel.add(cityLabel);
                bottomPanel.add(temperatureLabel);
                bottomPanel.add(windLabel);
            } catch (IOException | InterruptedException | ParseException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            } catch (WeatherNotFoundException ex) {
                JLabel cityNotFoundLabel = new JLabel(Translation.CITY_NO_DATA);

                bottomPanel.removeAll();
                bottomPanel.add(cityNotFoundLabel);
            } finally {
                frame.revalidate();
                frame.repaint();
            }
        }
    }
}
