package com.example.jarvis.lexer;

import com.example.jarvis.comands.Comando;

public abstract class Tokenizer {

    /**
     * @brief Convierte una cadena de texto en una lista de tokens interpretable por el selector
     * @param spokenInput Entrada ingresada por la voz del usuario
     * @return Comando a ejecutar
     */
    public abstract Comando analize(String spokenInput);
}
