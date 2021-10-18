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

public class ModificarClientes extends AppCompatActivity {

    EditText etNombreClientes, etCuitClientes, etDireccionClientes, etAltaClientes, etTelefonoClientes;
    Button btnModificarClientes, btnEliminarClientes;

    //creo variables para recibir parametros
    int id;
    String nombre;
    String cuit;
    String direccion;
    String fecha;
    String telefono;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_clientes);

        etNombreClientes = (EditText) findViewById(R.id.etNombreClientes);
        etCuitClientes = (EditText) findViewById(R.id.etCuitClientes);
        etDireccionClientes = (EditText) findViewById(R.id.etDireccionClientes);
        etTelefonoClientes = (EditText) findViewById(R.id.etTelefonoClientes);
        btnModificarClientes = (Button) findViewById(R.id.btnModificarClientes);
        btnEliminarClientes = (Button) findViewById(R.id.btnEliminarClientes);


        //recibo parametros
        Bundle b = getIntent().getExtras();
        if(b != null){

            id = b.getInt("Id");
            nombre = b.getString("Nombre");
           cuit = b.getString("Cuit");
            direccion = b.getString("Direccion");
            telefono = b.getString("Telefono");
        }

    //lleno los campos

        etNombreClientes.setText(nombre);
        etCuitClientes.setText(cuit);
        etDireccionClientes.setText(direccion);
        etTelefonoClientes.setText(telefono);





        //------------------EJECUTO METODO PARA MODIFICAR A TRAVES DE BOTON-----------------------------//
        btnModificarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(id,etNombreClientes.getText().toString(), etCuitClientes.getText().toString(),etDireccionClientes.getText().toString(),etTelefonoClientes.getText().toString());
                onBackPressed();
            }
        });
        //----------------------------------------------//

        //------------------EJECUTO METODO PARA ELIMINAR A TRAVES DE BOTON-----------------------------//
        btnEliminarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               eliminar(id);
                onBackPressed();
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

    private void modificar(int id, String Nombre, String Cuit, String Direccion, String Telefono){
        BaseHelper helper = new BaseHelper(this,"Demo",null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "update Clientes set Nombre='" + Nombre + "',Cuit='" + Cuit + "',Direccion='" + Direccion + "',Telefono='" + Telefono + "' Where Id="+ id;
       db.execSQL(sql);
       db.close();
    }


    private void eliminar(int id){
        BaseHelper helper = new BaseHelper(this,"Demo",null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "delete from Clientes   Where Id="+ id;
        db.execSQL(sql);
        db.close();
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