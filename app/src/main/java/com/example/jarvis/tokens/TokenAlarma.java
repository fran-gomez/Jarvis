package com.example.jarvis.tokens;

import java.util.LinkedList;
import java.util.List;

public class TokenAlarma extends Comando {
    public TokenAlarma(String id) {
        super(id);
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
