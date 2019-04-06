package ru.holyway.ficustracker.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.holyway.ficustracker.R;
import ru.holyway.ficustracker.entity.FlowerData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.FlowerViewHolder> {

    private final List<FlowerData> flowers;

    public FlowersAdapter(List<FlowerData> flowers) {
        this.flowers = flowers;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_item, parent, false);
        FlowerViewHolder pvh = new FlowerViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {

        holder.flowerName.setText(flowers.get(position).getName());
        holder.flowerType.setText(flowers.get(position).getType());
        holder.humidity.setText(flowers.get(position).getSensorData().getSoilMoisture() + "%");
        holder.temperature.setText(flowers.get(position).getSensorData().getTemperature() + "°C");
        holder.light.setText(flowers.get(position).getSensorData().getLight() + " lm");

        holder.warning.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return flowers.size();
    }

    public static class FlowerViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView flowerName;
        TextView flowerType;
        TextView humidity;
        TextView temperature;
        TextView light;
        ImageView warning;


        FlowerViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            flowerName = itemView.findViewById(R.id.flower_name);
            flowerType = itemView.findViewById(R.id.flower_type);
            humidity = itemView.findViewById(R.id.humidity);
            temperature = itemView.findViewById(R.id.temperature);
            light = itemView.findViewById(R.id.light);
            warning = itemView.findViewById(R.id.warning_icon);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

