package ua.ithillel.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {
    private List<Weather> weather;
    private WeatherMain main;

    public  List<Weather> getWeather() {
        return weather;
    }

    public void setWeather( List<Weather> weather) {
        this.weather = weather;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "weather=" + weather +
                ", main=" + main +
                '}';
    }
}
