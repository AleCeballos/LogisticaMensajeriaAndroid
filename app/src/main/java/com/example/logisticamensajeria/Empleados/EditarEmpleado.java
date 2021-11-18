package com.example.logisticamensajeria.Empleados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logisticamensajeria.Entidades.Empleados;
import com.example.logisticamensajeria.R;

public class EditarEmpleado extends AppCompatActivity {

    EditText etNombreEmpleados, etCuilEmpleados, etDireccionEmpleados, etAltaEmpleados, etTelefonoEmpleados;
    Button btnModificarEmpleados;
boolean correcto = false;

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

       final DbEmpleados dbEmpleados = new DbEmpleados( EditarEmpleado.this);
        empleados = dbEmpleados.modificarEmpleados(id);

        if(empleados != null ){
            etNombreEmpleados.setText(empleados.getNombre());
            etCuilEmpleados.setText(empleados.getCuil());
            etDireccionEmpleados.setText(empleados.getDireccion());
            etTelefonoEmpleados.setText(empleados.getTelefono());




        }


        //boton que modifica


       btnModificarEmpleados.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               // valido que no sea vacio
               if (!etNombreEmpleados.getText().toString().equals("") && !etCuilEmpleados.getText().toString().equals("") && !etDireccionEmpleados.getText().toString().equals("") && !etTelefonoEmpleados.getText().toString().equals("")  ){

                 correcto =  dbEmpleados.insertarDatosModificados(id, etNombreEmpleados.getText().toString(), etCuilEmpleados.getText().toString(),etDireccionEmpleados.getText().toString(),etTelefonoEmpleados.getText().toString());

                 if(correcto){

                     Toast.makeText(EditarEmpleado.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                     regresarListadoEmpleados();
                 } else {

                     Toast.makeText(EditarEmpleado.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                 }
               }else   {

                   Toast.makeText(EditarEmpleado.this, "LLENE TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
               }

           }
       });


    }

        private void regresarListadoEmpleados(){

            Intent intent = new Intent(this, ListadoEmpleados.class);
            intent.putExtra("ID",id);
            startActivity(intent);
    }

}


