package com.example.jarvis.comands;

import android.content.Context;
import com.example.jarvis.events.Evento;
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

        misRecordatorios = new RecordatoriosOrdenados();
        misRecordatorios.start();
    }


    public String ejecutar(Context contexto) {

        Evento nuevo = misRecordatorios.agregarEvento(descripcion, fecha);

        if (nuevo == null)
            return "Error agregando el recordatorio";
        else
            return "Recordatorio agendado";

    }

    public void analizarArgumentos(String[] args) throws InvalidFormatException {
            int idx = armarDescripcion(args);
            armarFecha(args, idx);
    }

    /**
     * Arma la descripcion del recordatorio
     * @param args Argumentos ingresados por el usuario
     * @return Indice donde termina la descripcion
     */
    private int armarDescripcion(String[] args) throws InvalidFormatException {
        if (args.length < 9)
            throw new InvalidFormatException("Formato de orden invalido");

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
     * Arma la fecha del recordatorio, extrayendola del argumento
     * Se espera que la fecha venga en formato <dd de mm a las HH:MM>
     * @param args Lista completa de argumentos
     *             TODO Ojo con los index out of bounds
     */
    private void armarFecha(String[] args, int idx) throws InvalidFormatException {
        if (args.length-idx < 6)
            throw new InvalidFormatException("Formato de fecha invalido");

        String fecha = "";
        String mes;

        fecha += args[idx++] + " "; // Dia
        mes = meses.get(args[idx+1]);
        if (!args[idx].equals("de") || mes == null)
            throw new InvalidFormatException("Error. Eperaba un mes y obtuve otra cosa");
        fecha += mes + " "; // Mes

        idx += 2;
        if (!args[idx].equals("a") && !args[idx+1].equals("las"))
            throw new InvalidFormatException("Error. Esperaba un horario y obtuve otra cosa");

        // Hora y minutos
        idx += 2;
        fecha += args[idx];

        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd MM HH:mm");
            this.fecha = fmt.parse(fecha);
        } catch (ParseException e) {
            throw new InvalidFormatException("Error. Horario invalido");
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
