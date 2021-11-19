package com.example.logisticamensajeria.Viajes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logisticamensajeria.Entidades.Viajes;
import com.example.logisticamensajeria.R;
import com.example.logisticamensajeria.adaptadores.ListaViajesAdapter;

import java.util.ArrayList;

public class ListadoViajes extends AppCompatActivity {

    RecyclerView listadoViajes;
    ArrayList<Viajes> listadoArrayViajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_viajes);


        listadoViajes = findViewById(R.id.listadoViajes);
        listadoViajes.setLayoutManager(new LinearLayoutManager(this));

        DbViajes dbViajes = new DbViajes(ListadoViajes.this);

        listadoArrayViajes = new ArrayList<>();

        ListaViajesAdapter adapter = new ListaViajesAdapter(dbViajes.mostrarViajes());
        listadoViajes.setAdapter(adapter);

    }
}