package com.example.logisticamensajeria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AdapterView;

import androidx.annotation.Nullable;

public class BaseHelper extends SQLiteOpenHelper {

   //CREO LA TABLA CLIENTES
    String tabla = "CREATE TABLE CLIENTES (ID INTEGER PRIMARY KEY, NOMBRE TEXT, CUIT TEXT, DIRECCION TEXT, FECHA TEXT, TELEFONO TEXT)";
    public BaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table clientes");
        db.execSQL(tabla);
    }
}
