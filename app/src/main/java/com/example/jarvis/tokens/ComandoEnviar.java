package com.example.jarvis.tokens;

import android.content.Context;
import android.content.Intent;

public class ComandoEnviar implements Comando {

    protected String identificador;

    protected String nombreContacto;
    protected String numeroContacto;
    protected String cuerpoMsj;

    public ComandoEnviar(String id) {
        identificador = id;
    }

    @Override
    public String ejecutar(Context contexto) {

        Intent enviar = new Intent(Intent.ACTION_SENDTO);
        enviar.putExtra("jid", numeroContacto+"@s.whatsapp.net");
        enviar.putExtra(Intent.EXTRA_TEXT, cuerpoMsj);
        enviar.setPackage("com.whatsapp");
        enviar.setType("text/plain");
        contexto.startActivity(enviar);

        return identificador;
    }

    public void analizarArgumentos(String[] args) {
        assert (args.length > 4);

        int i;

        // Obtengo los datos del contacto
        if (args[2].equals("a")) {
            if (esCelValido(args[2]))
                numeroContacto = args[3];
            else {
                nombreContacto = args[3];
                // Buscar contacto en la agenda del telefono
            }
        }

        // Recupero el cuerpo del mensaje
        i = 4;
        while (i < args.length)
            cuerpoMsj += cuerpoMsj + args[i] + " ";
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
