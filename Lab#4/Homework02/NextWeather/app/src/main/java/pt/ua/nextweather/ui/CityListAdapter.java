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
    private View.OnClickListener onclicklistener;

    public CityListAdapter(Context context,ArrayList<String> mCityList, View.OnClickListener onclick) {
        this.mCityList = mCityList;
        mInflater=LayoutInflater.from(context);
        onclicklistener = onclick;
    }


    class CityViewHolder extends RecyclerView.ViewHolder{
        public final TextView CityItemView;
        final CityListAdapter mAdapter;
        public CityViewHolder(@NonNull View itemView, CityListAdapter mAdapter) {
            super(itemView);
            CityItemView = itemView.findViewById(R.id.city);
            this.mAdapter = mAdapter;
        }
    }
    @NonNull
    @Override
    public CityListAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.citylist_item, parent, false);
        mItemView.setOnClickListener(onclicklistener);
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
