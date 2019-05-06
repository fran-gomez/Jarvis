package com.example.jarvis.tokens;

import java.util.List;

public abstract class Comando extends Token {

    public Comando(String id) {
        super(id);
    }

    public abstract void ejecutar(List<Token> argumentos);
    public abstract List<Token> analizarArgumentos(String[] args);
}
