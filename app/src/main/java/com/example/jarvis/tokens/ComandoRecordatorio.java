package com.example.jarvis.tokens;

public class ComandoRecordatorio extends Comando {

    public ComandoRecordatorio(String id) {
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
