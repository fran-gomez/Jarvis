package com.example.jarvis.tokens;

public class ComandoError extends Comando {
    public ComandoError(String id) {
        super(id);
    }

    @Override
    public String ejecutar() {
        return identificador;
    }

    @Override
    public void analizarArgumentos(String[] args) {
    }
}
