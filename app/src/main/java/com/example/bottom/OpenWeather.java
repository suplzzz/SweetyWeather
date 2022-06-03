package com.example.bottom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeather {

    String API_TOKEN = "92b012e89c4d7b80c590911619634f08";

    private static String getUrlContent(String urlAddress) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    String initialize() throws JSONException {
        String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + "Volgograd" + "&appid=" + API_TOKEN + "&units=metric");
        JSONObject obj = new JSONObject(output);
        double tempa = obj.getJSONObject("main").getDouble("temp");
        return String.valueOf(tempa);
    }
}