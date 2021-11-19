package com.example.logisticamensajeria.Viajes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.logisticamensajeria.DbHelper;
import com.example.logisticamensajeria.Entidades.Viajes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbViajes extends DbHelper {

    Context context;

    public DbViajes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarViaje ( String direccion , String localidad, String precio, String cliente, String empleado ){

        long id = 0;
        try {


            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();


            //fecha
            String Fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            values.put("direccion", direccion);
            values.put("localidad", localidad);
            values.put("precio", precio);
            values.put("fecha",Fecha);
            values.put("cliente", cliente);
            values.put("empleado", empleado);


            id =  db.insert(TABLE_VIAJES, null, values );

        }catch (Exception ex){
            ex.toString();

        }

        return id;
    }


    public ArrayList<Viajes> mostrarViajes(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Viajes> listaViajes = new ArrayList<>();

        Viajes viajes = null;
        Cursor cursorViajes =null;

        cursorViajes = db.rawQuery("SELECT * FROM " + TABLE_VIAJES, null);

        if(cursorViajes.moveToFirst()){

            do{
                viajes = new Viajes();

                viajes.setId(cursorViajes.getInt(0));
                viajes.setDireccion(cursorViajes.getString(1));
                viajes.setLocalidad(cursorViajes.getString(2));
                viajes.setPrecio(cursorViajes.getString(3));
                viajes.setFecha(cursorViajes.getString(4));
                viajes.setCliente(cursorViajes.getString(5));
                viajes.setEmpleado(cursorViajes.getString(6));
                listaViajes.add(viajes);

            }while(cursorViajes.moveToNext());


        }
        cursorViajes.close();
        return listaViajes;
    }

    //PANTALLA DE MODIFICACION

    public Viajes modificarViajes(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        Viajes viajes = null;
        Cursor cursorViajes ;

        cursorViajes = db.rawQuery("SELECT * FROM " + TABLE_VIAJES + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorViajes.moveToFirst()){
            viajes = new Viajes();

            viajes.setId(cursorViajes.getInt(0));
            viajes.setDireccion(cursorViajes.getString(1));
            viajes.setLocalidad(cursorViajes.getString(2));
            viajes.setPrecio(cursorViajes.getString(3));
            viajes.setFecha(cursorViajes.getString(4));
            viajes.setCliente(cursorViajes.getString(5));
            viajes.setEmpleado(cursorViajes.getString(6));


        }
        cursorViajes.close();
        return viajes;
    }

    //BOTON DE MODIFICAR

    public boolean insertarDatosModificados ( int id, String direccion , String localidad, String precio, String cliente, String empleado ){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLE_VIAJES + " SET direccion = '" + direccion + "' , localidad = '" + localidad + "', precio = '" + precio + "', cliente = '" + cliente + "', empleado = '" + empleado + "' WHERE id= '" + id + "' ");
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

    public boolean eliminarViaje ( int id ){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_VIAJES + "  WHERE id= '" + id + "' ");
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
