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
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setWebpage(cursorContactos.getString(2));
                contacto.setTelefono(cursorContactos.getString(3));
                contacto.setEmail(cursorContactos.getString(4));
                contacto.setProyser(cursorContactos.getString(5));
                contacto.setConsultoria(cursorContactos.getInt(6));
                contacto.setDesarrollo(cursorContactos.getInt(7));
                contacto.setFabrica(cursorContactos.getInt(8));
                listaContactos.add(contacto);
            }while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;
    }


    public Contactos verContacto(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM "+TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1",null);

        if(cursorContactos.moveToFirst()){
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setWebpage(cursorContactos.getString(2));
            contacto.setTelefono(cursorContactos.getString(3));
            contacto.setEmail(cursorContactos.getString(4));
            contacto.setProyser(cursorContactos.getString(5));
            contacto.setConsultoria(cursorContactos.getInt(6));
            contacto.setDesarrollo(cursorContactos.getInt(7));
            contacto.setFabrica(cursorContactos.getInt(8));
        }

        cursorContactos.close();

        return contacto;
    }


    public boolean editarContacto(int id, String nombre, String webpage, String telefono, String email, String proyser, int consultoria, int desarrollo, int fabrica){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET nombre = '"+nombre+"',webpage = '"+webpage+"',telefono = '"+telefono+"'," +
                    "email = '"+email+"',proyser = '"+proyser+"',consultoria = '"+consultoria+"',desarrollo = '"+desarrollo+"'," +
                    "fabrica = '"+fabrica+"' WHERE id='"+id+"' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

}
