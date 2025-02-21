import JsonToJava.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class ApiCaller {
    private String forecastURL;
    private StringBuffer content;

    public ApiCaller(String forecastURL) {

        this.forecastURL = forecastURL;
        content = new StringBuffer();
    }

    public void openConnection() {
        HttpURLConnection conn = null;
        try {

            URL url = new URL(this.forecastURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000); // Timeout for connection
            conn.setReadTimeout(5000); // Timeout for reading
            conn.connect();

            if (conn.getResponseCode() != 200) {

                System.out.println("Error: Unable to fetch weather data");
                return;
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        } finally {

            if (conn != null) conn.disconnect();
        }
    }

    public Optional<WeatherResponse> jsonToJava() {

        if (content.length() == 0) {
            System.err.println("No content to parse");
            return Optional.empty();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {

            WeatherResponse weatherData = mapper.readValue(content.toString(), WeatherResponse.class);
            return Optional.ofNullable(weatherData);

        } catch (JsonProcessingException e) {

            System.out.println(e.getMessage());

            return Optional.empty();
        }

    }
}
