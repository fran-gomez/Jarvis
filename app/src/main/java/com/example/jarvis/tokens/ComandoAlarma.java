package com.example.jarvis.tokens;

import android.content.Context;

public class ComandoAlarma implements Comando {

    protected String identificador;

    public ComandoAlarma(String id) {
        identificador = id;
    }

    @Override
    public String ejecutar(Context contexto) {
        return identificador;
    }

    @Override
    public void analizarArgumentos(String[] args) {
    }
}
