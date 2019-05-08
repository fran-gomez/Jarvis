package com.example.jarvis.tokens;

public class ComandoLlamar extends Comando {

    public ComandoLlamar(String id) {
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
