package com.example.jarvis.events;

import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class RecordatoriosOrdenados extends Reminder {

    protected Queue<Evento> misEventos;

    private RecordatoriosOrdenados() {
        misEventos = new PriorityQueue<>(new ComparadorEvento<>());
    }

    public Evento obtenerProximoEvento() {
        assert (misEventos.size() > 0);

        return misEventos.peek();
    }

    public void eventoCompletado(Evento e) {
        misEventos.remove(e);
    }

    public Evento agregarEvento(String descripcion, Date fecha) {

        Evento nuevo = new Evento(descripcion, fecha);
        if (misEventos.add(nuevo))
            return nuevo;
        else
            return null;
    }

    public Evento editarEvento(CharSequence nuevaDescr) {
        return null;
    }

    public Iterator<Evento> obtenerEventos() {
        return misEventos.iterator();
    }

    public static Reminder getInstancia() {
        if (instancia == null)
            instancia = new RecordatoriosOrdenados();

        return instancia;
    }
}
