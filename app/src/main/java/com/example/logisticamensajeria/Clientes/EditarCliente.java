package com.example.logisticamensajeria.Clientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logisticamensajeria.Entidades.Clientes;
import com.example.logisticamensajeria.R;

public class EditarCliente extends AppCompatActivity{

    EditText etNombreClientes, etCuitClientes, etDireccionClientes, etAltaClientes, etTelefonoClientes;
    Button btnModificarClientes;
    boolean correcto = false;

    Clientes clientes;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);

        etNombreClientes = findViewById(R.id.etNombreClientes);
        etCuitClientes = findViewById(R.id.etCuitClientes);
        etDireccionClientes =  findViewById(R.id.etDireccionClientes);
        etTelefonoClientes = findViewById(R.id.etTelefonoClientes);
        btnModificarClientes =  findViewById(R.id.btnModificarClientes);

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

        final DbClientes dbClientes = new DbClientes( EditarCliente.this);
        clientes = dbClientes.modificarClientes(id);

        if(clientes != null ){
            etNombreClientes.setText(clientes.getNombre());
            etCuitClientes.setText(clientes.getCuit());
            etDireccionClientes.setText(clientes.getDireccion());
            etTelefonoClientes.setText(clientes.getTelefono());




        }


        //boton que modifica


        btnModificarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // valido que no sea vacio
                if (!etNombreClientes.getText().toString().equals("") && !etCuitClientes.getText().toString().equals("") && !etDireccionClientes.getText().toString().equals("") && !etTelefonoClientes.getText().toString().equals("")  ){

                    correcto =  dbClientes.insertarDatosModificados(id, etNombreClientes.getText().toString(), etCuitClientes.getText().toString(),etDireccionClientes.getText().toString(),etTelefonoClientes.getText().toString());

                    if(correcto){

                        Toast.makeText(EditarCliente.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        regresarListadoClientes();
                    } else {

                        Toast.makeText(EditarCliente.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }else   {

                    Toast.makeText(EditarCliente.this, "LLENE TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void regresarListadoClientes(){

        Intent intent = new Intent(this, ListadoClientes.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}
