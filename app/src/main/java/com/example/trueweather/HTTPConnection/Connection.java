package com.example.trueweather.HTTPConnection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Connection {

    public static String KEY = "8e90ac6997b4843ad50b78236f2a540e";
    public static String LINK = "http://api.openweathermap.org/data/2.5/weather";



    //Obtain weather info for just one location
    public static String apiInfoOne(String lat, String lng){
        StringBuilder mStringBuilder = new StringBuilder(LINK);
        mStringBuilder.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat,lng,KEY));
        return mStringBuilder.toString();
    }



    //Obtain weather info for several locations
    public static String[] apiInfoThree(String lat1, String lng1, String lat2, String lng2, String lat3, String lng3){
        StringBuilder mStringBuilder1 = new StringBuilder(LINK);
        StringBuilder mStringBuilder2 = new StringBuilder(LINK);
        StringBuilder mStringBuilder3 = new StringBuilder(LINK);
        String [] result = new String[3];
        mStringBuilder1.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat1,lng1,KEY));
        mStringBuilder2.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat2,lng2,KEY));
        mStringBuilder3.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat3,lng3,KEY));
        result[0]= mStringBuilder1.toString();
        result[1]= mStringBuilder2.toString();
        result[2]= mStringBuilder3.toString();
        return result;
    }

    //Obtain weather image.
    public static String getImage(String icon){
        return String.format("http://openweathermap.org/img/w/%s.png",icon);
    }
    public static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }





}
