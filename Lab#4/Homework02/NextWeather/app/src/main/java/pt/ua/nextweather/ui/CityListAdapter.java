package pt.ua.nextweather.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.ua.nextweather.R;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private final ArrayList<String> mCityList;
    private LayoutInflater mInflater;

    public CityListAdapter(Context context,ArrayList<String> mCityList) {
        this.mCityList = mCityList;
        mInflater=LayoutInflater.from(context);
    }


    class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView CityItemView;
        final CityListAdapter mAdapter;
        public CityViewHolder(@NonNull View itemView, CityListAdapter mAdapter) {
            super(itemView);
            CityItemView = itemView.findViewById(R.id.city);
            this.mAdapter = mAdapter;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            String element = mCityList.get(mPosition);
            Log.d("click",element);
            //view.getContext().startActivity(new Intent(view.getContext(), ActivityB.class));


        }
    }

    @NonNull
    @Override
    public CityListAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.citylist_item, parent, false);
        return new CityViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CityListAdapter.CityViewHolder holder, int position) {
        String mCurrent = mCityList.get(position);
        holder.CityItemView.setText(mCurrent);

    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }
}
