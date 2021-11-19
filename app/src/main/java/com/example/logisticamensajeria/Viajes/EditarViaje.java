package com.example.logisticamensajeria.Viajes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logisticamensajeria.Entidades.Viajes;
import com.example.logisticamensajeria.R;

public class EditarViaje extends AppCompatActivity {

    EditText etDireccionViajes, etLocalidadViajes, etPrecioViajes, etClienteViajes, etEmpleadoViajes;
    Button btnModificarViajes;
    boolean correcto = false;


    int id = 0;
    Viajes viajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_viaje);

        etDireccionViajes = (EditText) findViewById(R.id.etDireccionViajes);
        etLocalidadViajes = (EditText) findViewById(R.id.etLocalidadViajes);
        etPrecioViajes = (EditText) findViewById(R.id.etPrecioViajes);
        etClienteViajes = (EditText) findViewById(R.id.etClienteViajes);
        etEmpleadoViajes = (EditText) findViewById(R.id.etEmpleadoViajes);
        btnModificarViajes = (Button) findViewById(R.id.btnModificarViajes);

        if(savedInstanceState == null){

            Bundle extras = getIntent().getExtras();
            if(extras == null ){

                id = Integer.parseInt((null));

            }else{

                id = extras.getInt("ID");
            }
        }else {

            id = (int) savedInstanceState.getSerializable("ID");

        }

        final DbViajes dbViajes = new DbViajes( EditarViaje.this);
        viajes = dbViajes.modificarViajes(id);

        if(viajes != null ){
            etDireccionViajes.setText(viajes.getDireccion());
            etLocalidadViajes.setText(viajes.getLocalidad());
            etPrecioViajes.setText(viajes.getPrecio());
            etClienteViajes.setText(viajes.getCliente());
            etEmpleadoViajes.setText(viajes.getEmpleado());





        }


        //boton que modifica


        btnModificarViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // valido que no sea vacio
                if (!etDireccionViajes.getText().toString().equals("") && !etLocalidadViajes.getText().toString().equals("") && !etPrecioViajes.getText().toString().equals("") && !etClienteViajes.getText().toString().equals("")  && !etEmpleadoViajes.getText().toString().equals("") ){

                    correcto =  dbViajes.insertarDatosModificados(id, etDireccionViajes.getText().toString(), etLocalidadViajes.getText().toString(),etPrecioViajes.getText().toString(),etClienteViajes.getText().toString(),etEmpleadoViajes.getText().toString());

                    if(correcto){

                        Toast.makeText(EditarViaje.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        regresarListadoViajes();
                    } else {

                        Toast.makeText(EditarViaje.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }else   {

                    Toast.makeText(EditarViaje.this, "LLENE TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void regresarListadoViajes(){

        Intent intent = new Intent(this, ListadoViajes.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}