package main;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

public class Test3 {

    protected static void initUI() {
        JFrame frame = new JFrame("test");
        frame.setUndecorated(true);
        AWTUtilities.setWindowOpaque(frame, false);
        JLabel label = new JLabel("Hello NOT transparent label");
        label.setOpaque(true);
        label.setBackground(new Color(255, 0, 0));
        JLabel transLabel = new JLabel("Hello transparent label");
        transLabel.setOpaque(true);
        transLabel.setBackground(new Color(255, 0, 0, 50));
        frame.setLocationByPlatform(true);
        frame.getContentPane().add(label);
        frame.getContentPane().add(transLabel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                initUI();
            }
        });
    }
}