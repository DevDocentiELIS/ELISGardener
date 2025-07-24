package org.elis.gardener;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.elis.gardener.model.Plant;

public class PlantsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_plants_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Plant plant = (Plant) getIntent().getSerializableExtra("plant");

        TextView tvPlantName = findViewById(R.id.tvPlantName);
        TextView tvIconType = findViewById(R.id.tvIconType);
        TextView tvPlantingPeriod = findViewById(R.id.tvPlantingPeriod);
        TextView tvSuggestions = findViewById(R.id.tvSuggestions);
        TextView tvWarnings = findViewById(R.id.tvWarnings);
        TextView tvCureChecklist = findViewById(R.id.tvCureChecklist);
        TextView tvWaterNeeds = findViewById(R.id.tvWaterNeeds);
        TextView tvCureNeeds = findViewById(R.id.tvCureNeeds);
        TextView tvFragility = findViewById(R.id.tvFragility);
        TextView tvEaseOfGrowth = findViewById(R.id.tvEaseOfGrowth);

        ImageView ivType= findViewById(R.id.iconType);
        ImageView ivWaterNeeds = findViewById(R.id.iconWater);
        ImageView ivCureNeeds = findViewById(R.id.iconCure);
        ImageView ivFragility = findViewById(R.id.iconFragility);
        ImageView ivEaseOfGrowth = findViewById(R.id.iconGrowth);

        tvPlantName.setText(plant.getName());
        tvIconType.setText(plant.getType());
        tvPlantingPeriod.setText(plant.getPlantingPeriod());
        tvSuggestions.setText(plant.getSuggestions());
        tvWarnings.setText(plant.getWarnings());
        tvCureChecklist.setText(plant.getCureCheckList());

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
                ivEaseOfGrowth.setImageResource(R.drawable.ic_easygrow_u);
                break;
            case "Medio":
                ivEaseOfGrowth.setImageResource(R.drawable.ic_easygrow_d);
                break;
            case "Difficile":
                ivEaseOfGrowth.setImageResource(R.drawable.ic_easygrow_t);
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