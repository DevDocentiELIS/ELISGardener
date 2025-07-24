package org.elis.gardener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.common.util.concurrent.FutureCallback;
import com.google.firebase.ai.type.GenerateContentResponse;

import org.elis.gardener.model.PlantsResponse;
import org.elis.gardener.logic.LocationHelper;
import org.elis.gardener.logic.PermissionHelper;
import org.elis.gardener.ui.PlantsAdapter;

public class MainActivity extends AppCompatActivity implements FutureCallback<GenerateContentResponse> {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private AlertDialog dialog;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        showLottieDialog(this);
        new PermissionHelper().askLocationPermissions(this,
                () -> new LocationHelper().sendLocationToGemini(this));
    }

    @Override
    public void onSuccess(GenerateContentResponse result) {
        String resultText = result.getText();
        System.out.println(resultText);
        ObjectMapper mapper = new ObjectMapper();
        try {
            PlantsResponse plantsResponse = mapper.readValue(resultText, PlantsResponse.class);
            runOnUiThread(() -> {
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setAdapter(new PlantsAdapter(plantsResponse.getPlants()));
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                dismissDialog();
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFailure(@NonNull Throwable t) {
        t.printStackTrace();
        Log.e("MainActivity", "Error: " + t.getMessage());
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                new LocationHelper().sendLocationToGemini(this);
            }
        }
    }
    private void showLottieDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.loading_dialog, null);
        LottieAnimationView lottieView = view.findViewById(R.id.lottieView);
        TextView title = view.findViewById(R.id.dialogTitle);
        TextView message = view.findViewById(R.id.dialogMessage);
        title.setText("Caricamento...");
        message.setText("Stiamo cercando le piante migliori per la tua posizione");
        lottieView.setRepeatCount(LottieDrawable.INFINITE);
        lottieView.playAnimation();
        dialog = new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setCancelable(false)
                .create();
        dialog.show();
    }
    private void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}