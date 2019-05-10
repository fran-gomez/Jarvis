package com.example.jarvis.lexer;

import android.content.Context;
import com.example.jarvis.comands.*;

import java.util.HashMap;
import java.util.Map;

public class AnalizadorFuerzaBruta extends Tokenizer {

    protected Map<String, Comando> misObjetos;

    public AnalizadorFuerzaBruta(Context contexto) {
        misObjetos = new HashMap<>();

        // Agregamos un token por cada comando registrado
        misObjetos.put("recu\u00e9rdame", new ComandoRecordatorio(contexto));
        misObjetos.put("despi\u00e9rtame", new ComandoAlarma(contexto));
        misObjetos.put("llamar", new ComandoLlamar(contexto));
        misObjetos.put("enviar", new ComandoEnviar(contexto));
        misObjetos.put("reproducir", new ComandoReproducir(contexto));
        misObjetos.put("reportame", new ComandoReportar());
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
