package com.dimsen.pasaronline.utils;

import com.dimsen.pasaronline.responses.PackageItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PackageItemApi {

    @Headers(value = "Content-Type:application/json")
    @GET(value = "package-item/{id}")
    Call<PackageItemResponse> PACKAGE_ITEM_RESPONSE_CALL(@Path(value = "id") Long id);
}
