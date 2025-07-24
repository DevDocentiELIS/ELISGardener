package org.elis.gardener.logic;

import android.Manifest;

import androidx.annotation.RequiresPermission;

import com.google.android.gms.location.LocationServices;

import org.elis.gardener.MainActivity;

public class LocationHelper {

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void sendLocationToGemini(MainActivity callback) {
        LocationServices.getFusedLocationProviderClient(callback)
                .getLastLocation()
                .addOnSuccessListener(callback, location -> {
                    if (location != null) {
                        new GeminiHelper().fetchPlantData(location, callback);
                    }
                });
    }
}
