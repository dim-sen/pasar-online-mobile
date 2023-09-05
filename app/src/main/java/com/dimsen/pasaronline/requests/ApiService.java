package com.dimsen.pasaronline.requests;

import com.dimsen.pasaronline.utils.Credentials;
import com.dimsen.pasaronline.utils.ItemsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ItemsApi getItemsApi() {
        return retrofit.create(ItemsApi.class);
    }
}
