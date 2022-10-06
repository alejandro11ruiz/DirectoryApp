package com.example.directoryapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbContactos extends DbHelper{

    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long crearContacto(String nombre, String webpage, String telefono, String email, String proyser, int consultoria, int desarrollo, int fabrica){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("webpage", webpage);
            values.put("telefono", telefono);
            values.put("email", email);
            values.put("proyser", proyser);
            values.put("consultoria", consultoria);
            values.put("desarrollo", desarrollo);
            values.put("fabrica", fabrica);

            id = db.insert(TABLE_CONTACTOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
