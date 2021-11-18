package com.example.logisticamensajeria.Clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.logisticamensajeria.DbHelper;
import com.example.logisticamensajeria.Entidades.Clientes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbClientes extends DbHelper {

    Context context;

    public DbClientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCliente ( String nombre , String cuit, String direccion, String telefono ){

        long id = 0;
        try {


            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();


            //fecha
            String Fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            values.put("nombre", nombre);
            values.put("cuit", cuit);
            values.put("direccion", direccion);
            values.put("Fecha",Fecha);
            values.put("telefono", telefono);


            id =  db.insert(TABLE_CLIENTES, null, values );

        }catch (Exception ex){
            ex.toString();

        }

        return id;
    }


    public ArrayList<Clientes> mostrarClientes(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();

        Clientes clientes = null;
        Cursor cursorClientes =null;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES, null);

        if(cursorClientes.moveToFirst()){

            do{
                clientes = new Clientes();

                clientes.setId(cursorClientes.getInt(0));
                clientes.setNombre(cursorClientes.getString(1));
                clientes.setCuit(cursorClientes.getString(2));
                clientes.setDireccion(cursorClientes.getString(3));
                clientes.setFecha(cursorClientes.getString(4));
                clientes.setTelefono(cursorClientes.getString(5));
                listaClientes.add(clientes);

            }while(cursorClientes.moveToNext());


        }
        cursorClientes.close();
        return listaClientes;
    }

    //PANTALLA DE MODIFICACION

    public Clientes modificarClientes(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        Clientes clientes = null;
        Cursor cursorClientes ;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorClientes.moveToFirst()){
            clientes = new Clientes();

            clientes.setId(cursorClientes.getInt(0));
            clientes.setNombre(cursorClientes.getString(1));
            clientes.setCuit(cursorClientes.getString(2));
            clientes.setDireccion(cursorClientes.getString(3));
            clientes.setTelefono(cursorClientes.getString(5));


        }
        cursorClientes.close();
        return clientes;
    }

    //BOTON DE MODIFICAR

    public boolean insertarDatosModificados ( int id, String nombre , String cuit, String direccion, String telefono ){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLE_CLIENTES + " SET nombre = '" + nombre + "' , cuit = '" + cuit + "', direccion = '" + direccion + "', telefono = '" + telefono + "' WHERE id= '" + id + "' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }

        return correcto;
    }


    //BOTON DE ELIMINAR

    public boolean eliminarCliente ( int id ){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_CLIENTES + "  WHERE id= '" + id + "' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }

        return correcto;
    }


}
