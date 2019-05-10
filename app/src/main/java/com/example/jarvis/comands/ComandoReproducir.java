package com.example.jarvis.comands;

import android.content.Context;
import android.content.ContextWrapper;

public class ComandoReproducir extends ContextWrapper implements Comando {

    public ComandoReproducir(Context contexto) {
        super(contexto);
    }

    @Override
    public String ejecutar() {
        return "reproducir";
    }

    @Override
    public void analizarArgumentos(String[] args) {
    }
}
