package com.dimsen.pasaronline.utils;

import com.dimsen.pasaronline.responses.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CategoryApi {

    @Headers(value = "Content-Type:application/json")
    @GET(value = "category")
    Call<CategoryResponse> CATEGORY_RESPONSE_CALL();
}
