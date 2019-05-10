package com.example.jarvis.events;

import java.util.Date;

public class Evento {

    protected CharSequence descripcion;
    protected Date fecha;

    public Evento(String descripcion, Date fecha) {
        this.descripcion = descripcion;
        fecha.setYear(119); // Cantidad de años que pasaron desde 1900
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
