package com.example.directoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.directoryapp.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtWebpage, txtTelefono, txtEmail, txtProyser;
    CheckBox checkCons, checkDesa, checkFabr;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre=findViewById(R.id.txtNombre);
        txtWebpage=findViewById(R.id.txtWebpage);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtEmail=findViewById(R.id.txtEmail);
        txtProyser=findViewById(R.id.txtProyser);
        checkCons=findViewById(R.id.cbCons);
        checkDesa=findViewById(R.id.cbDesa);
        checkFabr=findViewById(R.id.cbFabr);
        btnGuardar=findViewById(R.id.btnCreate);

        btnGuardar.setOnClickListener(view -> {
            DbContactos dbContactos = new DbContactos(NuevoActivity.this);
            long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtWebpage.getText().toString(),
                    txtTelefono.getText().toString(), txtEmail.getText().toString(), txtProyser.getText().toString(),
                    toConvertBool(checkCons.isChecked()),toConvertBool(checkDesa.isChecked()),toConvertBool(checkFabr.isChecked()));

            if(id>0) {
                Toast.makeText(NuevoActivity.this,"REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                limpiar();
            } else Toast.makeText(NuevoActivity.this,"ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_SHORT).show();
        });
    }

    private int toConvertBool(boolean checkBox){
        if (checkBox == true) return 1;
        else return 0;
    }

    private void limpiar(){
        txtNombre.setText("");
        txtWebpage.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtProyser.setText("");
        checkCons.setChecked(false);
        checkDesa.setChecked(false);
        checkFabr.setChecked(false);
    }
}