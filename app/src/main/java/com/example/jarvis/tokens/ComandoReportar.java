package com.example.jarvis.tokens;

import java.util.List;

public class ComandoReportar extends Comando {

    public ComandoReportar(String id) {
        super(id);
    }

    @Override
    public String ejecutar() {
        return null;
    }

    @Override
    public List<Argumento> analizarArgumentos(String[] args) {
        return null;
    }
}
