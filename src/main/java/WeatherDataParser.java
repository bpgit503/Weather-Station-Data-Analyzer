import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
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

            int locationId = insertLocation(conn, city, country, lat, lon);

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

            int weatherId = insertWeatherData(conn, locationId, date, minTemp, maxTemp, avgTemp, avgHumidity, maxWind, condition, precip, snow, uvIndex);


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private int insertLocation(Connection conn, String city, String country, double lat, double lon) throws SQLException {
        String query = "INSERT INTO locations (city, country, lat, lon) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE city=city";
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, city);
        stmt.setString(2, country);
        stmt.setDouble(3, lat);
        stmt.setDouble(4, lon);
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            // If the location already exists, fetch its ID
            query = "SELECT location_id FROM locations WHERE city = ? AND country = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, city);
            stmt.setString(2, country);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("location_id");
        }
    }

    private int insertWeatherData(Connection conn, int locationId, String date, double minTemp, double maxTemp, double avgTemp,
                                  double avgHumidity, double maxWind, String weather_condition, double precip, double snow, double uvIndex) throws SQLException {
        String query = "INSERT INTO weather_data (location_id, date, min_temp_c, max_temp_c, avg_temp_c, avg_humidity, max_wind_kph, weather_condition, precip_mm, totalsnow_cm, uv_index) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, locationId);
        stmt.setString(2, date);
        stmt.setDouble(3, minTemp);
        stmt.setDouble(4, maxTemp);
        stmt.setDouble(5, avgTemp);
        stmt.setDouble(6, avgHumidity);
        stmt.setDouble(7, maxWind);
        stmt.setString(8, weather_condition);
        stmt.setDouble(9, precip);
        stmt.setDouble(10, snow);
        stmt.setDouble(11, uvIndex);
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }


}
