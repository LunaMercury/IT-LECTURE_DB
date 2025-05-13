package com.example.demo.C02OpenWeatherMap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
@RequestMapping("/open/weather")
public class OpenWeatherController {
    @GetMapping("/get/{lat}/{lon}")
    public ResponseEntity<OpenWeatherData> get(@PathVariable String lat, @PathVariable String lon) {
        log.info("GET /open/weather/get....");

        String url = "https://api.openweathermap.org/data/2.5/weather";
        lat = "35.8";
        lon = "128.6";
        String appid = System.getenv("OPENWEATHERMAP_API");

        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", URLEncoder.encode(appid, StandardCharsets.UTF_8))
                .build(true)
                .toUri();

        // 요청 -> 응답
        RestTemplate rt = new RestTemplate();
        ResponseEntity<OpenWeatherData> response = rt.exchange(uri, HttpMethod.GET, null, OpenWeatherData.class);
        System.out.println(response);

        return ResponseEntity.ok(response.getBody());
    }
}
