package pt.ua.nextweather.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;
import pt.ua.nextweather.network.CityResultsObserver;
import pt.ua.nextweather.network.ForecastForACityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;
import pt.ua.nextweather.network.WeatherTypesResultsObserver;

public class MainActivity extends AppCompatActivity {

    private TextView feedback;

    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    private HashMap<Integer, WeatherType> weatherDescriptions;
    private boolean DualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        callWeatherForecastForACityList();
        //Começar Fragment
        feedback = findViewById(R.id.tvFeedback);

        //Check orientation
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("DualPane", "landscape");
            DualPane = true;
        } else {
            Log.d("DualPane", "vertical");
            DualPane=false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class OnClickCity implements View.OnClickListener, Serializable, Parcelable {
        static final long serialVersionUID = 42L;
        @Override
        public void onClick(View v) {
            String city = ((TextView)((LinearLayout) v).getChildAt(0)).getText().toString();
            City Mcity = cities.get(city);
            Log.d("Onclick",city);
            client.retrieveForecastForCity(Mcity.getGlobalIdLocal(), new ForecastForACityResultsObserver() {
                @Override
                public void receiveForecastList(List<Weather> forecast) {
                    if(DualPane){
                        View vx = findViewById(R.id.your_placeholder2);
                        vx.setVisibility(vx.VISIBLE);
                        Fragment x = Fragment_B_info.newInstance(city, forecast,weatherDescriptions);
                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        //Fragment x = FragmentA_list.newInstance(cars);
                        //ft.replace(R.id.your_placeholder, Fragment_B_info.newInstance("Porto"));
                        ft2.replace(R.id.your_placeholder2, x);
                        // or ft.add(R.id.your_placeholder, new FooFragment());
                        // Complete the changes added above
                        ft2.commit();
                    }
                    else{
                        weatherDetails(city, forecast, weatherDescriptions);
                     }}

                    @Override
                    public void onFailure(Throwable cause) {

                    }});
        }
        @Override
        public int describeContents() { return 0; }
        @Override
        public void writeToParcel(Parcel parcel, int i) { }
    }

    //
    public void weatherDetails(String city, List<Weather> forecast, HashMap<Integer, WeatherType> weatherDescriptions){
        Intent myIntent = new Intent(this, ActivityB.class);
        Bundle b = new Bundle();
        myIntent.putExtra("string", city); //Optional parameters
        b.putSerializable("fore", (Serializable) forecast);
        b.putSerializable("details", (Serializable) weatherDescriptions);
        myIntent.putExtra("data",b);
        startActivity(myIntent);
    }


    private void callWeatherForecastForACityList(){
        client.retrieveCitiesList(new CityResultsObserver() {
            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                MainActivity.this.cities = citiesCollection;
                // Begin the transaction
                // Replace the contents of the container with the new fragment
                Fragment x = Fragment_A_list.newInstance(new ArrayList<>(cities.keySet()), new OnClickCity());
                //Fragment x = FragmentA_list.newInstance(cars);
                //ft.replace(R.id.your_placeholder, Fragment_B_info.newInstance("Porto"));
                ft.replace(R.id.your_placeholder, x);
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append("Failed to get cities list!");
            }
        });
    }
    //


    //Funções de apoio para a Api

    private void callWeatherForecastForACityStep1(String city) {

        feedback.append("\nGetting forecast for: " + city); feedback.append("\n");

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                MainActivity.this.weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2( city);
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append("Failed to get weather conditions!");
            }
        });

    }

    private void callWeatherForecastForACityStep2(String city) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                MainActivity.this.cities = citiesCollection;
                City cityFound = cities.get(city);
                if( null != cityFound) {
                    int locationId = cityFound.getGlobalIdLocal();
                    callWeatherForecastForACityStep3(locationId);
                } else {
                    feedback.append("unknown city: " + city);
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                feedback.append("Failed to get cities list!");
            }
        });
    }

    private void callWeatherForecastForACityStep3(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                for (Weather day : forecast) {
                    feedback.append(day.toString());
                    feedback.append("\t");
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append( "Failed to get forecast for 5 days");
            }
        });

    }
    private void callWeatherDescription() {
        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                MainActivity.this.weatherDescriptions = descriptorsCollection;
            }
            @Override
            public void onFailure(Throwable cause) {
            }
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("citylist",cities);
    }



}
