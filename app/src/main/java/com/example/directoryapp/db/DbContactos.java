package com.example.directoryapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.directoryapp.entidades.Contactos;

import java.util.ArrayList;

public class DbContactos extends DbHelper{

    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String webpage, String telefono, String email, String proyser, int consultoria, int desarrollo, int fabrica){

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

    public ArrayList<Contactos> mostrarContactos(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM "+TABLE_CONTACTOS,null);

        if(cursorContactos.moveToFirst()){
            do {
                contacto = new Contactos();
                contacto.setNombre(cursorContactos.getString(0));
                contacto.setWebpage(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setEmail(cursorContactos.getString(3));
                contacto.setProyser(cursorContactos.getString(4));
                contacto.setConsultoria(cursorContactos.getInt(5));
                contacto.setDesarrollo(cursorContactos.getInt(6));
                contacto.setFabrica(cursorContactos.getInt(7));
                listaContactos.add(contacto);
            }while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;
    }

}
