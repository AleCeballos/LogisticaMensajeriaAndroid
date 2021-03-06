package com.example.logisticamensajeria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1 ;
    private static final  String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_EMPLEADOS ="t_empleados";
    public static final String TABLE_CLIENTES ="t_clientes";
    public static final String TABLE_VIAJES ="t_viajes";
    public DbHelper(@Nullable Context context){
        super (context, DATABASE_NOMBRE, null, DATABASE_VERSION);



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_EMPLEADOS + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "cuil TEXT NOT NULL," +
                    "direccion TEXT NOT NULL," +
                    "fecha TEXT NOT NULL," +
                    "telefono TEXT NOT NULL)"


            );


            //--------CREAR TABLA CLIENTES

        db.execSQL("CREATE TABLE " + TABLE_CLIENTES + "(" +

                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "cuit TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "fecha TEXT NOT NULL," +
                "telefono TEXT NOT NULL)"


        );



        //--------CREAR TABLA CLIENTES

        db.execSQL("CREATE TABLE " + TABLE_VIAJES + "(" +

                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "direccion TEXT NOT NULL," +
                "localidad TEXT NOT NULL," +
                "precio TEXT NOT NULL," +
                "fecha TEXT NOT NULL," +
                "cliente TEXT NOT NULL," +
                "empleado TEXT NOT NULL)"


        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ TABLE_EMPLEADOS);
        onCreate(db);

        db.execSQL("DROP TABLE "+ TABLE_CLIENTES);
        onCreate(db);

        db.execSQL("DROP TABLE "+ TABLE_VIAJES);
        onCreate(db);

    }


}
