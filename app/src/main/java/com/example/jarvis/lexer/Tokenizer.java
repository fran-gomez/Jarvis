package com.example.jarvis.lexer;

import com.example.jarvis.tokens.Token;

import java.util.List;

public abstract class Tokenizer {

    /**
     * @brief Convierte una cadena de texto en una lista de tokens interpretable por el selector
     * @param spokenInput Entrada ingresada por la voz del usuario
     * @return Lista de tokens con formato [ Comando, argumentos... ]
     */
    public abstract List<Token> tokenize(String spokenInput);
}
