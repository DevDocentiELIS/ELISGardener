package org.elis.gardener.model;

import java.io.Serializable;

public class Plant implements Serializable {

    private String name;
    private String type;
    private String plantingPeriod;
    private String suggestions;
    private String warnings;
    private String cureCheckList;

    private String waterNeedsScale;
    private String cureNeedsScale;
    private String fragilityScale;
    private String grownEasynessScale;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlantingPeriod() {
        return plantingPeriod;
    }

    public void setPlantingPeriod(String plantingPeriod) {
        this.plantingPeriod = plantingPeriod;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getCureCheckList() {
        return cureCheckList;
    }

    public void setCureCheckList(String cureCheckList) {
        this.cureCheckList = cureCheckList;
    }

    public String getWaterNeedsScale() {
        return waterNeedsScale;
    }

    public void setWaterNeedsScale(String waterNeedsScale) {
        this.waterNeedsScale = waterNeedsScale;
    }

    public String getCureNeedsScale() {
        return cureNeedsScale;
    }

    public void setCureNeedsScale(String cureNeedsScale) {
        this.cureNeedsScale = cureNeedsScale;
    }

    public String getFragilityScale() {
        return fragilityScale;
    }

    public void setFragilityScale(String fragilityScale) {
        this.fragilityScale = fragilityScale;
    }

    public String getGrownEasynessScale() {
        return grownEasynessScale;
    }

    public void setGrownEasynessScale(String grownEasynessScale) {
        this.grownEasynessScale = grownEasynessScale;
    }
}