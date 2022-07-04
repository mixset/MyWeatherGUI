package UI;

import Config.FrameConfig;

import javax.swing.*;
import java.awt.*;

public class AppFrame implements Runnable
{
    public void run()
    {
        JFrame frame = new JFrame(FrameConfig.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FrameConfig.WINDOW_WIDTH, FrameConfig.WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icon.png"));
    }
}
