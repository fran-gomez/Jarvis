package com.example.jarvis.lexer;

import com.example.jarvis.comands.*;

import java.util.HashMap;
import java.util.Map;

public class AnalizadorFuerzaBruta extends Tokenizer {

    protected Map<String, Comando> misObjetos;

    public AnalizadorFuerzaBruta() {
        misObjetos = new HashMap<>();

        // Agregamos un token por cada comando registrado
        misObjetos.put("recu\u00e9rdame", new ComandoRecordatorio("recu\u00e9rdame"));
        misObjetos.put("despi\u00e9rtame", new ComandoAlarma("despi\u00e9rtame"));
        misObjetos.put("llamar", new ComandoLlamar("llamar"));
        misObjetos.put("enviar", new ComandoEnviar("enviar"));
        misObjetos.put("reproducir", new ComandoReproducir("reproducir"));
        misObjetos.put("reportame", new ComandoReportar("reportame"));
    }

    public Comando analize(String spokenInput) {
        String[] palabras = spokenInput.split(" ");

        Comando comando = misObjetos.get(palabras[0].toLowerCase());
        if (comando == null)
            comando = new ComandoError("Lo lamento señor, no encuentro el comando que me esta pidiendo");
        else
            try {
                comando.analizarArgumentos(palabras);
            } catch (InvalidFormatException e) {
                comando = new ComandoError(e.getMessage());
            }

        return comando;
    }
}
