package com.example.logisticamensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logisticamensajeria.Clientes.AgregarVerClientes;
import com.example.logisticamensajeria.Empleados.AgregarVerEmpleados;

public class MainActivity extends AppCompatActivity {

    Button btnViajes, btnClientes, btnEmpleados, btnFacturacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViajes = (Button) findViewById(R.id.btnViajes);
        btnClientes = (Button) findViewById(R.id.btnClientes);
        btnEmpleados = (Button) findViewById(R.id.btnEmpleados);
        btnFacturacion = (Button) findViewById(R.id.btnFacturacion);

        //-----------Abro ventana ABM clientes-------------//
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AgregarVerClientes.class);
               startActivity(i);
            }
       });

        //-----------Abro ventana ABM Empleados-------------//
        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AgregarVerEmpleados.class);
                startActivity(i);
            }
        });

        //-----------Abro ventana ABM Viajes-------------//
        btnViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Viajes.class);
                startActivity(i);
            }
        });

    }
}