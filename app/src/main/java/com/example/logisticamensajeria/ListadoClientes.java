package com.example.logisticamensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoClientes extends AppCompatActivity {

    ListView listViewClientes;
    ArrayList<String> listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_clientes);

        listViewClientes = (ListView) findViewById(R.id.ListviewClientes);
        CargarListado();
        //EliminarBase();
    }

    private void EliminarBase() {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.delete("Clientes", null, null);
    }

    private void CargarListado(){

        listado = ListadoClientes();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listViewClientes.setAdapter(adapter);

        //AGREGO EL BOTON DE REGRESAR
        //----------------------------------------------------------//
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //----------------------------------------------------------//
    }

    private ArrayList<String> ListadoClientes (){
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select Id, Nombre, Cuit, Direccion, Fecha, Telefono from Clientes";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){

            do {
                String linea = c.getInt(0) +" "+ c.getString(1) +" "+ c.getString(2) +" "+ c.getString(3) +" "+ c.getString(4) +" "+
                        c.getString(5);
                datos.add(linea);
            }while (c.moveToNext());
        }
db.close();
        return datos;
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