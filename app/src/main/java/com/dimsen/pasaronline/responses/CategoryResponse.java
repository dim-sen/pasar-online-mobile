package com.dimsen.pasaronline.responses;

import com.dimsen.pasaronline.data.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {

    @SerializedName(value = "data")
    @Expose
    private ArrayList<Category> dataCategory;

    public ArrayList<Category> getDataCategory() {
        return dataCategory;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "dataCategory=" + dataCategory +
                '}';
    }
}
