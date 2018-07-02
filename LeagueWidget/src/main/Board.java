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
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by Hakanoreaver on 30/6/18.
 */
public class Board extends MotionPanel implements ActionListener {
    Timer t;
    ImageIcon ii = new ImageIcon(getClass().getResource("LeagueWidget.png"));
    Image i = ii.getImage();
    String clientStatus = "", serverStatus = "";
    boolean setUp = false;
    Font f1 = new Font("Palatino Linotype", Font.BOLD, 18);
    String APIKey = "RGAPI-e2847315-d0e5-40b5-a5f4-809dc9f56ccb";
    String ping = "";

    public Board(JFrame parent) {
        super(parent);
        setSize(new Dimension(i.getHeight(this), i.getWidth(this)));
        this.setBackground(new Color(0, 0, 0, 0));
        update();
        Timer t = new Timer(10000, this);
        t.start();
    }

    private void update() {
        try {
            String ipAddress = "203.174.139.185";
            InetAddress inet = InetAddress.getByName(ipAddress);
            long finish = 0;
            long start = new GregorianCalendar().getTimeInMillis();

            if (inet.isReachable(5000)){
                finish = new GregorianCalendar().getTimeInMillis();
                ping = (finish - start) + "ms";
            } else {
                ping = "undefined";
            }
        } catch ( Exception e ) {
            System.out.println("Exception:" + e.getMessage());
        }

        try {
            URL url;
            url = new URL("https://oc1.api.riotgames.com/lol/status/v3/shard-data?api_key=" + APIKey);
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
                clientStatus = "Error";
                serverStatus = "Error";
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
        g.setColor(new Color(40,75,65));
        g.drawImage(i, 0,0,this);
        g.setFont(f1);
        g.drawString(clientStatus, 30, 40);
        g.drawString(serverStatus, 30, 70);
        g.drawString(ping, 30, 100);
    }
}
