package com.example.jarvis.events;

import java.util.Comparator;
import java.util.Date;

public class ComparadorEvento<E> implements Comparator<E> {

    public int compare(E o1, E o2) {
        Date fechaO1 = ((Evento) o1).getFecha();
        Date fechaO2 = ((Evento) o2).getFecha();

        return fechaO1.compareTo(fechaO2);
    }
}
