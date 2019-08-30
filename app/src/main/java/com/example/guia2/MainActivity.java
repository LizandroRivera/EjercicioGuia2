package com.example.guia2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    EditText edtCorreo, edtContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnIniciar = findViewById(R.id.login);
        this.edtCorreo = findViewById(R.id.correo);
        this.edtContra = findViewById(R.id.contra);

        btnIniciar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(edtCorreo.getText().toString().isEmpty() || edtContra.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Ambos campos son obligatorios!", Toast.LENGTH_SHORT).show();
                } else{
                    abrirDatos();
                }
                return  true;
            }
        });
    }

    public  void abrirDatos(){
        Intent abrir = new Intent(this, datos.class);
        startActivity(abrir);
    }

}
