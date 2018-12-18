package com.htw.githubrepofinder.model;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RootSearchResponse {

    @SerializedName("items")
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

}
