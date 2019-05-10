package com.example.jarvis.comands;

import com.example.jarvis.weather.Forecast;
import com.example.jarvis.weather.ReporteCiudad;

public class ComandoReportar implements Comando {

    protected Forecast reporte;
    protected String ciudad;


    public ComandoReportar() {
        ciudad = "Bahia Blanca";
        reporte = new ReporteCiudad(ciudad);

        reporte.start();
    }

    public String ejecutar() {

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
    }
}
