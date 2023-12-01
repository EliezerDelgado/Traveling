package com.travel_world.traveling.io;

import static com.travel_world.traveling.data.constants.URLS.URL_HOST;

import com.travel_world.traveling.model.MyApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApiAdapter {
    private static MyApiService API_SERVICE;
    public static MyApiService getApiService() {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            API_SERVICE = retrofit.create(MyApiService.class);
        }

        return API_SERVICE;
    }
}
