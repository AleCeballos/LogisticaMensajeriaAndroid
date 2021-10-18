package com.example.logisticamensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoClientes extends AppCompatActivity {

    ListView listViewClientes;
    ArrayList<String> listado;

    //-----------aca sobreescribo el metodo para refrescar la pantalla cuando se ingresa a este activity


    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_clientes);
        listViewClientes = (ListView) findViewById(R.id.ListviewClientes);

        CargarListado();
        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ListadoClientes.this,"Posicion:"+position, Toast.LENGTH_SHORT).show();
                //Toast.makeText(ListadoClientes.this,listado.get(position), Toast.LENGTH_SHORT).show();

                //Capturo lo que hay en la posicion
                 Toast.makeText(ListadoClientes.this,listado.get(position), Toast.LENGTH_SHORT).show();
                //corto el contenido del text view por los espacios

                int indice = Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre = listado.get(position).split(" ")[1];
                String cuit = listado.get(position).split(" ")[2];
                String direccion = listado.get(position).split(" ")[3];
                String fecha = listado.get(position).split(" ")[4];
                String telefono = listado.get(position).split(" ")[5];


               // BaseHelper helper = new BaseHelper(ListadoClientes.this,"Demo",null, 1);
               // SQLiteDatabase db = helper.getReadableDatabase();
               // Cursor cursor = db.rawQuery("select Id from Clientes where Id="+id,null);
              //  List<ListadoClientes> indice = new ArrayList<>();
                //Toast.makeText(ListadoClientes.this,sql, Toast.LENGTH_SHORT).show();
                //   db.close();








                //Paso lo datos a la vista modificar
                Intent intent = new Intent(ListadoClientes.this,ModificarClientes.class);
                intent.putExtra("Id",indice);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("Cuit",cuit);
                intent.putExtra("Direccion",direccion);
                intent.putExtra("Fecha",fecha);
                intent.putExtra("Telefono",telefono);
                startActivity(intent);
            }
        });
        //EliminarBase();
    }

  //  private void EliminarBase() {
   //     BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
   //     SQLiteDatabase db = helper.getReadableDatabase();
  //      db.delete("Clientes", null, null);
  // }

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