package main;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Hakanoreaver on 30/6/18.
 */
public class Board extends MotionPanel implements ActionListener {
    Timer t;
    ImageIcon ii = new ImageIcon(getClass().getResource("ClockImage.png"));
    Image i = ii.getImage();
    int minutes = 0, hours = 0, seconds = 0;
    String time = "";
    Font f1 = new Font("Palatino Linotype", Font.BOLD, 40);

    public Board(JFrame parent) {
        super(parent);
        setSize(new Dimension(400, 400));
        this.setBackground(new Color(0, 0, 0, 0));
        Timer t = new Timer(1000, this);
        t.start();
    }

    private void update() {
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        hours =  calendar.get(Calendar.HOUR);
        minutes = calendar.get(Calendar.MINUTE);

        String minutess, hourss;

        if(minutes < 10) {
            minutess = "0" + Integer.toString(minutes);
        }
        else {
            minutess = Integer.toString(minutes);
        }

        if(hours < 10) {
            hourss = "0" + Integer.toString(hours);
        }
        else {
            hourss = Integer.toString(hours);
        }
        time = hourss + " : " + minutess;
    }
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
        System.out.println("here");
    }


    public void paintComponent(Graphics g) {
        drawObjects(g);
    }

    public void drawObjects(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(i, 0,0,this);
        g.setFont(f1);
        g.drawString(time, 65, 90);
    }
}
