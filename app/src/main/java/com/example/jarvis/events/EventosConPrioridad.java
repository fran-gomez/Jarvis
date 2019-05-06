package com.example.jarvis.events;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class EventosConPrioridad extends Handler {

    private static final String rutaArchivo = "";

    protected Queue<Evento> misEventos;

    public EventosConPrioridad() {
        misEventos = new PriorityQueue<>(new ComparadorEvento<>());
    }

    public Evento obtenerProximoEvento() {
        if (misEventos.size() > 0)
            return misEventos.element();

        return null;
    }

    public void eventoCompletado(Evento e) {
        misEventos.remove(e);
    }

    public Evento agregarEvento(String[] descripcion) {

        Evento nuevo = new Evento(descripcion[0], descripcion[1]);
        misEventos.add(nuevo);

        return nuevo;
    }

    public Evento editarEvento(CharSequence nuevaDescr) {
        return null;
    }

    public Iterator<Evento> obtenerEventos() {
        return misEventos.iterator();
    }
}
