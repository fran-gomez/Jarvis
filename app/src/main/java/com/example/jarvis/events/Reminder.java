package com.example.jarvis.events;

import java.util.Date;
import java.util.Iterator;

public abstract class Reminder {

    protected static Reminder instancia;

    /**
     * @brief Obtiene el recordatorio mas cercano en el tiempo
     * @return Recordatorio
     */
    public abstract Evento obtenerProximoEvento();

    /**
     * @brief Elimina un descripcion que ya fue completado
     * @param e Evento a eliminar
     */
    public abstract void eventoCompletado(Evento e);

    /**
     * @brief Recibe la descripcion de un descripcion ingresado por el usuario y lo agrega a la
     *        cola de eventos
     * @param descripcion Descripcion del recordatorio
     * @param fecha Fecha del recordatorio (Dia, mes, hora y minutos)
     * @return Evento ingresado por el usuario
     * */
    public abstract Evento agregarEvento(String descripcion, Date fecha);

    /**
     * @brief Modifica un descripcion existente
     * @param nuevaDescr Nueva info del descripcion
     * @return Evento modificado
     */
    public abstract Evento editarEvento(CharSequence nuevaDescr);

    /**
     * @brief Devuelve un iterador de eventos, ordenados por fecha
     * @return Iterador de eventos
     */
    public abstract Iterator<Evento> obtenerEventos();

}
