package main;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    static CardLayout layout = new CardLayout();
    static JPanel j;
    public Main()  {
        initUI();
    }

    private void initUI() {
        this.setSize(299, 225);
        this.setResizable(false);
        this.setTitle("Display a White Circle");
        this.setLayout(layout);
        this.setUndecorated(true);
        Board b = new Board(this);
        LogIn l = new LogIn(this);
        l.setSize(new Dimension(299,225));
        b.setSize(new Dimension(299,225));

        j = new JPanel(layout);
        j.setBackground(new Color(0, 0, 0, 0));
        j.add(l, "LogIn");
        j.add(b, "ServerStatus");
        this.add(j);
        AWTUtilities.setWindowOpaque(this, false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Main ex = new Main();
            }
        });
    }

    public static void switchView(String view) {
        layout.show(j, view);
    }


}