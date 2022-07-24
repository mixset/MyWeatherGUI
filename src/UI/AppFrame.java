package UI;

import Config.FrameConfig;
import DTO.Request;
import DTO.Weather;
import Http.WeatherDataProvider;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import Exception.WeatherNotFoundException;

public class AppFrame implements Runnable, ActionListener
{
    JFrame frame;
    JPanel bottomPanel;
    JTextField city;
    JLabel cityLabel;
    JLabel temperatureLabel;
    JLabel windLabel;

    public void run()
    {
        frame = new JFrame(FrameConfig.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FrameConfig.WINDOW_WIDTH, FrameConfig.WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icon.png"));
        frame.setResizable(false);

        JSplitPane panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(215, 241, 250));

        JLabel formLabel = new JLabel("Wpisz nazwę miasta, aby pobrać pogodę:");
        city = new JTextField(15);
        JButton button = new JButton("Pobierz");
        button.addActionListener(this);

        formPanel.add(formLabel);
        formPanel.add(city);
        formPanel.add(button);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 245, 245));

        JLabel defaultLabel = new JLabel ("Uzupełnij formularz, aby wyświetlic dane pogodowe.");

        bottomPanel.add(defaultLabel);

        panel.add(formPanel);
        panel.add(bottomPanel);
        panel.setDividerSize(0);
        panel.setResizeWeight(0.15);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();

        if (action.equals("Pobierz")) {
            WeatherDataProvider weatherDataProvider = new WeatherDataProvider();
            try {
                Weather weather = weatherDataProvider.execute(new Request(city.getText().trim()));

                cityLabel = new JLabel(
                    String.format(
                        "Miasto: %s(%.3f, %.3f) \n",
                        weather.getLocation().getCity(),
                        weather.getLocation().getLatency(),
                        weather.getLocation().getLongitude()
                    )
                );
                temperatureLabel = new JLabel (String.format("Temperatura: %s stopni Celsiusa \n", weather.getTemperature().getTemperature()));
                windLabel = new JLabel (String.format("Wiatr: %f km/h, kierunek: %s \n", weather.getWind().getSpeed(), weather.getWind().getDirection()));

                bottomPanel.removeAll();

                bottomPanel.add(cityLabel);
                bottomPanel.add(temperatureLabel);
                bottomPanel.add(windLabel);

                frame.revalidate();
                frame.repaint();
            } catch (IOException | InterruptedException | ParseException | URISyntaxException | WeatherNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
