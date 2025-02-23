import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherWatchApplication {
    public static void main(String[] args) throws Exception {

        WeatherDataParser parser = new WeatherDataParser();
        try {
            LocalDate startDate = LocalDate.of(2024, 2, 23);
            LocalDate endDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                String formattedDate = date.format(formatter);
                String forecastURL = "http://api.weatherapi.com/v1/history.json?key=610ec3279f83484a9b2143452251302&q=Katowice&dt=" + formattedDate;

                // Fetch API data
                String JsonResponseWeatherData = parser.fetchWeatherData(forecastURL);

                // Parse and insert into DB
                parser.parseAndStoreWeatherData(JsonResponseWeatherData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
