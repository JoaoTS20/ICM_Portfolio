package pt.ua.nextweather.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;

public class WeatherlistAdapter extends
        RecyclerView.Adapter<WeatherlistAdapter.WeatherViewHolder>{
    private final List<Weather> tempo;
    private LayoutInflater mInflater;
    private  HashMap<Integer, WeatherType> weatherinfo;
    //private View.OnClickListener onclicklistener;

    public WeatherlistAdapter(Context context, List<Weather> tempo,  HashMap<Integer, WeatherType> weatherinfo) {
        this.tempo = tempo;
        this.weatherinfo=weatherinfo;
        mInflater=LayoutInflater.from(context);
        //onclicklistener = onclick;
    }


    class WeatherViewHolder extends RecyclerView.ViewHolder{
        public final ImageView WeatherItemView;
        final WeatherlistAdapter mAdapter;
        public final TextView tMaxItemView;
        public final TextView tMinItemView;
        public final TextView diaItemView;
        public WeatherViewHolder(@NonNull View itemView, WeatherlistAdapter mAdapter) {
            super(itemView);
            WeatherItemView = itemView.findViewById(R.id.imgViewWeather);
            tMaxItemView = itemView.findViewById(R.id.txtTMax);
            tMinItemView = itemView.findViewById(R.id.txtTMin);
            diaItemView = itemView.findViewById(R.id.txtDia);
            this.mAdapter = mAdapter;
        }
    }
    @NonNull
    @Override
    public WeatherlistAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.weatherlist_info, parent, false);
        return new WeatherlistAdapter.WeatherViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherlistAdapter.WeatherViewHolder holder, int position) {
        Weather mCurrent = tempo.get(position);
        holder.tMaxItemView.setText(String.valueOf(mCurrent.getTMax()));
        holder.tMinItemView.setText(String.valueOf(mCurrent.getTMin()));
        holder.diaItemView.setText(mCurrent.getForecastDate());
        switch(weatherinfo.get(mCurrent.getIdWeatherType()).getDescIdWeatherTypePT()){
            case "Aguaceiros":
                holder.WeatherItemView.setImageResource(R.drawable.ic_baseline_wb_cloudy_24);
                break;
            case "Chuva":
                holder.WeatherItemView.setImageResource(R.drawable.ic_baseline_invert_colors_24);
                break;
            case "Céu nublado por nuvens altas":
                holder.WeatherItemView.setImageResource(R.drawable.ic_baseline_cloud_queue_24);
            default:
                holder.WeatherItemView.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
        }
    }

    @Override
    public int getItemCount() {
        return tempo.size();
    }



}
