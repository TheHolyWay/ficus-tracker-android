package ru.holyway.ficustracker.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.holyway.ficustracker.R;
import ru.holyway.ficustracker.entity.FlowerData;


public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.FlowerViewHolder> implements View.OnClickListener {

    private final List<FlowerData> flowers;

    private OnItemClickListener onItemClickListener;


    public FlowersAdapter(List<FlowerData> flowers) {
        this.flowers = flowers;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_item, parent, false);
        v.setOnClickListener(this);
        FlowerViewHolder pvh = new FlowerViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {

        final FlowerData flowerData = flowers.get(position);

        holder.flowerName.setText(flowerData.getName());
        holder.flowerType.setText(flowerData.getType());
        holder.humidity.setText(flowerData.getSensorData().getSoilMoisture() + "%");
        holder.temperature.setText(flowerData.getSensorData().getTemperature() + "°C");
        holder.light.setText(flowerData.getSensorData().getLight() + " lm");

        if (flowerData.getProblems() != null && !flowerData.getProblems().isEmpty()) {
            holder.warning.setImageResource(R.drawable.warning_icon);
        } else if (flowerData.getWarnings() != null && !flowerData.getWarnings().isEmpty()) {
            holder.warning.setImageResource(R.drawable.warning_easy_icon);
        } else if (flowerData.getRecommendations() != null && !flowerData.getRecommendations().isEmpty()) {
            holder.warning.setImageResource(R.drawable.ic_information);
        } else {
            holder.warning.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setTag(flowers.get(position));
    }

    @Override
    public int getItemCount() {
        return flowers.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        onItemClickListener.onItemClick(view, (FlowerData) view.getTag());
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

    public interface OnItemClickListener {
        void onItemClick(View view, FlowerData flower);
    }
}

