package ua.ithillel.weather.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.weather.model.WeatherInfo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenWeatherClient implements WeatherClient {
    private static final String API_KEY = System.getenv("OPEN_WEATHER_API_KEY");
    private final HttpClient httpClient;

    public OpenWeatherClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public WeatherInfo getWeatherByCity(String city) {
        try {
            String url
                    = String.format("https://api.openweathermap.org/data/2.5/weather?appid=%s&units=metric&q=%s", API_KEY, city);

            HttpRequest request = HttpRequest.newBuilder().GET().uri(new URI(url)).build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.body(), WeatherInfo.class);
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
