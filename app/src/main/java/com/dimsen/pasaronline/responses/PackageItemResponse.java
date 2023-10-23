package com.dimsen.pasaronline.responses;

import com.dimsen.pasaronline.data.PackageItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PackageItemResponse {

    @SerializedName(value = "data")
    @Expose
    private ArrayList<PackageItem> dataPackageItem;

    public ArrayList<PackageItem> getDataPackageItem() {
        return dataPackageItem;
    }

    @Override
    public String toString() {
        return "PackageItemResponse{" +
                "dataPackageItem=" + dataPackageItem +
                '}';
    }
}
