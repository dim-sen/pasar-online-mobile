package com.dimsen.pasaronline.responses;

import com.dimsen.pasaronline.data.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemsResponse {

    @SerializedName(value = "data")
    @Expose
    private ArrayList<Item> dataItems;

    public ArrayList<Item> getDataItems() {
        return dataItems;
    }

    @Override
    public String toString() {
        return "ItemsResponse{" +
                "dataItems=" + dataItems +
                '}';
    }
}
