package main;

import main.UI.AppFrame;
import javax.swing.*;

public class MyWeatherGUI
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new AppFrame().run());
    }
}
