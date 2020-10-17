package com.example.hw01_dialer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class SpeedDialConf extends AppCompatActivity {
    private TextView nome;
    private TextView numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_dial_conf);
        //Obter dados
        String name = getIntent().getStringExtra("name");
        String number=getIntent().getStringExtra("number");
        //Set Text
        nome = (TextView)findViewById(R.id.editTextTextName);
        numero = (TextView)findViewById(R.id.editTextTextNumber);

        nome.setText(name);
        numero.setText(number);
    }
    //Função para editar
    public void Edit(View view){
        Intent returnIntent= new Intent();

        returnIntent.putExtra("resultNome",nome.getText().toString());
        returnIntent.putExtra("resultNumero",numero.getText().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();



    }
}


