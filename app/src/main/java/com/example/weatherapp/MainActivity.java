package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import java.util.Locale;

import static com.example.weatherapp.R.menu.menu_items;

public class MainActivity extends AppCompatActivity {
    EditText city_name;
    String d= (new SimpleDateFormat("dd MMMM yyyy")).format(new Date());
    String city;
    RequestQueue rq;

    RelativeLayout relativelayout;
    TextView wind_speed;
    TextView pressuree;
    TextView min_temp;
    TextView max_temp;
    TextView tempp;
    TextView sunrisee;
    TextView sunsett;
    TextView descc;
    TextView date;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        city_name=findViewById(R.id.city_name);
        pressuree=findViewById(R.id.pressure);
        tempp=findViewById(R.id.temp);
        max_temp=findViewById(R.id.maxtemp);
        min_temp=findViewById(R.id.mintemp);
        sunrisee=findViewById(R.id.sunshine);
        sunsett=findViewById(R.id.sunset);
        descc=findViewById(R.id.desc);
        date=findViewById(R.id.date);
        wind_speed=findViewById(R.id.windspeed);

        relativelayout=(RelativeLayout) findViewById(R.id.relativelayoutid);

    }

    public void weatherReport(View view) {
        city= city_name.getText().toString().trim();

        url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=06693c137fa8e258e44419044f88baf9";

        rq= Volley.newRequestQueue(this);
        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String temp=response.getJSONObject("main").getString("temp");
                    String desc=response.getJSONArray("weather").getJSONObject(0).getString("description");
                    String temp_max=response.getJSONObject("main").getString("temp_max");
                    String temp_min=response.getJSONObject("main").getString("temp_min");
                    String pressur=response.getJSONObject("main").getString("pressure");
                    String wind_speedd=response.getJSONObject("wind").getString("speed");
                    Long shuns=   response.getJSONObject("sys").getLong("sunrise");
                    Long shunse=   response.getJSONObject("sys").getLong("sunset");
                    TempCelsius ob=new TempCelsius();
                    String  temperature=ob.convert(temp);
                    String max_tempp=ob.convert(temp_max);
                    String min_tempp=ob.convert(temp_min);
                    tempp.setText( temperature + "°C");
                    max_temp.setText("Max Temp "+max_tempp +"°C");
                    min_temp.setText("Min Temp"+min_tempp+"°C");
                    wind_speed.setText(wind_speedd+" Km/hr");
                    sunrisee.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(shuns * 1000)));
                    sunsett.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(shunse * 1000)));

                    date.setText(d);
                    descc.setText(desc);
                    pressuree.setText(pressur+" hpa");
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this,"no city found",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Wrong city input" ,Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jor);

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(menu_items, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.item1:
                relativelayout=(RelativeLayout) findViewById(R.id.relativelayoutid);


                relativelayout.setBackgroundResource(R.drawable.weather_app);
                return true;
            case R.id.item2:

                relativelayout.setBackgroundResource(R.drawable.cluster_sky);
                return true;
            case R.id.item3:

                relativelayout.setBackgroundResource(R.drawable.forest_background);
                return true;
            case R.id.item4:

                relativelayout.setBackgroundResource(R.drawable.sunset_background);
                return true;
            default: return(super.onOptionsItemSelected(item));
        }
    }



}
