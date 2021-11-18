package com.example.logisticamensajeria.Clientes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logisticamensajeria.Entidades.Clientes;
import com.example.logisticamensajeria.R;
import com.example.logisticamensajeria.adaptadores.ListaClientesAdapter;

import java.util.ArrayList;

public class ListadoClientes extends AppCompatActivity {
    RecyclerView listadoClientes;
    ArrayList<Clientes> listadoArrayClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_clientes);

        listadoClientes = findViewById(R.id.listadoClientes);
        listadoClientes.setLayoutManager(new LinearLayoutManager(this));

        DbClientes dbClientes = new DbClientes(ListadoClientes.this);

        listadoArrayClientes = new ArrayList<>();

        ListaClientesAdapter adapter = new ListaClientesAdapter(dbClientes.mostrarClientes());
        listadoClientes.setAdapter(adapter);
    }
}