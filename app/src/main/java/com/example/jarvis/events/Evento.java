package com.example.jarvis.events;

import java.util.Date;

public class Evento {

    protected CharSequence descripcion;
    protected Date fecha;

    public Evento(String descripcion, String fecha) {
        this.descripcion = descripcion;
        ajustarFecha(fecha.split(" "), this.fecha);
    }

    public CharSequence getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    private void ajustarFecha(String[] tokens, Date fecha) {

    }
}
