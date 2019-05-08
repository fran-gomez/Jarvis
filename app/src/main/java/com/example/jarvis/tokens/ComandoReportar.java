package com.example.jarvis.tokens;

import com.example.jarvis.weather.Forecast;
import com.example.jarvis.weather.ReporteCiudad;

public class ComandoReportar extends Comando {

    protected Forecast reporte;
    protected String ciudad;

    private long ultimaSolicitud;

    public ComandoReportar(String id) {
        super(id);
        ciudad = "Bahia Blanca";
        reporte = new ReporteCiudad(ciudad);
        ultimaSolicitud = 0;

        ejecutar();
    }

    public String ejecutar() {

        long nuevaSolicitud = System.currentTimeMillis();
        // Pido el reporte al servidor una vez cada 60 minutos como maximo
        if ((nuevaSolicitud - ultimaSolicitud) / 1000 > 3600) {
            reporte.execute(reporte.getUrl());
            ultimaSolicitud = nuevaSolicitud;
        }

        return reporte.getReporte() ;
    }

    public void analizarArgumentos(String[] args) {

        if (args.length > 3)
            if (args[4].equals("en")) {
                ciudad = "";
                for (int i = 5; i < args.length; i++)
                    ciudad += args[i] + " ";
                ultimaSolicitud = 0;
            }
    }
}
