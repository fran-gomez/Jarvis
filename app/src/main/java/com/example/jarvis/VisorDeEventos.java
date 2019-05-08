package com.example.jarvis;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jarvis.events.Evento;
import com.example.jarvis.events.RecordatoriosOrdenados;
import com.example.jarvis.events.Reminder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VisorDeEventos extends AppCompatActivity {

    protected Reminder misRecordatorios;
    protected RecyclerView contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_de_eventos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        misRecordatorios = RecordatoriosOrdenados.getInstancia();

        List<Evento> eventos = new ArrayList<>();
        Iterator<Evento> it;
        it = misRecordatorios.obtenerEventos();
        while(it.hasNext())
            eventos.add(it.next());

        contenedor = findViewById(R.id.contenedor);
        contenedor.setLayoutManager(new LinearLayoutManager(this));
        contenedor.setAdapter(new Adaptador(eventos));
    }

    public static class Adaptador extends RecyclerView.Adapter<Adaptador.Vista> {

        protected List<Evento> conjunto;

        public Adaptador(List<Evento> l) {
            conjunto = l;
        }

        @NonNull
        @Override
        public Vista onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_visor_de_eventos, viewGroup, false);
            Vista vh = new Vista((TextView) v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull Vista vista, int i) {
            vista.textView.setText(conjunto.get(i).toString());
        }

        @Override
        public int getItemCount() {
            return conjunto.size();
        }

        public static class Vista extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView textView;
            public Vista(TextView v) {
                super(v);
                textView = v;
            }
        }

    }
}
