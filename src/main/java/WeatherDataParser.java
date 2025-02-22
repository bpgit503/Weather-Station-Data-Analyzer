import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WeatherDataParser {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/weather_watch";
    private static final String USER = "root";
    private static final String PASS = "Admin123!";


    public String fetchWeatherData(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            content.append(line);
        }
        in.close();
        conn.disconnect();

        return content.toString();
    }

    public void parseAndStoreWeatherData(String jsonResponse) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            JSONObject json = new JSONObject(jsonResponse);

            JSONObject location = json.getJSONObject("location");
            String city = location.getString("name");
            String country = location.getString("country");
            double lat = location.getDouble("lat");
            double lon = location.getDouble("lon");

            JSONObject forecastDay = json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0);
            String date = forecastDay.getString("date");
            JSONObject day = forecastDay.getJSONObject("day");

            double minTemp = day.getDouble("mintemp_c");
            double maxTemp = day.getDouble("maxtemp_c");
            double avgTemp = day.getDouble("avgtemp_c");
            double avgHumidity = day.getDouble("avghumidity");
            double maxWind = day.getDouble("maxwind_kph");
            double precip = day.getDouble("totalprecip_mm");
            double snow = day.getDouble("totalsnow_cm");
            double uvIndex = day.getDouble("uv");
            String condition = day.getJSONObject("condition").getString("text");

        } catch (Exception e) {
            System.out.println(e);
        }

    }


}
