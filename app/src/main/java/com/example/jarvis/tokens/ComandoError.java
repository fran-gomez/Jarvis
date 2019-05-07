package com.example.jarvis.tokens;

import java.util.List;

public class ComandoError extends Comando {
    public ComandoError(String id) {
        super(id);
    }

    @Override
    public String ejecutar() {
        return "";
    }

    @Override
    public List<Argumento> analizarArgumentos(String[] args) {
        return null;
    }

    public String toString() {
        return identificador;
    }
}
