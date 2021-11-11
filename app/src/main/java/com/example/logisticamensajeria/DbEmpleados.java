package com.example.logisticamensajeria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.logisticamensajeria.Entidades.Empleados;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbEmpleados extends DbHelper {

    Context context;

    public DbEmpleados(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEmpleado ( String nombre , String cuil, String direccion, String telefono ){

        long id = 0;
        try {


        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();


            //fecha
            String Fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        values.put("nombre", nombre);
        values.put("cuil", cuil);
        values.put("direccion", direccion);
        values.put("Fecha",Fecha);
        values.put("telefono", telefono);


        id =  db.insert(TABLE_EMPLEADOS, null, values );

        }catch (Exception ex){
            ex.toString();

        }

      return id;
    }


    public ArrayList<Empleados> mostrarEmpleados(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Empleados> listaEmpleados = new ArrayList<>();

        Empleados empleados = null;
        Cursor cursorEmpleados =null;

        cursorEmpleados = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADOS, null);

        if(cursorEmpleados.moveToFirst()){

          do{
              empleados = new Empleados();

              empleados.setId(cursorEmpleados.getInt(0));
              empleados.setNombre(cursorEmpleados.getString(1));
              empleados.setCuil(cursorEmpleados.getString(2));
              empleados.setDireccion(cursorEmpleados.getString(3));
              empleados.setFecha(cursorEmpleados.getString(4));
              empleados.setTelefono(cursorEmpleados.getString(5));
              listaEmpleados.add(empleados);

          }while(cursorEmpleados.moveToNext());


        }
cursorEmpleados.close();
        return listaEmpleados;
    }

    //PANTALLA DE MODIFICACION

    public Empleados modificarEmpleados(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        Empleados empleados = null;
        Cursor cursorEmpleados ;

        cursorEmpleados = db.rawQuery("SELECT * FROM " + TABLE_EMPLEADOS + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorEmpleados.moveToFirst()){
                empleados = new Empleados();

                empleados.setId(cursorEmpleados.getInt(0));
                empleados.setNombre(cursorEmpleados.getString(1));
                empleados.setCuil(cursorEmpleados.getString(2));
                empleados.setDireccion(cursorEmpleados.getString(3));
               empleados.setTelefono(cursorEmpleados.getString(5));


        }
        cursorEmpleados.close();
        return empleados;
    }

    //BOTON DE MODIFICAR

    public boolean insertarDatosModificados ( int id, String nombre , String cuil, String direccion, String telefono ){

      boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

     db.execSQL("UPDATE " + TABLE_EMPLEADOS + " SET nombre = '" + nombre + "' , cuil = '" + cuil + "', direccion = '" + direccion + "', telefono = '" + telefono + "' WHERE id= '" + id + "' ");
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

    public boolean eliminarEmpleado ( int id ){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_EMPLEADOS + "  WHERE id= '" + id + "' ");
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
