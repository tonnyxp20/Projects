package com.developer.tonny.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tonny on 25/04/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String baseDatos = "Escuela.db";
    public static final String tabla = "Estudiante";

    public static final String COL_1 = "id";
    public static final String COL_2 = "carrera";
    public static final String COL_3 = "nombre";
    public static final String COL_4 = "edad";

    public DataBaseHelper(Context context) {
        super(context, baseDatos, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE " + baseDatos + "(id INTEGER PRIMARY KEY AUTOINCREMENT, carrera TEXT, nombre TEXT, edad INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabla);
    }

    public boolean insertData(String carrera, String nombre, String edad){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, carrera);
        cv.put(COL_3, nombre);
        cv.put(COL_4, edad);
        long result = db.insert(tabla, null, cv);
        db.close();

        // Para comprobar si los datos se insertan en DataBase
        if(result == 1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tabla, null);
        return res;
    }
}
