package com.example.logisticamensajeria.Empleados;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logisticamensajeria.Entidades.Empleados;
import com.example.logisticamensajeria.R;
import com.example.logisticamensajeria.adaptadores.ListaEmpleadosAdapter;

import java.util.ArrayList;

public class ListadoEmpleados extends AppCompatActivity {

    RecyclerView listadoEmpleados;
    ArrayList<Empleados> listadoArrayEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_empleados);

        listadoEmpleados = findViewById(R.id.listadoEmpleados);
        listadoEmpleados.setLayoutManager(new LinearLayoutManager(this));

        DbEmpleados dbEmpleados = new DbEmpleados(ListadoEmpleados.this);

        listadoArrayEmpleados = new ArrayList<>();

        ListaEmpleadosAdapter adapter = new ListaEmpleadosAdapter(dbEmpleados.mostrarEmpleados());
        listadoEmpleados.setAdapter(adapter);

    }
}