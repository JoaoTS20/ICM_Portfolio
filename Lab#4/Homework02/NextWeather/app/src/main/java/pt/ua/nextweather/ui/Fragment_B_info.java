package pt.ua.nextweather.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_B_info#newInstance} factory method to
 * create an instance of this fragment.
 */



public class Fragment_B_info extends Fragment {


    private static final String CITY = "city";
    private static final String TEMPO = "tempo";
    private static final String TIPO_TEMPO = "tipo_tempo";

    private String city;
    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private HashMap<Integer, WeatherType> weatherDescriptions;
    private RecyclerView mRecyclerView;
    private WeatherlistAdapter mAdapter;
    private List<Weather> tempo ;
    private HashMap<Integer, WeatherType> tipo_tempo;



    public Fragment_B_info() {
        // Required empty public constructor
    }

    public static Fragment_B_info newInstance(String city, List<Weather> tempo, HashMap<Integer, WeatherType> tipo_tempo) {
        Fragment_B_info fragment = new Fragment_B_info();
        Bundle args = new Bundle();
        args.putString(CITY, city);
        args.putSerializable(TEMPO,(Serializable) tempo);
        args.putSerializable(TIPO_TEMPO,(Serializable) tipo_tempo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getString(CITY);
            tempo = (List<Weather>)getArguments().getSerializable(TEMPO);
            tipo_tempo = (HashMap<Integer, WeatherType>) getArguments().getSerializable(TIPO_TEMPO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_b_info, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerviewweather);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WeatherlistAdapter(view.getContext(), tempo);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

}