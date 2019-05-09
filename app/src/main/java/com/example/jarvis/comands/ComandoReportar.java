package com.example.jarvis.comands;

import android.content.Context;
import com.example.jarvis.weather.Forecast;
import com.example.jarvis.weather.ReporteCiudad;

public class ComandoReportar implements Comando {

    protected String identificador;

    protected Forecast reporte;
    protected String ciudad;

    private long ultimaSolicitud;

    public ComandoReportar(String id) {
        identificador = id;

        ciudad = "Bahia Blanca";
        reporte = new ReporteCiudad(ciudad);
        ultimaSolicitud = 0;

        //ejecutar(null);
    }

    public String ejecutar(Context contexto) {

        return reporte.getReporte();
    }

    public void analizarArgumentos(String[] args) throws InvalidFormatException {
        /*
        // Chequeamos si el usuario quiere cambiar de ciudad
        if (args.length > 4 && args[3].equals("en")) {
            int i = 4;
            String ciudad = "";

            while (i < args.length)
                ciudad += args[i++] + " ";
            reporte.setIdCiudad(ciudad.substring(0, ciudad.length()-2));
        }*/

        if (!args[1].equals("el") && !args[2].equals("clima"))
            throw new InvalidFormatException("Formato de orden invalido");

        long nuevaSolicitud = System.currentTimeMillis();
        // Pido el reporte al servidor una vez cada 60 minutos como maximo
        if ((nuevaSolicitud - ultimaSolicitud) / 1000 > 3600) {
            reporte.execute(reporte.getUrl());
            ultimaSolicitud = nuevaSolicitud;
        }
    }
}
