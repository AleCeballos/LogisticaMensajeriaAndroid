package com.example.logisticamensajeria.Viajes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.logisticamensajeria.R;

public class AgregarVerViajes extends AppCompatActivity {


    Button btnVerViajes,btnAgregarViajes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ver_viajes);

        btnVerViajes = (Button) findViewById(R.id.btnVerViajes);
        btnAgregarViajes = (Button) findViewById(R.id.btnAgregarViajes);

        //-----------Abro ventana ABM Clientes-------------//
        btnAgregarViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarVerViajes.this, ViajesForm.class);
                startActivity(i);
            }
        });

        //-----------Abro ventana Listado Clientes-------------//
        btnVerViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarVerViajes.this, ListadoViajes.class);
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