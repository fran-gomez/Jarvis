package com.example.jarvis.tokens;

import java.util.List;

public abstract class Comando  {

    protected String identificador;

    public Comando(String id) {
        identificador = id;
    }

    public abstract String ejecutar();
    public abstract List<Argumento> analizarArgumentos(String[] args);

    public String toString() {
        return identificador;
    }
}
