package com.example.hw01_dialer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Função escrever número
    public void sendNumber(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        TextView tv1 = (TextView)findViewById(R.id.textView);
        tv1.append(buttonText);
    }
    //Função para realizar chamada
    public void call(View view){
        TextView tv1 = (TextView)findViewById(R.id.textView);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+ tv1.getText()));
        startActivity(intent);
    }
    //Função para limpar último número
    public void clear(View view){
        TextView tv1 = (TextView)findViewById(R.id.textView);
        String s=tv1.getText().toString();
        if(s.length()>0) { //Evitar Erro
            tv1.setText(s.substring(0, s.length() - 1));
        }
    }

    public void speed_dial(View view){

    }
}