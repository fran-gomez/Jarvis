package com.example.jarvis.comands;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class ComandoEnviar extends ContextWrapper implements Comando {

    protected String nombreContacto;
    protected String numeroContacto;
    protected String cuerpoMsj;

    public ComandoEnviar(Context contexto) {
        super(contexto);
        numeroContacto = "5492914311274";
        cuerpoMsj = "";
    }

    @Override
    public String ejecutar() {

        Intent enviar = new Intent();
        /*enviar.setAction(Intent.ACTION_SEND);
        enviar.putExtra(Intent.EXTRA_TEXT, cuerpoMsj);
        enviar.setPackage("com.whatsapp");
        enviar.setType("text/plain");*/

        enviar.setAction(Intent.ACTION_VIEW);
        enviar.setData(Uri.parse("whatsapp://send?phone=" + numeroContacto + "&text=" + cuerpoMsj));

        startActivity(enviar);


        return "Mensaje enviado";
    }

    // TODO Ver como reconstruir el nombre completo del contacto
    public void analizarArgumentos(String[] args) throws InvalidFormatException {
        assert (args.length > 4);

        if (!args[1].equals("mensaje") || !args[2].equals("a"))
            throw new InvalidFormatException("Esperaba una orden de mensaje y recibi cualquier cosa");

        nombreContacto = args[3];
        numeroContacto = buscarContacto(nombreContacto);

        int i;
        String cuerpo = "";

        // Recupero el cuerpo del mensaje
        i = 4;
        while (i < args.length)
            cuerpo += args[i++] + " ";
        cuerpoMsj = cuerpo;
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
