package main;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{


    public Main()  {
        initUI();
    }

    private void initUI() {
        this.setSize(400, 400);
        this.setResizable(false);
        this.setTitle("Display a White Circle");
        this.setUndecorated(true);
        Board b = new Board(this);
        b.setSize(new Dimension(400,400));
        this.add(b);
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
}