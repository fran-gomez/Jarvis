package com.example.jarvis.comands;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class ComandoLlamar extends ContextWrapper implements Comando {

    protected String nombreContacto;
    protected String numeroContacto;

    public ComandoLlamar(Context contexto) {
        super(contexto);
        nombreContacto = "";
        numeroContacto = "";
    }

    public String ejecutar() {

        Intent llamada = new Intent(Intent.ACTION_CALL);
        llamada.setData(Uri.parse("tel:" + numeroContacto));
        llamada.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        nombreContacto = numeroContacto = "";
        startActivity(llamada);

        return "Llamando";
    }


    // TODO Ver por que no me toma los nombres de contacto compuestos
    public void analizarArgumentos(String[] args) throws InvalidFormatException {
        assert (nombreContacto.equals(""));
        assert (numeroContacto.equals(""));
        assert (args.length >= 3);

        // Comando de la forma llamar a <contacto>
        if (!args[1].equals("a"))
            throw new InvalidFormatException("Esperaba un contacto y recibi cualquier cosa");

        // Armo el nombre del contacto
        for (int i = 2; i < args.length-1; i++)
            nombreContacto += args[i] + " ";
        nombreContacto += args[args.length-1];

        // Busco el numero del contacto en la agenda del celu
        numeroContacto = buscarContacto(nombreContacto);
        if (numeroContacto.equals("")) {
            nombreContacto = "";
            throw new InvalidFormatException("Contacto no encontrado");
        }
    }

    private String buscarContacto(String nombreContacto) {
        // Buscar el contacto por nombre
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                "DISPLAY_NAME = '" + nombreContacto + "'", null, null);

        if (cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            // Obtener todos los numeros de telefono
            Cursor numeros = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
            while (numeros.moveToNext()) {
                String numero = numeros.getString(numeros.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                int type = numeros.getInt(numeros.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                if (type == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
                    cursor.close();
                    numeros.close();
                    return numero;
                }
            }
            numeros.close();
        }
        cursor.close();

        return "";
    }
}
