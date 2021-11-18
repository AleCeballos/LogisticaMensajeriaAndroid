package com.example.logisticamensajeria.Empleados;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logisticamensajeria.R;

public class AgregarVerEmpleados extends AppCompatActivity {

    Button btnVerEmpleados,btnAgregarEmpleados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ver_empleados);


        btnVerEmpleados = (Button) findViewById(R.id.btnVerEmpleados);
        btnAgregarEmpleados = (Button) findViewById(R.id.btnAgregarEmpleados);



        //-----------Abro ventana ABM empleados-------------//
        btnAgregarEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarVerEmpleados.this, EmpleadosForm.class);
                startActivity(i);
            }
        });

        //-----------Abro ventana Listado empleados-------------//
        btnVerEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarVerEmpleados.this, ListadoEmpleados.class);
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