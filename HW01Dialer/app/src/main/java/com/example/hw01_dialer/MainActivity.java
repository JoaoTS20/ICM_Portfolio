package com.example.hw01_dialer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{
    private static final int _ACTIVITY_REQUEST_CODE_bbt1 = 1;
    private static final int _ACTIVITY_REQUEST_CODE_bbt2 = 2;
    private static final int _ACTIVITY_REQUEST_CODE_bbt3 = 3;

    private Button empty1, empty2, empty3;

    private HashMap<String, String  > speedNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        speedNumbers = new HashMap<String, String>();

        empty1 = (Button) findViewById(R.id.empty1);
        empty2 = (Button) findViewById(R.id.empty2);
        empty3 = (Button) findViewById(R.id.empty3);

        empty1.setOnLongClickListener(this);
        empty2.setOnLongClickListener(this);
        empty3.setOnLongClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==_ACTIVITY_REQUEST_CODE_bbt1){
            speedNumbers.put(data.getStringExtra("resultNome"), data.getStringExtra("resultNumero"));
            String nome1=data.getStringExtra("resultNome");
            empty1.setText(nome1);
        }
        else if(requestCode==_ACTIVITY_REQUEST_CODE_bbt2) {
            speedNumbers.put(data.getStringExtra("resultNome"), data.getStringExtra("resultNumero"));
            String nome2=data.getStringExtra("resultNome");
            empty2.setText(nome2);
        }
        else if(requestCode==_ACTIVITY_REQUEST_CODE_bbt3) {
            speedNumbers.put(data.getStringExtra("resultNome"), data.getStringExtra("resultNumero"));
            String nome3=data.getStringExtra("resultNome");
            empty3.setText(nome3);
        }
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
    //Função para realizar chamada via Speed Contacts
    public void speed_call(View view){
        Button b =(Button)view;
        String buttonText =b.getText().toString();
        Intent intent= new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+speedNumbers.get(buttonText)));
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

    //Editar speed dial
    @Override
    public boolean onLongClick(View v) {
        switch(v.getId()){
            case R.id.empty1:
                String name1= empty1.getText().toString();
                Intent intent = new Intent(this,SpeedDialConf.class);
                intent.putExtra("name",name1);
                if(speedNumbers.containsKey(name1)){
                    intent.putExtra("number", speedNumbers.get(name1));
                }
                else{
                    intent.putExtra("number", "");
                }
                startActivityForResult(intent,_ACTIVITY_REQUEST_CODE_bbt1);

                break;
            case R.id.empty2:
                String name2= empty2.getText().toString();
                Intent intent2 = new Intent(this,SpeedDialConf.class);
                intent2.putExtra("name",name2);
                if(speedNumbers.containsKey(name2)){
                    intent2.putExtra("number", speedNumbers.get(name2));
                }
                else{
                    intent2.putExtra("number", "");
                }
                startActivityForResult(intent2,_ACTIVITY_REQUEST_CODE_bbt2);
                break;
            case R.id.empty3:
                String name3= empty3.getText().toString();
                Intent intent3 = new Intent(this,SpeedDialConf.class);
                intent3.putExtra("name",name3);
                if(speedNumbers.containsKey(name3)){
                    intent3.putExtra("number", speedNumbers.get(name3));
                }
                else{
                    intent3.putExtra("number", "");
                }
                startActivityForResult(intent3,_ACTIVITY_REQUEST_CODE_bbt3);
                break;
            default:
                break;
        }
        return false;
    }
}
