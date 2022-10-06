package com.example.directoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.directoryapp.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button mBtnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCreate=findViewById(R.id.btnCreate);

        mBtnCreate.setOnClickListener(view -> {
            DbHelper dbHelper = new DbHelper(MainActivity.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if(db != null) Toast.makeText(MainActivity.this,"BASE DE DATOS CREADA", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this,"ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_SHORT).show();
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        startActivity(new Intent(this, NuevoActivity.class));
    }
}