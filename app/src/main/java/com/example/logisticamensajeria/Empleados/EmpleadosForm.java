package com.example.logisticamensajeria.Empleados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logisticamensajeria.R;

public class EmpleadosForm extends AppCompatActivity {

    EditText etNombreEmpleados, etCuitEmpleados, etDireccionEmpleados, etAltaEmpleados, etTelefonoEmpleados;
    Button btnGuardarEmpleados;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados_form);

        etNombreEmpleados = (EditText) findViewById(R.id.etNombreEmpleados);
        etCuitEmpleados = (EditText) findViewById(R.id.etCuilEmpleados);
        etDireccionEmpleados = (EditText) findViewById(R.id.etDireccionEmpleados);
        etTelefonoEmpleados = (EditText) findViewById(R.id.etTelefonoEmpleados);
        btnGuardarEmpleados = (Button) findViewById(R.id.btnGuardarEmpleados);

        //------------------EJECUTO METODO PARA GUARDAR A TRAVES DE BOTON-----------------------------//
        btnGuardarEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbEmpleados dbEmpleados = new DbEmpleados(EmpleadosForm.this);
               long id = dbEmpleados.insertarEmpleado(etNombreEmpleados.getText().toString(), etCuitEmpleados.getText().toString(),etDireccionEmpleados.getText().toString(),etTelefonoEmpleados.getText().toString());


                if(id > 0){

                    Toast.makeText(EmpleadosForm.this, "Empleado Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {

                    Toast.makeText(EmpleadosForm.this, "Error al guardar", Toast.LENGTH_LONG).show();
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

        etNombreEmpleados.setText("");
        etCuitEmpleados.setText("");
        etDireccionEmpleados.setText("");
        etTelefonoEmpleados.setText("");

    }
}