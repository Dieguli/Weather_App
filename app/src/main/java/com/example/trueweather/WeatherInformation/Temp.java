package com.example.trueweather.WeatherInformation;

public class Temp {

    private double day;
    private double min;
    private double max;
    private double night;

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getNight() {
        return night;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getEve() {
        return eve;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public double getMorn() {
        return morn;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }

    private double eve;
    private double morn;

    public Temp(double day, double min, double max, double night, double eve, double morn){
        this.day= day;
        this.max = max;
        this.min = min;
        this.morn = morn;
        this.eve = eve;
        this.night = night;

    }


}
