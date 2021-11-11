package com.example.logisticamensajeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.logisticamensajeria.Entidades.Empleados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetalleEmpleado extends AppCompatActivity {

    EditText etNombreEmpleados, etCuilEmpleados, etDireccionEmpleados, etAltaEmpleados, etTelefonoEmpleados;
    Button btnModificarEmpleados;
    FloatingActionButton fabEditar,fabEliminar;


    Empleados empleados;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);

        etNombreEmpleados = findViewById(R.id.etNombreEmpleados);
        etCuilEmpleados = findViewById(R.id.etCuilEmpleados);
        etDireccionEmpleados =  findViewById(R.id.etDireccionEmpleados);
        etTelefonoEmpleados = findViewById(R.id.etTelefonoEmpleados);
        btnModificarEmpleados =  findViewById(R.id.btnModificarEmpleados);
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

      final   DbEmpleados dbEmpleados = new DbEmpleados( DetalleEmpleado.this);
        empleados = dbEmpleados.modificarEmpleados(id);

        if(empleados != null ){
            etNombreEmpleados.setText(empleados.getNombre());
            etCuilEmpleados.setText(empleados.getCuil());
            etDireccionEmpleados.setText(empleados.getDireccion());
            etTelefonoEmpleados.setText(empleados.getTelefono());

            btnModificarEmpleados.setVisibility(View.INVISIBLE);

            etNombreEmpleados.setInputType(InputType.TYPE_NULL);
            etCuilEmpleados.setInputType(InputType.TYPE_NULL);
            etDireccionEmpleados.setInputType(InputType.TYPE_NULL);
            etTelefonoEmpleados.setInputType(InputType.TYPE_NULL);

        }


        //boton que modifica

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( DetalleEmpleado.this, EditarEmpleado.class );
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });


        //boton eliminar

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DetalleEmpleado.this);
                builder.setMessage("Queres eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {

                   @Override

                   public void onClick(DialogInterface dialogInterface, int i ){
                    if(dbEmpleados.eliminarEmpleado(id)){

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

        Intent intent = new Intent(this, ListadoEmpleados.class);
        startActivity(intent);

    }
}