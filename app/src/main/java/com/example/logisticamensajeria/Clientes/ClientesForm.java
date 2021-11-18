package com.example.logisticamensajeria.Clientes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logisticamensajeria.Empleados.DbEmpleados;
import com.example.logisticamensajeria.Empleados.EmpleadosForm;
import com.example.logisticamensajeria.R;
public class ClientesForm extends AppCompatActivity {

    EditText etNombreClientes, etCuitClientes, etDireccionClientes, etAltaClientes, etTelefonoClientes;
    Button btnGuardarClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_form);

        etNombreClientes = (EditText) findViewById(R.id.etNombreClientes);
        etCuitClientes = (EditText) findViewById(R.id.etCuitClientes);
        etDireccionClientes = (EditText) findViewById(R.id.etDireccionClientes);
        etTelefonoClientes = (EditText) findViewById(R.id.etTelefonoClientes);
        btnGuardarClientes = (Button) findViewById(R.id.btnGuardarClientes);

        //------------------EJECUTO METODO PARA GUARDAR A TRAVES DE BOTON-----------------------------//
        btnGuardarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbClientes dbClientes = new DbClientes(ClientesForm.this);
                long id = dbClientes.insertarCliente(etNombreClientes.getText().toString(), etCuitClientes.getText().toString(),etDireccionClientes.getText().toString(),etTelefonoClientes.getText().toString());


                if(id > 0){

                    Toast.makeText(ClientesForm.this, "Cliente Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {

                    Toast.makeText(ClientesForm.this, "Error al guardar", Toast.LENGTH_LONG).show();
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

        etNombreClientes.setText("");
        etCuitClientes.setText("");
        etDireccionClientes.setText("");
        etTelefonoClientes.setText("");

    }
    }