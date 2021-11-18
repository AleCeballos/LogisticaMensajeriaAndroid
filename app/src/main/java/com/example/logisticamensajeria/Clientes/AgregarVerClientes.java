package com.example.logisticamensajeria.Clientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logisticamensajeria.R;


public class AgregarVerClientes extends AppCompatActivity {

    Button btnVerClientes,btnAgregarClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ver_clientes);

        btnVerClientes = (Button) findViewById(R.id.btnVerClientes);
        btnAgregarClientes = (Button) findViewById(R.id.btnAgregarClientes);

        //-----------Abro ventana ABM Clientes-------------//
        btnAgregarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarVerClientes.this, ClientesForm.class);
                startActivity(i);
            }
        });

        //-----------Abro ventana Listado Clientes-------------//
        btnVerClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarVerClientes.this, ListadoClientes.class);
                startActivity(i);
            }
        });
        //AGREGO EL BOTON DE REGRESAR
        //----------------------------------------------------------//
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //----------------------------------------------------------//

    }

    //SI APRETO EL BOTON ATRAS CIERRO EL ACTIVITY
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //-------------------------------------/
    }
