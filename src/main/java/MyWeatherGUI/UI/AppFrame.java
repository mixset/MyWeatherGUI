package MyWeatherGUI.UI;

import MyWeatherGUI.Config.FrameConfig;
import MyWeatherGUI.UI.Listener.SearchCityActionPerformedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AppFrame implements Runnable
{
    JFrame frame;
    JPanel formPanel;
    JPanel bottomPanel;
    JTextField cityTextField;

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
        cityTextField = new JTextField(15);

        JButton button = new JButton(Translation.BUTTON_NAME);

        ActionListener actionListener = new SearchCityActionPerformedListener(
            bottomPanel,
            frame,
            cityTextField
        );

        button.addActionListener(actionListener);

        cityTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });

        formPanel.add(formLabel);
        formPanel.add(cityTextField);
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
