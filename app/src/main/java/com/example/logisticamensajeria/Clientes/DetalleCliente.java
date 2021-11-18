package com.example.logisticamensajeria.Clientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logisticamensajeria.Entidades.Clientes;
import com.example.logisticamensajeria.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetalleCliente extends AppCompatActivity {

    EditText etNombreClientes, etCuitClientes, etDireccionClientes, etAltaClientes, etTelefonoClientes;
    Button btnModificarClientes;
    FloatingActionButton fabEditar,fabEliminar;

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

        final DbClientes dbClientes = new DbClientes( DetalleCliente.this);
        clientes = dbClientes.modificarClientes(id);

        if(clientes != null ){
            etNombreClientes.setText(clientes.getNombre());
            etCuitClientes.setText(clientes.getCuit());
            etDireccionClientes.setText(clientes.getDireccion());
            etTelefonoClientes.setText(clientes.getTelefono());

            btnModificarClientes.setVisibility(View.INVISIBLE);

            etNombreClientes.setInputType(InputType.TYPE_NULL);
            etCuitClientes.setInputType(InputType.TYPE_NULL);
            etDireccionClientes.setInputType(InputType.TYPE_NULL);
            etTelefonoClientes.setInputType(InputType.TYPE_NULL);

        }

        //boton que modifica

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( DetalleCliente.this, EditarCliente.class );
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        //boton eliminar

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DetalleCliente.this);
                builder.setMessage("Queres eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialogInterface, int i ){
                                if(dbClientes.eliminarCliente(id)){

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

        Intent intent = new Intent(this, ListadoClientes.class);
        startActivity(intent);

    }
}