package com.example.directoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.directoryapp.db.DbContactos;
import com.example.directoryapp.entidades.Contactos;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtWebpage, txtTelefono, txtEmail, txtProyser;
    CheckBox checkCons, checkDesa, checkFabr;
    Button btnGuardar, btnEditar;
    boolean correcto = false;
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

        DbContactos dbContactos = new DbContactos(EditarActivity.this);
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

            btnEditar.setVisibility(View.INVISIBLE);
        }

        btnGuardar.setOnClickListener(view -> {
            if(!txtNombre.getText().toString().equals("")){
                correcto = dbContactos.editarContacto(id, txtNombre.getText().toString(), txtWebpage.getText().toString(),
                        txtTelefono.getText().toString(), txtEmail.getText().toString(), txtProyser.getText().toString(),
                        toConvertBool(checkCons.isChecked()),toConvertBool(checkDesa.isChecked()),toConvertBool(checkFabr.isChecked()));

                if(correcto){
                    Toast.makeText(EditarActivity.this, "Contacto modificado", Toast.LENGTH_SHORT).show();
                    verRegistro();
                } else {
                    Toast.makeText(EditarActivity.this, "Error al modificar contacto", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(EditarActivity.this, "Debe asignar el nombre de la empresa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean toConvertInt(int checkBox){
        if (checkBox == 1) return true;
        else return false;
    }

    private int toConvertBool(boolean checkBox){
        if (checkBox == true) return 1;
        else return 0;
    }


    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
