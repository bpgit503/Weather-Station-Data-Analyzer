public class WeatherWatchApplication {
    public static void main(String[] args) throws Exception {
        final String forecastURL = "http://api.weatherapi.com/v1/history.json?key=610ec3279f83484a9b2143452251302&q=Katowice&dt=2025-02-12";

        WeatherDataParser parser = new WeatherDataParser();
        try {

            String JsonResponseWeatherData = parser.fetchWeatherData(forecastURL);

            parser.parseAndStoreWeatherData(JsonResponseWeatherData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
