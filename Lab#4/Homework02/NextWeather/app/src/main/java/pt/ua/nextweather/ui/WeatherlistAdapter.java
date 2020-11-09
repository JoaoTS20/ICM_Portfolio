package pt.ua.nextweather.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.Weather;

public class WeatherlistAdapter extends
        RecyclerView.Adapter<WeatherlistAdapter.WeatherViewHolder>{
    private final List<Weather> tempo;
    private LayoutInflater mInflater;
    //private View.OnClickListener onclicklistener;

    public WeatherlistAdapter(Context context, List<Weather> tempo ) {
        this.tempo = tempo;
        mInflater=LayoutInflater.from(context);
        //onclicklistener = onclick;
    }


    class WeatherViewHolder extends RecyclerView.ViewHolder{
        public final TextView WeatherItemView;
        final WeatherlistAdapter mAdapter;
        public WeatherViewHolder(@NonNull View itemView, WeatherlistAdapter mAdapter) {
            super(itemView);
            WeatherItemView = itemView.findViewById(R.id.textView1);
            this.mAdapter = mAdapter;
        }
    }
    @NonNull
    @Override
    public WeatherlistAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.weatherlist_info, parent, false);
        //mItemView.setOnClickListener(onclicklistener);
        return new WeatherlistAdapter.WeatherViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherlistAdapter.WeatherViewHolder holder, int position) {
        Weather mCurrent = tempo.get(position);
        holder.WeatherItemView.setText(mCurrent.toString());
    }

    @Override
    public int getItemCount() {
        return tempo.size();
    }



}
