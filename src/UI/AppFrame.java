package UI;

import Config.FrameConfig;
import UI.Listener.ActionPerformedListener;

import javax.swing.*;
import java.awt.*;

public class AppFrame implements Runnable
{
    JFrame frame;
    JPanel formPanel;
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

        formPanel = new JPanel();
        formPanel.setBackground(new Color(215, 241, 250));

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 245, 245));

        JLabel formLabel = new JLabel(Translation.APP_HEADER);
        city = new JTextField(15);

        JButton button = new JButton(Translation.BUTTON_NAME);
        button.addActionListener(new ActionPerformedListener(cityLabel, temperatureLabel, windLabel, bottomPanel, frame, city));

        formPanel.add(formLabel);
        formPanel.add(city);
        formPanel.add(button);

        JLabel defaultLabel = new JLabel(Translation.DEFAULT_PANEL_MESSAGE);

        bottomPanel.add(defaultLabel);

        panel.add(formPanel);
        panel.add(bottomPanel);

        panel.setDividerSize(0);
        panel.setResizeWeight(0.15);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
