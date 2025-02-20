import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        final String forecastURL = "http://api.weatherapi.com/v1/history.json?key=610ec3279f83484a9b2143452251302&q=Katowice&dt=2025-02-10";
        final String currentDayURL = "http://api.weatherapi.com/v1/current.json?key=610ec3279f83484a9b2143452251302&q=Katowice&aqi=no";
        try {
            URL url = new URL(forecastURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
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

            System.out.println("Location: " + weatherData.getLocation().name + ", " + weatherData.getLocation().country);
            System.out.println("Current Date & Time: " + weatherData.getLocation().localtime);
            System.out.println("Weather Condition: " + weatherData.getForecast().getForecastday().get(0).getDay().getCondition().getText());
            System.out.println("Date & Time: " + weatherData.getForecast().getForecastday().get(0).getDate());
            System.out.println("Max Temperature: " + weatherData.getForecast().getForecastday().get(0).getDay().getMaxtemp_c());
            System.out.println("Min Temperature: " + weatherData.getForecast().getForecastday().get(0).getDay().getMintemp_c());
            System.out.println("Avg Temperature: " + weatherData.getForecast().getForecastday().get(0).getDay().getAvgtemp_c());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
