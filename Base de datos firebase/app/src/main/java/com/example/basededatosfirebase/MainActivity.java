package com.example.basededatosfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView labelId;
    private TextView labelName;
    private TextView labelDireccion;
    private TextView labelCel;
    private EditText identificacion;
    private EditText nombre;
    private EditText direccion;
    private EditText celular;
    private Button btnRegistrar;
    private Button btnConsultar;
    private Button btnActualizar;
    private Button btnEliminar;
    private DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = FirebaseDatabase.getInstance().getReference();
        identificacion = findViewById(R.id.campo_id);
        nombre = findViewById(R.id.campo_name);
        direccion = findViewById(R.id.campo_direc);
        celular = findViewById(R.id.campo_cel);
        labelId = findViewById(R.id.ident);
        labelName = findViewById(R.id.name);
        labelDireccion = findViewById(R.id.direccion);
        labelCel = findViewById(R.id.cel);
        btnRegistrar = findViewById(R.id.buttonAdd);
        btnConsultar = findViewById(R.id.buttonConsult);
        btnActualizar = findViewById(R.id.buttonUpdate);
        btnEliminar = findViewById(R.id.buttonDelete);
    }

    public void registrar(View view){
        if(identificacion.getVisibility() == View.GONE){
            labelId.setVisibility(View.VISIBLE);
            labelName.setVisibility(View.VISIBLE);
            labelDireccion.setVisibility(View.VISIBLE);
            labelCel.setVisibility(View.VISIBLE);
            identificacion.setVisibility(View.VISIBLE);
            nombre.setVisibility(View.VISIBLE);
            direccion.setVisibility(View.VISIBLE);
            celular.setVisibility(View.VISIBLE);
            btnConsultar.setVisibility(View.GONE);
            btnActualizar.setVisibility(View.GONE);
            btnEliminar.setVisibility(View.GONE);
        }else {
            String idUser = identificacion.getText().toString();
            String name = nombre.getText().toString();
            String dir = direccion.getText().toString();
            String cel = celular.getText().toString();
            if (idUser.isEmpty() | idUser.length() < 10) {
                Toast.makeText(this, getString(R.string.msg_error_id), Toast.LENGTH_SHORT).show();
            } else if (name.isEmpty() | name.length() < 10) {
                Toast.makeText(this, getString(R.string.msg_error_name), Toast.LENGTH_SHORT).show();
            } else if (dir.isEmpty() | dir.length() < 15) {
                Toast.makeText(this, getString(R.string.msg_error_direccion), Toast.LENGTH_SHORT).show();
            } else if (cel.isEmpty() | cel.length() < 10) {
                Toast.makeText(this, getString(R.string.msg_error_cel), Toast.LENGTH_SHORT).show();
            } else {
                Registro user = new Registro(idUser, name, dir, cel);
                dataBase.child("Registros").child(idUser).setValue(user);
                Toast.makeText(this, getString(R.string.msg_registrar), Toast.LENGTH_SHORT).show();
                labelId.setVisibility(View.GONE);
                labelName.setVisibility(View.GONE);
                labelDireccion.setVisibility(View.GONE);
                labelCel.setVisibility(View.GONE);
                identificacion.setText("");
                nombre.setText("");
                direccion.setText("");
                celular.setText("");
                identificacion.setVisibility(View.GONE);
                nombre.setVisibility(View.GONE);
                celular.setVisibility(View.GONE);
                direccion.setVisibility(View.GONE);
                btnConsultar.setVisibility(View.VISIBLE);
                btnEliminar.setVisibility(View.VISIBLE);
                btnActualizar.setVisibility(View.VISIBLE);
            }
        }
    }

    public void consultar(View view){
        if(identificacion.getVisibility() == View.GONE){
            labelId.setVisibility(View.VISIBLE);
            identificacion.setVisibility(View.VISIBLE);
            btnRegistrar.setVisibility(View.GONE);
            btnActualizar.setVisibility(View.GONE);
            btnEliminar.setVisibility(View.GONE);
        }else {
            String idUser = identificacion.getText().toString();
            if (idUser.isEmpty() | idUser.length() < 10) {
                Toast.makeText(this, getString(R.string.msg_error_id), Toast.LENGTH_SHORT).show();
            } else {
                Query query = dataBase.child("Registros").equalTo(idUser);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Toast.makeText(MainActivity.this, getString(R.string.msg_consultar), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, getString(R.string.msg_error), Toast.LENGTH_SHORT).show();
                    }
                });
                labelId.setVisibility(View.GONE);
                identificacion.setText("");
                identificacion.setVisibility(View.GONE);
                btnRegistrar.setVisibility(View.VISIBLE);
                btnActualizar.setVisibility(View.VISIBLE);
                btnEliminar.setVisibility(View.VISIBLE);
            }
        }
    }

    public void actualizar(View view){
        if(identificacion.getVisibility() == View.GONE){
            labelId.setVisibility(View.VISIBLE);
            labelName.setVisibility(View.VISIBLE);
            labelDireccion.setVisibility(View.VISIBLE);
            labelCel.setVisibility(View.VISIBLE);
            identificacion.setVisibility(View.VISIBLE);
            nombre.setVisibility(View.VISIBLE);
            direccion.setVisibility(View.VISIBLE);
            celular.setVisibility(View.VISIBLE);
            btnConsultar.setVisibility(View.GONE);
            btnRegistrar.setVisibility(View.GONE);
            btnEliminar.setVisibility(View.GONE);
        }else {
            String idUser = identificacion.getText().toString();
            String name = nombre.getText().toString();
            String dir = direccion.getText().toString();
            String cel = celular.getText().toString();

            if (idUser.isEmpty() | idUser.length() < 10) {
                Toast.makeText(this, getString(R.string.msg_error_id), Toast.LENGTH_SHORT).show();
            } else if (name.isEmpty() | name.length() < 10) {
                Toast.makeText(this, getString(R.string.msg_error_name), Toast.LENGTH_SHORT).show();
            } else if (dir.isEmpty() | dir.length() < 15) {
                Toast.makeText(this, getString(R.string.msg_error_direccion), Toast.LENGTH_SHORT).show();
            } else if (cel.isEmpty() | cel.length() < 10) {
                Toast.makeText(this, getString(R.string.msg_error_cel), Toast.LENGTH_SHORT).show();
            } else {
                dataBase.child("Registros").child(idUser).child("nombre").setValue(name);
                dataBase.child("Registros").child(idUser).child("direccion").setValue(dir);
                dataBase.child("Registros").child(idUser).child("celular").setValue(cel);
                Toast.makeText(this, getString(R.string.msg_actualizar), Toast.LENGTH_SHORT).show();
                labelId.setVisibility(View.GONE);
                labelName.setVisibility(View.GONE);
                labelDireccion.setVisibility(View.GONE);
                labelCel.setVisibility(View.GONE);
                identificacion.setText("");
                nombre.setText("");
                direccion.setText("");
                celular.setText("");
                identificacion.setVisibility(View.GONE);
                nombre.setVisibility(View.GONE);
                celular.setVisibility(View.GONE);
                direccion.setVisibility(View.GONE);
                btnConsultar.setVisibility(View.VISIBLE);
                btnEliminar.setVisibility(View.VISIBLE);
                btnRegistrar.setVisibility(View.VISIBLE);
            }
        }
    }

    public void eliminar(View view){
        if(identificacion.getVisibility() == View.GONE){
            labelId.setVisibility(View.VISIBLE);
            identificacion.setVisibility(View.VISIBLE);
            btnRegistrar.setVisibility(View.GONE);
            btnActualizar.setVisibility(View.GONE);
            btnConsultar.setVisibility(View.GONE);
        }else {
            String idUser = identificacion.getText().toString();
            if (idUser.isEmpty() | idUser.length() < 10) {
                Toast.makeText(this, getString(R.string.msg_error_id), Toast.LENGTH_SHORT).show();
            } else {
                dataBase.child("Registros").child(idUser).removeValue();
                Toast.makeText(this, getString(R.string.msg_eliminar), Toast.LENGTH_SHORT).show();
                labelId.setVisibility(View.GONE);
                identificacion.setText("");
                identificacion.setVisibility(View.GONE);
                btnRegistrar.setVisibility(View.VISIBLE);
                btnActualizar.setVisibility(View.VISIBLE);
                btnConsultar.setVisibility(View.VISIBLE);
            }
        }
    }
}
