package com.dimsen.pasaronline.utils;

import com.dimsen.pasaronline.data.Items;
import com.dimsen.pasaronline.responses.ItemsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ItemsApi {

    @Headers(value = "Content-Type:application/json")
    @GET(value = "item")
    Call<ItemsResponse> DATA_ITEM_CALL();
}
