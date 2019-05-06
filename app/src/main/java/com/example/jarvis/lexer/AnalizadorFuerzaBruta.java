package com.example.jarvis.lexer;

import com.example.jarvis.tokens.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnalizadorFuerzaBruta extends Tokenizer {

    protected Map<String, Token> misObjetos;

    public AnalizadorFuerzaBruta() {
        misObjetos = new HashMap<>();

        // Agregamos un token por cada comando registrado
        misObjetos.put("recu\u00e9rdame", new TokenRecordatorio("recu\u00e9rdame"));
        misObjetos.put("despi\u00e9rtame", new TokenAlarma("despi\u00e9rtame"));
        misObjetos.put("llamar", new TokenLlamada("llamar"));
        misObjetos.put("enviar", new TokenMensaje("enviar"));
        misObjetos.put("reproducir", new TokenReproduccion("reproducir"));
        misObjetos.put("reportame", new TokenReporte("reportame"));
    }

    public List<Token> tokenize(String spokenInput) {
        List<Token> listaTokens = new LinkedList<>();
        String[] palabras = spokenInput.split(" ");

        Comando comando = (Comando) misObjetos.get(palabras[0].toLowerCase());
        if (comando == null)
            // Pongo un token de error con el mensaje
            listaTokens.add(new TokenError("Lo lamento señor, no encuentro el comando que me esta pidiendo"));
        else {
            listaTokens.add(comando);
            listaTokens.addAll(comando.analizarArgumentos(palabras));
        }

        return listaTokens;
    }
}
