package com.example.jarvis.comands;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.provider.AlarmClock;

import java.util.Date;

public class ComandoAlarma extends ContextWrapper implements Comando {

    protected Date horaAlarma;

    public ComandoAlarma(Context contexto) {
        super(contexto);
        horaAlarma = new Date();
    }

    @Override
    public String ejecutar() {

        Intent alarma = new Intent(AlarmClock.ACTION_SET_ALARM);
        alarma.putExtra(AlarmClock.EXTRA_HOUR, horaAlarma.getHours());
        alarma.putExtra(AlarmClock.EXTRA_MINUTES, horaAlarma.getMinutes());
        startActivity(alarma);

        return "Alarma establecida";
    }

    @Override
    public void analizarArgumentos(String[] args) throws InvalidFormatException {
        assert (args.length == 4);

        if (!args[1].equals("a") && !args[2].equals("las"))
            throw new InvalidFormatException("Esperaba un horario y recibi cualquier cosa");

        String horario[] = args[3].split(":");
        if (horario.length != 2)
            throw new InvalidFormatException("Error en el formato del horario");

        horaAlarma.setHours(Integer.parseInt(horario[0]));
        horaAlarma.setMinutes(Integer.parseInt(horario[1]));
    }
}
