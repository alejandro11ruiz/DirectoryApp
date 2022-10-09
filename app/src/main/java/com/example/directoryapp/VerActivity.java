package com.example.directoryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.directoryapp.db.DbContactos;
import com.example.directoryapp.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtWebpage, txtTelefono, txtEmail, txtProyser;
    CheckBox checkCons, checkDesa, checkFabr;
    Button btnGuardar, btnEditar, btnEliminar;

    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre=findViewById(R.id.txtNombre);
        txtWebpage=findViewById(R.id.txtWebpage);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtEmail=findViewById(R.id.txtEmail);
        txtProyser=findViewById(R.id.txtProyser);
        checkCons=findViewById(R.id.cbCons);
        checkDesa=findViewById(R.id.cbDesa);
        checkFabr=findViewById(R.id.cbFabr);
        btnGuardar=findViewById(R.id.btnCreate);
        btnEditar=findViewById(R.id.btnEditar);
        btnEliminar=findViewById(R.id.btnEliminar);

        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");

            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtWebpage.setText(contacto.getWebpage());
            txtTelefono.setText(contacto.getTelefono());
            txtEmail.setText(contacto.getEmail());
            txtProyser.setText(contacto.getProyser());
            checkCons.setChecked(toConvertInt(contacto.isConsultoria()));
            checkDesa.setChecked(toConvertInt(contacto.isDesarrollo()));
            checkFabr.setChecked(toConvertInt(contacto.isFabrica()));

            btnGuardar.setVisibility(View.INVISIBLE);

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtWebpage.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtEmail.setInputType(InputType.TYPE_NULL);
            txtProyser.setInputType(InputType.TYPE_NULL);
            checkCons.setEnabled(false);
            checkDesa.setEnabled(false);
            checkFabr.setEnabled(false);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(VerActivity.this, EditarActivity.class);
            intent.putExtra("ID",id);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
            //.setNegativeButton("No", new DialogInterface.OnClickListener() {
            builder.setMessage("Are you sure you want to delete this contact?").
                    setPositiveButton("Yes", (dialogInterface, i) -> {
                        boolean correcto = dbContactos.eliminarContacto(id);
                        if(correcto){
                            lista();
                            Toast.makeText(this, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {

                    }).show();
        });

    }

    private boolean toConvertInt(int checkBox){
        if (checkBox == 1) return true;
        else return false;
    }

    private void lista(){
        startActivity(new Intent(this, MainActivity.class));
    }
}