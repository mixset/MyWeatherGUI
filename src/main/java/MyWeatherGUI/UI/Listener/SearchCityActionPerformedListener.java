package MyWeatherGUI.UI.Listener;

import MyWeatherGUI.Builder.RequestUrlBuilder;
import MyWeatherGUI.DTO.Request;
import MyWeatherGUI.DTO.Weather;
import MyWeatherGUI.Http.WeatherDataProvider;
import MyWeatherGUI.Provider.ApplicationConfigProvider;
import MyWeatherGUI.UI.Builder.WeatherViewBuilder;
import MyWeatherGUI.UI.Translation;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import MyWeatherGUI.Exception.WeatherNotFoundException;

public class SearchCityActionPerformedListener implements ActionListener
{
    private final JPanel bottomPanel;
    private final JFrame frame;
    private final JTextField cityTextField;

    private final WeatherDataProvider weatherDataProvider = new WeatherDataProvider(
        new RequestUrlBuilder(new ApplicationConfigProvider())
    );

    public SearchCityActionPerformedListener(
        JPanel bottomPanel,
        JFrame frame,
        JTextField cityTextField
    ) {
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

                WeatherViewBuilder weatherViewBuilder = new WeatherViewBuilder();
                JPanel container = weatherViewBuilder.build(weather);
                container.setPreferredSize(bottomPanel.getSize());

                bottomPanel.add(container);
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
