package com.example.demo.C02OpenWeatherMap;

import lombok.Data;

import java.util.List;


@Data
public class OpenWeatherData {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    @Data
    public static class Coord {
        public double lon;
        public double lat;
    }

    @Data
    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    @Data
    public static class Main {
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;
        public int sea_level;
        public int grnd_level;
    }

    @Data
    public static class Wind {
        public double speed;
        public int deg;
    }

    @Data
    public static class Clouds {
        public int all;
    }

    @Data
    public static class Sys {
        public int type;
        public int id;
        public String country;
        public int sunrise;
        public int sunset;
    }
}

