package com.example.jarvis;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.example.jarvis.events.EventosConPrioridad;
import com.example.jarvis.events.Handler;
import com.example.jarvis.lexer.AnalizadorFuerzaBruta;
import com.example.jarvis.lexer.Tokenizer;
import com.example.jarvis.tokens.Comando;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech voice;

    private Handler misEventos;
    private Tokenizer miAnalizador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el servidor de texto a voz
        voice = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.SUCCESS)
                System.err.println("No se pudo inicializar la voz de jarvis"); // TODO Corregir
            else
                voice.setLanguage(Locale.getDefault());
        });

        // Inicializar los objetos correspondientes a Jarvis
        misEventos = new EventosConPrioridad();
        miAnalizador = new AnalizadorFuerzaBruta();

        // Saludar al usuario
    }

    public void talk(View view) {
        talk("Funcion aun no implementada");
    }

    public void talk(String words) {

        if (words.length() == 0) {
            voice.speak("Lo siento, no tengo nada para decir", TextToSpeech.QUEUE_FLUSH, null, null);
        } else
            voice.speak(words, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void listen(View view) {

        // Creamos el intent para llamar a la actividad de registro de voz
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); // Lenguaje del sistema

        // Iniciamos la captura de voz
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 10);
        else
            Toast.makeText(this, "Error. No se pudo inicializar la captura de voz", Toast.LENGTH_SHORT).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<String> res;
        Comando comando;
        String str;

        if (requestCode == 10)
            if (resultCode == RESULT_OK && data != null) {
                res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                // Analizamos la cadena de entrada del usuario en busca del comando y sus argumentos
                comando = miAnalizador.analize(res.get(0));


                if ((str = comando.ejecutar()) == null)
                    talk("Comando aun no implementado");
                else
                    talk(str);
            }
    }

    protected void onDestroy() {
        if (voice != null) {
            talk("Hasta luego señor");
            voice.stop();
            voice.shutdown();
        }

        super.onDestroy();
    }
}
