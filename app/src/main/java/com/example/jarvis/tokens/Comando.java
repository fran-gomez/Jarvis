package com.example.jarvis.tokens;

public abstract class Comando  {

    protected String identificador;

    public Comando(String id) {
        identificador = id;
    }

    public abstract String ejecutar();
    public abstract void analizarArgumentos(String[] args);
}
