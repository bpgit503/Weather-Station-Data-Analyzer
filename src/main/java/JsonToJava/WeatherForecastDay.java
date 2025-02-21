package JsonToJava;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecastDay {
    private String date;
    private WeatherGivenDay day;
}
