package com.example.jarvis;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jarvis.events.EventosConPrioridad;
import com.example.jarvis.events.Handler;
import com.example.jarvis.lexer.AnalizadorFuerzaBruta;
import com.example.jarvis.lexer.Tokenizer;
import com.example.jarvis.tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView prompt;
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

        prompt = findViewById(R.id.prompt);

        // Inicializar los objetos correspondientes a Jarvis
        misEventos = new EventosConPrioridad();
        miAnalizador = new AnalizadorFuerzaBruta();
    }

    public void setVoiceOutput(View view) {

        CharSequence text = prompt.getText();

        if (text.length() == 0)
            voice.speak("Lo siento, no tengo nada para decir", TextToSpeech.QUEUE_FLUSH, null, null);
        else
            voice.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);

        prompt.setText("");
    }

    public void getVoiceInput(View view) {

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
        List<Token> parsedInput;

        if (requestCode == 10)
            if (resultCode == RESULT_OK && data != null) {
                res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                parsedInput = miAnalizador.tokenize(res.get(0));

                prompt.setText(parsedInput.get(0).toString());
                setVoiceOutput(null);
            }
    }

    protected void onDestroy() {
        if (voice != null) {
            voice.stop();
            voice.shutdown();
        }

        super.onDestroy();
    }
}
