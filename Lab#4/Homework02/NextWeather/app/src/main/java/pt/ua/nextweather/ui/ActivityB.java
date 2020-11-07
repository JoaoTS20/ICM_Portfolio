package pt.ua.nextweather.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import pt.ua.nextweather.R;

public class ActivityB extends AppCompatActivity {
    private FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Intent intent = getIntent();
        String city = intent.getStringExtra("string");
        Fragment x = Fragment_B_info.newInstance(city);
        //Fragment x = FragmentA_list.newInstance(cars);
        //ft.replace(R.id.your_placeholder, Fragment_B_info.newInstance("Porto"));
        ft.replace(R.id.your_placeholder2, x);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }
}