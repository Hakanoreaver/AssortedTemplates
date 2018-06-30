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
        logIn.setBounds(100, 140, 80, 20);
        logIn.setFont(f1);
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        this.add(region);
        this.add(username);
        this.add(userName);
        this.add(logIn);
    }

    private void update() {
        try {
            URL url;
            url = new URL("https://oc1.api.riotgames.com/lol/status/v3/shard-data?api_key=RGAPI-04513ec0-5282-47ba-9cfd-9e2dd8f1d77a");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(
                        new InputStreamReader(con.getInputStream(), "UTF-8"));
                System.out.println(jsonObject);
                JSONArray a = (JSONArray) jsonObject.get("services");
                JSONObject b = (JSONObject) a.get(0);
                JSONObject c = (JSONObject) a.get(3);
                clientStatus = (String) b.get("status");
                serverStatus = (String) c.get("status");


            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
