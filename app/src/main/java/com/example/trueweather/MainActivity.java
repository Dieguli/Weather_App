package com.example.trueweather;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trueweather.HTTPConnection.Connection;
import com.example.trueweather.HTTPData.Data;
import com.example.trueweather.WeatherInformation.OpenWeatherInformation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class MainActivity extends AppCompatActivity {


    // Latitudes and Longitudes of the different locations
    String latstck = "59.32478";
    String lonstck = "18.06427";
    String latban = "12.97623";
    String lonban= "77.603287";
    String latnai = "-1.28333";
    String lonnai = "36.833328";


    // Variables used to request permissions
    private static final int PERMISSION_REQUEST_INTERNET= 456;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 457;
    private static final int PERMISSION_REQUEST_NETWORK_STATE = 458;
    private static final int PERMISSION_REQUEST_SYSTEM_ALERT_WINDOW= 459;
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 460;

    // TextViews were the current temperatures of the locations will be displayed
    TextView tempstock, tempban, tempnai;
    ImageView stockholm, bangalore, nairobi;


    OpenWeatherInformation[] info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempstock = findViewById(R.id.tempstockholm);
        tempban = findViewById(R.id.tempbangalore);
        tempnai = findViewById(R.id.tempnairobi);

        stockholm = findViewById(R.id.stock);
        bangalore = findViewById(R.id.ben);
        nairobi = findViewById(R.id.nai);

        info = new OpenWeatherInformation[3];

        requestPermissions( new String[]{
                Manifest.permission.INTERNET}, PERMISSION_REQUEST_INTERNET);
        requestPermissions( new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        requestPermissions( new String[]{
                Manifest.permission.ACCESS_NETWORK_STATE}, PERMISSION_REQUEST_NETWORK_STATE);
        requestPermissions( new String[]{
                Manifest.permission.SYSTEM_ALERT_WINDOW}, PERMISSION_REQUEST_SYSTEM_ALERT_WINDOW);
        requestPermissions( new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);

        new GetTemperatures().execute(Connection.apiInfoThree(latstck,lonstck, latban, lonban,latnai, lonnai));

        stockholm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Stockholm.class));
            }
        });

       bangalore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Bangalore.class));
            }
        });
        nairobi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Nairobi.class));
            }
        });


    }

    private class GetTemperatures extends AsyncTask<String [],Void,String[]> {

        ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.setTitle("Please wait...");
            mProgressDialog.show();
        }

        @Override
        protected String[] doInBackground(String[]... strings) {

            String [] data  = new String[3];
            String urlString = strings[0][0];

            Data http = new Data();
            data[0] = http.getHTTPData(urlString);

            String urlString2 = strings[0][1];

            Data http2 = new Data();
            data[1] = http2.getHTTPData(urlString2);

            String urlString3 = strings[0][2];

            Data http3 = new Data();
            data[2] = http3.getHTTPData(urlString3);

            return data;

        }


        @Override
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);
            if((s[0].contains("Error: Not found city"))||(s[1].contains("Error: Not found city"))||(s[2].contains("Error: Not found city"))){
                mProgressDialog.dismiss();
                return;
            }
            Gson gson = new Gson();
            Type mType = new TypeToken<OpenWeatherInformation>(){}.getType();
            info[0] = gson.fromJson(s[0],mType);
            info[1] = gson.fromJson(s[1],mType);
            info[2] = gson.fromJson(s[2],mType);
            mProgressDialog.dismiss();

            tempstock.setText(String.format("%.2f °C", info[0].getMain().getTemp()));
            tempban.setText(String.format("%.2f °C", info[1].getMain().getTemp()));
            tempnai.setText(String.format("%.2f °C", info[2].getMain().getTemp()));

        }





    }


}
