package com.example.jarvis.tokens;

public class ComandoReproducir extends Comando {

    public ComandoReproducir(String id) {
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
