package com.dimsen.pasaronline.utils;

import com.dimsen.pasaronline.responses.PackageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PackageApi {

    @Headers(value = "Content-Type:application/json")
    @GET(value = "package")
    Call<PackageResponse> PACKAGE_RESPONSE_CALL();
}
