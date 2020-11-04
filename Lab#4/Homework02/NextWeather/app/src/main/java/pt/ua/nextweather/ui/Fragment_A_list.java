package pt.ua.nextweather.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pt.ua.nextweather.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_A_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_A_list extends Fragment {


    private RecyclerView mRecyclerView;
    private CityListAdapter mAdapter;
    private static final String CITY_LIST = "list";
    private ArrayList<String> mCityList;

    public Fragment_A_list() {
        // Required empty public constructor
    }

    public static Fragment_A_list newInstance(ArrayList<String> mCityList) {
        Fragment_A_list fragment = new Fragment_A_list();
        Bundle args = new Bundle();
        args.putStringArrayList(CITY_LIST,mCityList);
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCityList =getArguments().getStringArrayList(CITY_LIST);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a_list, container, false);
        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new CityListAdapter(view.getContext(), mCityList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return  view;
    }


}