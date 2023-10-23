package com.dimsen.pasaronline.responses;

import com.dimsen.pasaronline.data.Package;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PackageResponse {

    @SerializedName(value = "data")
    @Expose
    private ArrayList<Package> dataPackage;

    public ArrayList<Package> getDataPackage() {
        return dataPackage;
    }

    @Override
    public String toString() {
        return "PackageResponse{" +
                "dataPackage=" + dataPackage +
                '}';
    }
}
