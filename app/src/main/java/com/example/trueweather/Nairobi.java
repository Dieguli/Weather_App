package com.example.trueweather;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trueweather.HTTPConnection.Connection;
import com.example.trueweather.HTTPData.Data;
import com.example.trueweather.WeatherInformation.OpenWeatherInformation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

public class Nairobi extends AppCompatActivity {

    String latnai = "-1.28333";
    String lonnai = "36.833328";
    OpenWeatherInformation info;
    TextView temp, maxtemp,mintmep,humidity,descriptionnai;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nairobi);

        info = new OpenWeatherInformation();

        temp = findViewById(R.id.temp);
        maxtemp = findViewById(R.id.maxtemp);
        mintmep = findViewById(R.id.mintemp);
        humidity = findViewById(R.id.humidity);
        descriptionnai = findViewById(R.id.descriptionnai);
        imageView = findViewById(R.id.imageView);

        new GetWeather().execute(Connection.apiInfoOne(latnai,lonnai));

    }

    private class GetWeather extends AsyncTask<String ,Void,String> {

        ProgressDialog mProgressDialog = new ProgressDialog(Nairobi.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.setTitle("Please wait...");
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String  data  = null;
            String urlString = strings[0];

            Data http = new Data();
            data = http.getHTTPData(urlString);


            return data;

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if((s.contains("Error: Not found city"))){
                mProgressDialog.dismiss();
                return;
            }
            Gson gson = new Gson();
            Type mType = new TypeToken<OpenWeatherInformation>(){}.getType();
            info = gson.fromJson(s,mType);
            mProgressDialog.dismiss();

            temp.setText(String.format("%.2f °C", info.getMain().getTemp()));
            humidity.setText(String.format("%d%%",info.getMain().getHumidity()));
            mintmep.setText(String.format("%.2f °C",info.getMain().getTemp_min()));
            maxtemp.setText(String.format("%.2f °C",info.getMain().getTemp_max()));
            descriptionnai.setText(String.format("%s",info.getWeather().get(0).getDescription()));
            Picasso.with(Nairobi.this)
                    .load(Connection.getImage(info.getWeather().get(0).getIcon()))
                    .into(imageView);


        }





    }
}
