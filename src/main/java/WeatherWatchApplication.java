public class WeatherWatchApplication {
    public static void main(String[] args) throws Exception {
        final String forecastURL = "http://api.weatherapi.com/v1/history.json?key=610ec3279f83484a9b2143452251302&q=Katowice&dt=2025-02-10";

        WeatherDataParser parser = new WeatherDataParser();

        String weatherData = parser.fetchWeatherData(forecastURL);

        parser.parseAndStoreWeatherData(weatherData);
    }
}
