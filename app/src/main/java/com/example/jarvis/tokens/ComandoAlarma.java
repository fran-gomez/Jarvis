package com.example.jarvis.tokens;

public class ComandoAlarma extends Comando {

    public ComandoAlarma(String id) {
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
