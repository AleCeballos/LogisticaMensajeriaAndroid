package com.example.logisticamensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clientes extends AppCompatActivity {

    EditText etNombreClientes, etCuitClientes, etDireccionClientes, etAltaClientes, etTelefonoClientes;
    Button btnGuardarClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        etNombreClientes = (EditText) findViewById(R.id.etNombreClientes);
        etCuitClientes = (EditText) findViewById(R.id.etCuitClientes);
        etDireccionClientes = (EditText) findViewById(R.id.etDireccionClientes);
        etTelefonoClientes = (EditText) findViewById(R.id.etTelefonoClientes);
        btnGuardarClientes = (Button) findViewById(R.id.btnModificarClientes);

        //------------------EJECUTO METODO PARA GUARDAR A TRAVES DE BOTON-----------------------------//
        btnGuardarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(etNombreClientes.getText().toString(), etCuitClientes.getText().toString(),etDireccionClientes.getText().toString(),etTelefonoClientes.getText().toString());
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

    private void guardar(String Nombre, String Cuit, String Direccion, String Telefono){
        BaseHelper helper = new BaseHelper(this,"Demo",null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        //fecha
         String Fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        try {
            ContentValues c = new ContentValues();
            c.put("Nombre",Nombre);
            c.put("Cuit",Cuit);
            c.put("Direccion",Direccion);
            c.put("Fecha",Fecha);
            c.put("Telefono",Telefono);
            db.insert("CLIENTES",null, c);
            db.close();
            Toast.makeText(this,"Cliente Ingresado",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"Error: " +e.getMessage(),Toast.LENGTH_SHORT).show();
        }
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