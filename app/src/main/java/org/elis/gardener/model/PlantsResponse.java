package org.elis.gardener.model;

import java.util.List;

public class PlantsResponse {
    private String locationCity;
    private String locationRegion;
    private String locationNation;
    private List<Plant> plants;

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationRegion() {
        return locationRegion;
    }

    public void setLocationRegion(String locationRegion) {
        this.locationRegion = locationRegion;
    }

    public String getLocationNation() {
        return locationNation;
    }

    public void setLocationNation(String locationNation) {
        this.locationNation = locationNation;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
