import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=610ec3279f83484a9b2143452251302&q=Poland&aqi=no");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if(conn.getResponseCode() != 200) {
                System.out.println("Error: Unable to fetch weather data");
                return;
            }



            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            WeatherResponse weatherData = mapper.readValue(content.toString(), WeatherResponse.class);

            System.out.println("Location: " + weatherData.location.name + ", " + weatherData.location.country);
            System.out.println("Date & Time: " + weatherData.location.localtime);
            System.out.println("Temperature (°C): " + weatherData.current.temp_c);
            System.out.println("Wind Speed (kph): " + weatherData.current.wind_kph);
            System.out.println("Weather Condition: " + weatherData.current.condition.text);


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
