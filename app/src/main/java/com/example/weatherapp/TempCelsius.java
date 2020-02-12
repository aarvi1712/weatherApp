package com.example.weatherapp;

public class TempCelsius {


    public String convert(String temp)
    {
        float i=Float.parseFloat(temp);
        int r=(int)(i-273);
    return(String.valueOf(r));
    }


}
