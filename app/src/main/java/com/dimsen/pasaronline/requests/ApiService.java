package com.dimsen.pasaronline.requests;

import com.dimsen.pasaronline.utils.CategoryApi;
import com.dimsen.pasaronline.utils.Credentials;
import com.dimsen.pasaronline.utils.ItemsApi;
import com.dimsen.pasaronline.utils.PackageApi;
import com.dimsen.pasaronline.utils.PackageItemApi;

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

    public static CategoryApi categoryApi() {
        return retrofit.create(CategoryApi.class);
    }

    public static PackageApi packageApi() {
        return retrofit.create(PackageApi.class);
    }

    public static PackageItemApi packageItemApi() {
        return retrofit.create(PackageItemApi.class);
    }
}
