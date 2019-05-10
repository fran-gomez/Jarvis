package com.example.jarvis.comands;

public class ComandoError implements Comando {

    protected String errorMsg;

    public ComandoError(String id) {
        errorMsg = id;
    }

    @Override
    public String ejecutar() {
        return errorMsg;
    }

    @Override
    public void analizarArgumentos(String[] args) {
    }
}
