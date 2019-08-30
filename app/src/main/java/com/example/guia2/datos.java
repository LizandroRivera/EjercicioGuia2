package com.example.guia2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class datos extends AppCompatActivity implements View.OnClickListener {

    TextView progreso;
    AutoCompleteTextView actAnimal, actFruta, actLenguaje;
    ProgressBar barProgreso;
    Button btnProcesar;
    String [] listaAnimales = {"Leon", "Jirafa", "Oso", "Tigre", "Perro"};
    String [] listaFrutas = {"Manzana", "Sandía", "Naranja", "Uvas", "Melon"};
    String [] listaLenguajes = {"C++", "Java", "C#", "Python", "PHP"};

    private int estado = 0;
    Handler controlador = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Funciona xD");
        }

        actFruta = findViewById(R.id.fruta);
        actLenguaje = findViewById(R.id.lenguaje);
        actAnimal = findViewById(R.id.animal);
        barProgreso = findViewById(R.id.barra);
        btnProcesar = findViewById(R.id.procesar);
        progreso = findViewById(R.id.porcentaje);

        ArrayAdapter<String> animales = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listaAnimales);
        ArrayAdapter<String> frutas = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listaFrutas);
        ArrayAdapter<String> lenguajes = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listaLenguajes);

        actAnimal.setThreshold(1);
        actAnimal.setAdapter(animales);

        actFruta.setThreshold(1);
        actFruta.setAdapter(frutas);

        actLenguaje.setThreshold(1);
        actLenguaje.setAdapter(lenguajes);

        btnProcesar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.procesar: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(estado < 100){
                            estado++;
                            android.os.SystemClock.sleep(50);

                            controlador.post(new Runnable() {
                                @Override
                                public void run() {
                                    barProgreso.setProgress(estado);
                                    progreso.setText(estado+"%");
                                }
                            });
                        }

                        controlador.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(datos.this, "Usted ha seleccionado:"+"\n"+actFruta.getText().toString()+"\n"+
                                        actAnimal.getText().toString()+"\n"+ actLenguaje.getText().toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).start();
            }
            break;
        }
    }
}


