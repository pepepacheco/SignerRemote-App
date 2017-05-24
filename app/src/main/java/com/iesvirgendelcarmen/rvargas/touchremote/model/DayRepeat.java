package com.iesvirgendelcarmen.rvargas.touchremote.model;
import com.google.gson.annotations.SerializedName;

public class DayRepeat {

    @SerializedName("description")
    private String description;

    public DayRepeat(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description.toUpperCase();
    }
}
