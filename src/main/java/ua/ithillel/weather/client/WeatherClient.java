package ua.ithillel.weather.client;

import ua.ithillel.weather.model.WeatherInfo;

public interface WeatherClient {
    WeatherInfo getWeatherByCity(String city);
}
