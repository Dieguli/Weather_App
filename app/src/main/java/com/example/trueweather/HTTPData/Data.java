package com.example.trueweather.HTTPData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Data {

    static String data = null;

    public String getHTTPData(String urlString){
        try {
            URL url = new URL(urlString);
            HttpURLConnection mhttpURLConnection = (HttpURLConnection)url.openConnection();
            if(mhttpURLConnection.getResponseCode() == 200) // OK == 200 / Unauthorized == 401
            {
                BufferedReader mBuffferedReader = new BufferedReader(new InputStreamReader(mhttpURLConnection.getInputStream()));
                StringBuilder mStringBuilder = new StringBuilder();
                String line;
                while((line = mBuffferedReader.readLine())!=null)
                    mStringBuilder.append(line);
                data = mStringBuilder.toString();
                mhttpURLConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  data;
    }

}
