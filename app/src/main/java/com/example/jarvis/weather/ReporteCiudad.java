package com.example.jarvis.weather;

import java.util.HashMap;
import java.util.Map;

public class ReporteCiudad extends Forecast {

    protected Map<String, String> ciudades;

    public ReporteCiudad(String ciudad) {
        super();

        ciudades = armarMapeo();

        String idCiudad = ciudades.get(ciudad.toLowerCase());
        if (idCiudad != null)
            url = url + "id="+idCiudad + apiKey + "&units=metric";
        else
            url = "";
    }

    private Map<String, String> armarMapeo() {

        Map<String, String> ciudades = new HashMap<>();

        ciudades.put("bahia blanca", "3865086");
        ciudades.put("mar del plata", "3430863");
        ciudades.put("buenos aires", "6559994");
        ciudades.put("la plata", "3432039");
        ciudades.put("neuqu\u00e9n", "3843123");

        return ciudades;
    }
}
