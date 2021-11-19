package com.example.logisticamensajeria.Viajes;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logisticamensajeria.R;

public class ViajesForm extends AppCompatActivity {


    EditText etDireccionViajes, etLocalidadViajes, etPrecioViajes, etClienteViajes, etEmpleadoViajes;
    Button btnGuardarViajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajes_form);

        etDireccionViajes = (EditText) findViewById(R.id.etDireccionViajes);
        etLocalidadViajes = (EditText) findViewById(R.id.etLocalidadViajes);
        etPrecioViajes = (EditText) findViewById(R.id.etPrecioViajes);
        etClienteViajes = (EditText) findViewById(R.id.etClienteViajes);
        etEmpleadoViajes = (EditText) findViewById(R.id.etEmpleadoViajes);
        btnGuardarViajes = (Button) findViewById(R.id.btnGuardarViajes);

        //------------------EJECUTO METODO PARA GUARDAR A TRAVES DE BOTON-----------------------------//
        btnGuardarViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbViajes dbViajes = new DbViajes(ViajesForm.this);
                long id = dbViajes.insertarViaje(etDireccionViajes.getText().toString(), etLocalidadViajes.getText().toString(),etPrecioViajes.getText().toString(),etClienteViajes.getText().toString(),etEmpleadoViajes.getText().toString());


                if(id > 0){

                    Toast.makeText(ViajesForm.this, "Viaje Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {

                    Toast.makeText(ViajesForm.this, "Error al guardar", Toast.LENGTH_LONG).show();
                }
            }
        });
        //----------------------------------------------//

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

    //----Limpio el formulario
    private void limpiar (){

        etDireccionViajes.setText("");
        etLocalidadViajes.setText("");
        etPrecioViajes.setText("");
        etClienteViajes.setText("");
        etEmpleadoViajes.setText("");

    }
}