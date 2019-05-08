package com.example.jarvis.tokens;

public class ComandoEnviar extends Comando {

    public ComandoEnviar(String id) {
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
