package main;


import com.oracle.javafx.jmx.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class LogIn extends MotionPanel implements ActionListener {
    Timer t;
    ImageIcon ii = new ImageIcon(getClass().getResource("LeagueWidget.png"));
    Image i = ii.getImage();
    String clientStatus, serverStatus;
    boolean setUp = false;
    Font f1 = new Font("Palatino Linotype", Font.BOLD, 11);
    JComboBox<String> region;
    JTextField username;
    JButton logIn;

    public LogIn(JFrame parent) {
        super(parent);
        this.setLayout(null);
        setSize(new Dimension(i.getHeight(this), i.getWidth(this)));
        this.setBackground(new Color(0, 0, 0, 0));
        initComponents();
        update();
        Timer t = new Timer(60000, this);
        t.start();
    }

    private void initComponents() {
        JLabel userName = new JLabel("Summoner Name");
        userName.setBounds(30, 100, 95, 20);
        userName.setFont(f1);
        userName.setForeground(new Color(40,75,65));
        String[] strings = {"Oceania", "North America"};
        region = new JComboBox<>(strings);
        region.setBounds(70, 50, 160, 40);
        region.setFont(f1);
        username = new JFormattedTextField();
        username.setBounds(140, 100, 120, 20);
        username.setFont(f1);
        logIn = new JButton("Log In");
        logIn.setBounds(95, 140, 110, 30);
        logIn.setIcon(new ImageIcon(getClass().getResource("AcceptButton.png")));
        logIn.setFont(f1);
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.switchView("ServerStatus");
            }
        });
        logIn.setOpaque(false);
        logIn.setContentAreaFilled(false);
        logIn.setBorderPainted(false);
        logIn.setFocusPainted(false);
        this.add(region);
        this.add(username);
        this.add(userName);
        this.add(logIn);
    }

    private void update() {
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
        g.drawImage(i, 0,0,this);
    }
}
