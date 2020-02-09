package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText city_name;
    String d= (new SimpleDateFormat("dd MMMM yyyy")).format(new Date());
    String city;
RequestQueue rq;
 TextView txt;
 TextView wind_speed;
 TextView pressure;
 TextView min_temp;
    TextView max_temp;
 TextView temp;
 TextView place;
 TextView date;

 String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        city="";
city_name=findViewById(R.id.editText);
txt=findViewById(R.id.txtview);
    }

    public void findClimate(View view) {
          city= city_name.getText().toString();

           url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=06693c137fa8e258e44419044f88baf9";

            rq= Volley.newRequestQueue(this);
        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   String temp=response.getJSONObject("main").getString("temp");
                    String temp_max=response.getJSONObject("main").getString("temp_max");
                    String temp_min=response.getJSONObject("main").getString("temp_min");
                    String pressur=response.getJSONObject("main").getString("pressure");
                    String wind_speed=response.getJSONObject("wind").getString("speed");

                    String format=("place :"+city+"\n\n"+ " maximum temperature :"+temp_max+"°C"+"\n\n" +"minimum temperature :"+temp_min+"K"+"\n\n"+
                    "temperature :"+temp+"°C"+"\n\n"+ "pressure :"+pressur+"hpa"+"\n\n" +"wind speed :"+wind_speed+"Km/hr"+"\n\n"+
                    "date:"+d);
                    txt.setText(format);


                }
              catch(Exception e)
              {
                  Toast.makeText(MainActivity.this,"object error",Toast.LENGTH_SHORT).show();
              }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"volley error" ,Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jor);

    }


}
