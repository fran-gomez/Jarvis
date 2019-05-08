package com.example.jarvis.tokens;

import android.content.Context;
import com.example.jarvis.events.RecordatoriosOrdenados;
import com.example.jarvis.events.Reminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ComandoRecordatorio implements Comando {

    private Map<String, String> meses;

    protected Reminder misRecordatorios;

    protected String identificador;
    protected String descripcion;
    protected Date fecha;

    public ComandoRecordatorio(String id) {
        identificador = id;
        descripcion = "";
        fecha = null;

        meses = mapeoMeses();

        misRecordatorios = RecordatoriosOrdenados.getInstancia();
    }


    public String ejecutar(Context contexto) {

        if (misRecordatorios.agregarEvento(descripcion, fecha) == null)
            return "Error agregando el evento";
        else
            return "Evento agendado";

    }

    public void analizarArgumentos(String[] args) {
            int idx = armarDescripcion(args);
            armarFecha(args, idx);
    }

    /**
     * Arma la descripcion del recordatorio
     * @param args Argumentos ingresados por el usuario
     * @return Indice donde termina la descripcion
     */
    private int armarDescripcion(String[] args) {

        int i = 1;
        boolean seguir = true;

        while (i < args.length-2 && seguir) {
            if (args[i].equals("el") && esDia(args[i + 1]))
                seguir = false;
            else {
                descripcion += args[i] + " ";
                i++;
            }
        }

        return i+1;
    }

    /**
     * Arma la fecha del recordatorio
     * del argumento
     * @param args Lista completa de argumento
     *             TODO Ojo con los index out of bounds
     */
    private void armarFecha(String[] args, int idx) {
        assert (args.length-idx >= 5);

        String fecha = "";

        fecha += args[idx++] + " "; // Dia
        if (!args[idx++].equals("de"))
            fecha = "Error";
        else {
            fecha += meses.get(args[idx++].toLowerCase()) +" "; // Mes

            // Hora y minutos
            if (args[idx].equals("a") && args[idx + 1].equals("las")) {
                idx += 2;
                String hhmm[] = args[idx].split(":");
                fecha += hhmm[0] + " " + hhmm[1];
            }
        }

        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd MM HH mm");
            this.fecha = fmt.parse(fecha);
        } catch (ParseException e) {
            this.fecha = null;
            descripcion = "";
        }
    }

    private boolean esDia(String s) {

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9')
                return false;
        }

        return Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 31;
    }

    private Map<String, String> mapeoMeses() {

        Map<String, String> meses = new HashMap<>();

        meses.put("enero", "01");
        meses.put("febrero", "02");
        meses.put("marzo", "03");
        meses.put("abril", "04");
        meses.put("mayo", "05");
        meses.put("junio", "06");
        meses.put("julio", "07");
        meses.put("agosto", "08");
        meses.put("septiembre", "09");
        meses.put("octubre", "10");
        meses.put("noviembre", "11");
        meses.put("diciembre", "12");

        return meses;
    }
}
