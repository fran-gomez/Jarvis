package com.example.jarvis.events;

import java.util.Date;

public class Evento {

    protected CharSequence descripcion;
    protected Date fecha;

    public Evento(String descripcion, Date fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public String toString() {
        return descripcion + " " +
                fecha.toString();
    }
}
