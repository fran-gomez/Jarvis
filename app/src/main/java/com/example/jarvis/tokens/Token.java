package com.example.jarvis.tokens;

public abstract class Token {

    protected String identificador;

    public Token(String id) {
        identificador = id;
    }

    public String toString() {
        return identificador;
    }
}
