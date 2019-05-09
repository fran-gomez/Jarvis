package com.example.jarvis.events;

import java.util.Date;
import java.util.Iterator;

public abstract class Reminder extends Thread {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Reminder(String name) {
        super(name);
    }

    /**
     * @brief Recibe la descripcion de un descripcion ingresado por el usuario y lo agrega a la
     *        cola de eventos
     * @param descripcion Descripcion del recordatorio
     * @param fecha Fecha del recordatorio (Dia, mes, hora y minutos)
     * @return Evento ingresado por el usuario
     * */
    public abstract Evento agregarEvento(String descripcion, Date fecha);

    /**
     * @brief Devuelve un iterador de eventos, ordenados por fecha
     * @return Iterador de eventos
     */
    public abstract Iterator<Evento> obtenerEventos();
}
