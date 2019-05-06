package com.example.jarvis.tokens;

import com.example.jarvis.weather.Forecast;

import java.util.LinkedList;
import java.util.List;

public class TokenReporte extends Comando {

    protected Forecast srvReporte;

    public TokenReporte(String id) {
        super(id);
        srvReporte = null;
    }

    @Override
    public void ejecutar(List<Token> argumentos) {

    }

    @Override
    public List<Token> analizarArgumentos(String[] args) {
        List<Token> argumentos = new LinkedList<>();

        return argumentos;
    }
}
