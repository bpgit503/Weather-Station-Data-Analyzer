import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        final String forecastURL = "http://api.weatherapi.com/v1/history.json?key=610ec3279f83484a9b2143452251302&q=Katowice&dt=2025-02-10";
        final String currentDayURL = "http://api.weatherapi.com/v1/current.json?key=610ec3279f83484a9b2143452251302&q=Katowice&aqi=no";

        ApiCaller getPastWeatherAPI = new ApiCaller(forecastURL);
        getPastWeatherAPI.openConnection();

        Optional<WeatherResponse> weatherData = getPastWeatherAPI.jsonToJava();

        if (weatherData.isPresent()) {
            System.out.println(weatherData.get().getLocation().getCountry());
        } else {
            System.out.println("No weather data found");
        }
    }
}
