package com.example.jarvis.events;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class RecordatoriosOrdenados extends Reminder {

    private static Reminder instancia;

    protected Queue<Evento> misEventos;
    protected File eventosDeUsuario;
    protected boolean ejecutar;

    private RecordatoriosOrdenados() {
        super("RecordatoriosOrdenados");
        misEventos = new PriorityQueue<>(new ComparadorEvento<>());
        eventosDeUsuario = new File("misEventos.txt");

        // Levanto el archivo que contiene todos los eventos

        // Rearmo la cola de eventos

    }

    @Override
    public Evento agregarEvento(String descripcion, Date fecha) {

        Evento nuevo = new Evento(descripcion, fecha);
        if (nuevo != null) {
            // Meto el evento en el archivo
        }
        misEventos.add(nuevo);

        return nuevo;
    }

    @Override
    public Iterator<Evento> obtenerEventos() {
        return misEventos.iterator();
    }

    public void run() {
        Evento masCercano;

        while (true) {
            masCercano = misEventos.peek();

            if (masCercano == null) {
                try {
                    sleep(60000); // 1 minutos de espera
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            long segundosRestantes = (masCercano.getFecha().getTime() - System.currentTimeMillis()) / 1000;
            if (segundosRestantes >= 0 && segundosRestantes < 3600)
                // Mando una notificacion con el evento
                System.out.println("En la proxima hora hay un evento***************");
            else if (segundosRestantes < 0) {
                // El evento ya paso, lo saco de la cola
                misEventos.poll();
                segundosRestantes = 60; // Espero un minuto hasta pedir el proximo evento
            }

            // Duermo el thread por la mitad del tiempo que resta hasta el proximo evento
            try {
                sleep(segundosRestantes * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Reminder getInstancia() {
        if (instancia == null)
            instancia = new RecordatoriosOrdenados();

        return instancia;
    }
}
