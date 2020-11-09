package pt.ua.nextweather.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;

public class ActivityB extends AppCompatActivity {
    private FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    private List<Weather> forecast;
    private HashMap<Integer, WeatherType> weatherdescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Intent intent = getIntent();
        String city = intent.getStringExtra("string");
        Bundle b = intent.getBundleExtra("data");
        forecast = (List<Weather>)b.getSerializable("fore");
        weatherdescriptions = (HashMap<Integer, WeatherType>) b.getSerializable("details");
        Fragment x = Fragment_B_info.newInstance(city,forecast,weatherdescriptions);
        ft.replace(R.id.your_placeholder2, x);
        // Complete the changes added above
        ft.commit();
    }
}