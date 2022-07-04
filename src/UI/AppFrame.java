package UI;

import Config.FrameConfig;

import javax.swing.*;
import java.awt.*;

public class AppFrame implements Runnable
{
    // frame
    static JFrame f;

    // text areas
    static JTextArea t1, t2;

    public void run()
    {
        JFrame frame = new JFrame(FrameConfig.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FrameConfig.WINDOW_WIDTH, FrameConfig.WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icon.png"));

        JPanel MyPanel1 = new JPanel();

        MyPanel1.setLayout( new BorderLayout() );

        JButton x1 = new JButton("I am x1");

        MyPanel1.add(x1, "North");

        frame.getContentPane().add( MyPanel1, "North");

        JPanel MyPanel2 = new JPanel();

        MyPanel2.setLayout( new BorderLayout() );

        frame.getContentPane().add( MyPanel2, "South");
    }
}
