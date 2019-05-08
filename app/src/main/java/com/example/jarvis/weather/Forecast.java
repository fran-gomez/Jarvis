package com.example.jarvis.weather;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class Forecast extends AsyncTask<String, Void, String> {

    protected final String apiKey = "&APPID=e8db9e21c8c3494bda08cdc02e4f99f3";

    protected String url;
    protected String reporte;

    public Forecast() {
        url = "http://api.openweathermap.org/data/2.5/weather?";
        reporte = "";
    }

    public String getUrl() {
        return url;
    }
    public String getReporte() {
        return reporte;
    }

    protected String doInBackground(String... url) {
        assert (url.length == 1);

        try {
            URL direccionApi = new URL(url[0]);
            BufferedReader rd = new BufferedReader(new InputStreamReader(direccionApi.openStream()));

            String line, reporte = "";
            while ((line = rd.readLine()) != null)
                reporte += line;

            rd.close();

            return reporte;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error";
    }

    protected void onPostExecute(String aString) {
        super.onPostExecute(aString);

        if (aString.length() > 0)
            reporte = armarReporte(aString);
        else
            reporte = "Error obteniendo el reporte";
    }

    private String armarReporte(String jsonObject) {
        try {
            String reporte;
            JSONObject objeto = new JSONObject(jsonObject);
            JSONObject mainObj = objeto.getJSONObject("main");

            String temp = mainObj.getString("temp");
            String max = mainObj.getString("temp_max");
            String min = mainObj.getString("temp_min");
            String humedad = mainObj.getString("humidity");
            String presion = mainObj.getString("pressure");

            reporte = "La temperatura actual es de " + temp.substring(0, 2) + " grados. La maxima pronotsicada es de " +
                        max.substring(0, 2) + " grados y la minima es de " + min.substring(0, 2) + " grados. La humedad actual es del " +
                        humedad + " porciento y la presion de " + presion + " hectopascales.";

            return reporte;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "Error";
    }
}
