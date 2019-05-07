package com.example.jarvis.tokens;

import java.util.List;

public class ComandoAlarma extends Comando {

    public ComandoAlarma(String id) {
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
