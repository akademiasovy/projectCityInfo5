package sample;

import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebWeather {

    public Weather getData(String city, String code2) throws Exception{
        Weather weather=null;
        try {

            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+","+code2+"&units=metric&appid=8a493275eec43a055015e52e37ec5329");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {


                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output=br.readLine();
                System.out.println(output);

                JSONObject obj = new JSONObject(output);
                double temp = obj.getJSONObject("main").getDouble("temp");
                int humidity = obj.getJSONObject("main").getInt("humidity");
                double coord_lon = obj.getJSONObject("coord").getDouble("lon");
                double coord_lat = obj.getJSONObject("coord").getDouble("lat");
                String country = obj.getJSONObject("sys").getString("country");
                String city2 = obj.getString("name");


                System.out.println(temp+" "+humidity+" "+coord_lat+" "+coord_lon);
                weather = new Weather(city2,country,temp,humidity,coord_lon,coord_lat);

            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return weather;
    }
}
