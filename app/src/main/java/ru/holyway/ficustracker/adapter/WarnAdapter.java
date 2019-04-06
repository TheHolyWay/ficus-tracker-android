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
import ru.holyway.ficustracker.entity.WarnItem;


public class WarnAdapter extends RecyclerView.Adapter<WarnAdapter.WarnViewHolder> {

    private final List<WarnItem> warnItems;


    public WarnAdapter(List<WarnItem> warnItems) {
        this.warnItems = warnItems;
    }

    @NonNull
    @Override
    public WarnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.warn_item, parent, false);
        WarnViewHolder pvh = new WarnViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull WarnViewHolder holder, int position) {

        final WarnItem warnItem = warnItems.get(position);

        holder.warningText.setText(warnItem.getText());

        if (warnItem.getType() == WarnItem.Type.PROBLEM) {
            holder.warningImage.setImageResource(R.drawable.warning_icon);
        } else if (warnItem.getType() == WarnItem.Type.WARNING) {
            holder.warningImage.setImageResource(R.drawable.warning_easy_icon);
        } else if (warnItem.getType() == WarnItem.Type.NOTIFY) {
            holder.warningImage.setImageResource(R.drawable.ic_information);
        }
        holder.itemView.setTag(warnItems.get(position));
    }

    @Override
    public int getItemCount() {
        return warnItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class WarnViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView warningText;
        ImageView warningImage;


        WarnViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            warningText = itemView.findViewById(R.id.warn_text);
            warningImage = itemView.findViewById(R.id.warning_icon);
        }
    }
}

