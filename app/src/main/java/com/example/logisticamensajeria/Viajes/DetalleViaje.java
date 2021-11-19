package com.example.logisticamensajeria.Viajes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logisticamensajeria.Entidades.Viajes;
import com.example.logisticamensajeria.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetalleViaje extends AppCompatActivity {

    EditText etDireccionViajes, etLocalidadViajes, etPrecioViajes, etClienteViajes, etEmpleadoViajes;
    Button btnModificarViajes;
    FloatingActionButton fabEditar,fabEliminar;

    Viajes viajes;
    int id = 0;



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
        fabEditar =  findViewById(R.id.fabEditar);
        fabEliminar =  findViewById(R.id.fabEliminar);



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

        final DbViajes dbViajes = new DbViajes( DetalleViaje.this);
        viajes = dbViajes.modificarViajes(id);

        if(viajes != null ){
            etDireccionViajes.setText(viajes.getDireccion());
            etLocalidadViajes.setText(viajes.getLocalidad());
            etPrecioViajes.setText(viajes.getPrecio());
            etClienteViajes.setText(viajes.getCliente());
            etEmpleadoViajes.setText(viajes.getEmpleado());

            btnModificarViajes.setVisibility(View.INVISIBLE);

            etDireccionViajes.setInputType(InputType.TYPE_NULL);
            etLocalidadViajes.setInputType(InputType.TYPE_NULL);
            etPrecioViajes.setInputType(InputType.TYPE_NULL);
            etClienteViajes.setInputType(InputType.TYPE_NULL);
            etEmpleadoViajes.setInputType(InputType.TYPE_NULL);

        }

        //boton que modifica

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( DetalleViaje.this, EditarViaje.class );
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        //boton eliminar

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DetalleViaje.this);
                builder.setMessage("Queres eliminar este viaje?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialogInterface, int i ){
                                if(dbViajes.eliminarViaje(id)){

                                    volverListado();
                                }

                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i ){


                    }

                } ).show();

            }
        });


    }

    private void volverListado(){

        Intent intent = new Intent(this, ListadoViajes.class);
        startActivity(intent);

    }
}