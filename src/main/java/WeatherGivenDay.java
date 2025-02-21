import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherGivenDay {
    private double maxtemp_c;
    private double mintemp_c;
    private double avgtemp_c;
    private int avghumidity;
    private double totalprecip_mm;
    private double totalsnow_cm;
    private WeatherCondition condition;
    private double uv;
}
