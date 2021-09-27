package com.example.logisticamensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AbmClientes extends AppCompatActivity {

    Button btnVerClientes, btnAgregarClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abm_clientes);



        btnVerClientes = (Button) findViewById(R.id.btnVerClientes);
        btnAgregarClientes = (Button) findViewById(R.id.btnAgregarClientes);



        //-----------Abro ventana ABM clientes-------------//
        btnAgregarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AbmClientes.this,Clientes.class);
                startActivity(i);
            }
        });

        //-----------Abro ventana Listado clientes-------------//
        btnVerClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AbmClientes.this,ListadoClientes.class);
                startActivity(i);
            }
        });
    }
}