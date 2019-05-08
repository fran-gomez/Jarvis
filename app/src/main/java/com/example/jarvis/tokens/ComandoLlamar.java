package com.example.jarvis.tokens;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telecom.Call;

public class ComandoLlamar implements Comando {

    protected String identificador;
    protected String nombreContacto;
    protected String numeroContacto;

    public ComandoLlamar(String id) {
        numeroContacto = "5492914311274";
        identificador = id;
    }

    public String ejecutar(Context contexto) {

        Intent llamada = new Intent(contexto, Call.class);
        llamada.setData(Uri.parse(numeroContacto));
        contexto.startActivity(llamada);

        return "Llamada finalizada";
    }


    public void analizarArgumentos(String[] args) {
        assert (args.length >= 3);

        // Comando de la forma llamar a <contacto>
        if (args[1].equals("a")) {
            if (esCelValido(args[2]))
                numeroContacto = args[2];
            else {
                nombreContacto = args[2];
                // Buscar contacto en la agenda del telefono
            }
        }
    }

    private boolean esCelValido(String numCel) {

        int i = 0;

        while (i < numCel.length())
            if (numCel.charAt(i) <= '0' || numCel.charAt(i) >= '9')
                return false;
            else
                i++;

        return true;
    }
}
