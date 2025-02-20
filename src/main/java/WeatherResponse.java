import lombok.Getter;

@Getter
public class WeatherResponse {
    private WeatherLocation location;
    private WeatherCurrent current;
    private WeatherForecast forecast;
}
