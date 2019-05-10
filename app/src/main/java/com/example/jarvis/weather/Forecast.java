package com.example.jarvis.weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class Forecast extends Thread {

    protected final String apiKey = "&APPID=e8db9e21c8c3494bda08cdc02e4f99f3";

    protected String url;
    protected String reporte;
    protected String idCiudad;

    protected long ultimoPedido;

    public Forecast() {
        url = "http://api.openweathermap.org/data/2.5/weather?";
        reporte = "Datos aun no disponibles, por favor, intente mas tarde";
        ultimoPedido = 0;
    }

    /**
     * Consulta la direccion url correspondiente a la ciudad
     * @return Direccion url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Consulta el reporte mas reciente del clima
     * @return Reporte del clima
     */
    public String getReporte() {
        return reporte;
    }


    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    /**
     * Solicita a la API de openweather.com la informacion sobre el clima en una determinada ciudad
     * en segundo plano. Android fuerza a que todas las solicitudes web se realicen de esta forma para
     * evitar un impacto en el desempeño de la actividad principal
     * @param url Direccion correspondiente a la ciudad
     * @return JSON que contiene todos los datos referentes al clima actual en la ciudad
     */
    public void run() {


        while (true) {
            long pedidoActual = System.currentTimeMillis();

            if ((pedidoActual - ultimoPedido) / 1000 >= 3600) {
                reporte = obtenerReporte(url);
                ultimoPedido = pedidoActual;
            } else
                try {
                    sleep(pedidoActual - ultimoPedido / 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    private String obtenerReporte(String url) {
        try {
            URL direccionApi = new URL(url);
            BufferedReader rd = new BufferedReader(new InputStreamReader(direccionApi.openStream()));

            String line, webData = "";
            while ((line = rd.readLine()) != null)
                webData += line;

            rd.close();

            if (webData.length() > 0)
                return armarReporte(webData);
            else
                return  "Error al obtener el reporte en linea";
        } catch (MalformedURLException e) {
            return  "Direccion U R L mal formada. Por favor, contacte al administrador";
        } catch (IOException e) {
            return  "Error de lectura escritura en la web";
        }
    }


    /**
     * Analiza el objeto JSON para armar el reporte del clima
     * @param jsonObject JSON que contiene los datos referentes al clima actual en la ciudad
     * @return Reporte completo del clima actual en la ciudad
     */
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
            return "Campo argumento no encontrado, intente nuevamente mas tarde";
        }
    }
}
