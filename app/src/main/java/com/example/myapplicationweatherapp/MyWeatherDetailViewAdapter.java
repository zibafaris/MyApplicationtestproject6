package com.example.myapplicationweatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

class MyWeatherDetailViewAdapter extends RecyclerView.Adapter<MyWeatherDetailViewAdapter.ViewHolder> {
    private List<DetailWeather> mData;
    private LayoutInflater mInflater;
    private MyWeatherViewAdapter.ItemClickListener mClickListener;

    MyWeatherDetailViewAdapter(Context context, List<DetailWeather> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public MyWeatherDetailViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_show_seven_day_row, parent, false);
        return new MyWeatherDetailViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyWeatherDetailViewAdapter.ViewHolder holder, int position) {
        String MPoster = mData.get(position).txtdeg;
        holder._txtdeg.setText(MPoster);
        String Mdayofweek = mData.get(position).dayofweek;
        holder._txtdayofweek.setText(Mdayofweek);
        String Mtxtdes = mData.get(position).txtdes;
        holder._txtdes.setText(Mtxtdes);
        switch (mData.get(position).imgShow)
        {
            case "01d":
                holder._imgShow.setImageResource(R.drawable.one);
                break;
            case "02d":
                holder._imgShow.setImageResource(R.drawable.two);
                break;
            case "03d":
                holder._imgShow.setImageResource(R.drawable.three);
                break;
            case "04d":
                holder._imgShow.setImageResource(R.drawable.four);
                break;
            case "09d":
                holder._imgShow.setImageResource(R.drawable.nine);
                break;
            case "10d":
                holder._imgShow.setImageResource(R.drawable.ten);
                break;
            case "11d":
                holder._imgShow.setImageResource(R.drawable.eleven);
                break;
            case "13d":
                holder._imgShow.setImageResource(R.drawable.thrtin);
                break;
            default:
                holder._imgShow.setImageResource(R.drawable.one);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView _txtdeg,_txtdayofweek,_txtdes;
        ImageView _imgShow;
        ViewHolder(View itemView) {
            super(itemView);

            _txtdes = itemView.findViewById(R.id.txtdes);
            _txtdayofweek = itemView.findViewById(R.id.txtdayofweek);
            _txtdeg = itemView.findViewById(R.id.txtdeg);
            _imgShow = itemView.findViewById(R.id.imgShow);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getItem(int id) {
        return mData.get(id).txtdes;
    }

    void setClickListener(MyWeatherViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}