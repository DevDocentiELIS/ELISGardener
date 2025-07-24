package org.elis.gardener.ui;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.elis.gardener.PlantsDetailsActivity;
import org.elis.gardener.R;
import org.elis.gardener.model.Plant;

public class PlantsViewHolder extends RecyclerView.ViewHolder {

    public PlantsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setPlant(Plant plant) {
        itemView.setOnClickListener(v->{
            Intent intent = new Intent(itemView.getContext(), PlantsDetailsActivity.class);
            intent.putExtra("plant",plant);
            startActivity(itemView.getContext(),intent,null);
        });

        TextView tvPlantName = itemView.findViewById(R.id.tvPlantName);
        TextView tvPlantingPeriod = itemView.findViewById(R.id.tvPlantingPeriod);
        TextView tvIconType = itemView.findViewById(R.id.tvIconType);
        ImageView ivType = itemView.findViewById(R.id.iconType);
        ImageView ivWaterNeeds = itemView.findViewById(R.id.iconWater);
        ImageView ivCureNeeds = itemView.findViewById(R.id.iconCure);
        ImageView ivFragility = itemView.findViewById(R.id.iconFragility);
        ImageView ivEaseOfGrowth = itemView.findViewById(R.id.iconGrowth);

        tvPlantName.setText(plant.getName());
        tvPlantingPeriod.setText(plant.getPlantingPeriod());
        tvIconType.setText(plant.getType());
        switch(plant.getFragilityScale()){
            case "Poca":
                ivFragility.setImageResource(R.drawable.ic_frag_u);
                break;
            case "Media":
                ivFragility.setImageResource(R.drawable.ic_frag_d);
                break;
            case "Molta":
                ivFragility.setImageResource(R.drawable.ic_frag_t);
                break;
        }
        switch(plant.getCureNeedsScale()){
            case "Poca":
                ivCureNeeds.setImageResource(R.drawable.ic_cure_u);
                break;
            case "Media":
                ivCureNeeds.setImageResource(R.drawable.ic_cure_d);
                break;
            case "Molta":
                ivCureNeeds.setImageResource(R.drawable.ic_cure_t);
                break;
        }
        switch(plant.getWaterNeedsScale()){
            case "Poca":
                ivWaterNeeds.setImageResource(R.drawable.ic_droplet_u);
                break;
            case "Media":
                ivWaterNeeds.setImageResource(R.drawable.ic_droplet_d);
                break;
            case "Molta":
                ivWaterNeeds.setImageResource(R.drawable.ic_droplet_t);
                break;
        }
        switch(plant.getGrownEasynessScale()){
            case "Facile":
                ivEaseOfGrowth.setImageResource(R.drawable.ic_easygrow_d);
                break;
            case "Medio":
                ivEaseOfGrowth.setImageResource(R.drawable.ic_easygrow_t);
                break;
            case "Difficile":
                ivEaseOfGrowth.setImageResource(R.drawable.ic_easygrow_u);
                break;
        }
        switch(plant.getType()){
            case "Fiori":
                ivType.setImageResource(R.drawable.ic_flower);
                break;
            case "Ortaggi":
                ivType.setImageResource(R.drawable.ic_ort);
                break;
            case "Frutti":
                ivType.setImageResource(R.drawable.ic_fruit);
                break;
            case "Spezie":
                ivType.setImageResource(R.drawable.ic_spice);
                break;
        }
    }
}
