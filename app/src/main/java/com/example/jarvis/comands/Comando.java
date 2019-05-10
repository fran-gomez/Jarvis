package com.example.jarvis.comands;

public interface Comando  {

    /**
     * Ejecuta la accion correspondiente al comando
     * @return Cadena con el estado final del comando
     */
    String ejecutar();

    /**
     * Analiza la entrada provista por el usuario en busca de los
     * argumentos necesarios para ejecutar el comando
     * @param args Conjunto de palabras que contiene los argumentos del comando
     */
    void analizarArgumentos(String[] args) throws InvalidFormatException;
}
