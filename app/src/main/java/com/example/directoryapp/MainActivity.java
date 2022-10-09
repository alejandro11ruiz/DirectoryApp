package com.example.directoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.directoryapp.adaptadores.ListaContactosAdapter;
import com.example.directoryapp.db.DbContactos;
import com.example.directoryapp.db.DbHelper;
import com.example.directoryapp.entidades.Contactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscar;
    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;
    ListaContactosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaContactos=findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        DbContactos dbContactos = new DbContactos(MainActivity.this);

        listaArrayContactos = new ArrayList<>();

        adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        listaContactos.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);

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
            case R.id.menuClasificacion:
                showDialog(1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        startActivity(new Intent(this, NuevoActivity.class));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(id) {
            case 1:
                final CharSequence[] levels = {
                        getResources().getString(R.string.hint_consultoria),
                        getResources().getString(R.string.hint_desarrollo),
                        getResources().getString(R.string.hint_fabrica),
                        getResources().getString(R.string.mostrar_todos)};
                int selected =3;
                builder.setSingleChoiceItems(levels, selected, (dialog1, item) -> {
                    dialog1.dismiss(); // Close dialog
                    if(levels[item].equals(getResources().getString(R.string.hint_consultoria))) adapter.filtradoPorClas(0);
                    else if (levels[item].equals(getResources().getString(R.string.hint_desarrollo))) adapter.filtradoPorClas(1);
                    else if (levels[item].equals(getResources().getString(R.string.hint_fabrica))) adapter.filtradoPorClas(2);
                    else adapter.filtradoPorClas(3);
                    Toast.makeText(getApplicationContext(), levels[item],Toast.LENGTH_SHORT).show();
                });
                dialog = builder.create();
                break;
        }
        return dialog;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}