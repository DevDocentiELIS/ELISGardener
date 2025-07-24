package org.elis.gardener.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.elis.gardener.R;
import org.elis.gardener.model.Plant;

import java.util.List;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsViewHolder> {
    private List<Plant> plants;
    public PlantsAdapter(List<Plant> plants) {
        this.plants = plants;
    }

    @NonNull
    @Override
    public PlantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plants_row, parent, false);
        return  new PlantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantsViewHolder holder, int position) {
        holder.setPlant(plants.get(position));
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }
}
