package com.example.trueweather.WeatherInformation;

import java.util.List;

public class OpenWeatherInformation {

    private List<Weather> weather;
    private String base;
    private  Main main;


    public OpenWeatherInformation() {
    }

    public OpenWeatherInformation( List<Weather> weatherList, String base, Main main) {

        this.weather = weatherList;
        this.base = base;
        this.main = main;

    }






    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }



    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }


}
