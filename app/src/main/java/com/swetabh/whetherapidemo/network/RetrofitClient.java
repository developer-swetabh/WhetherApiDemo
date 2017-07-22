package com.swetabh.whetherapidemo.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by swets on 22-07-2017.
 */

public class RetrofitClient {
    private static WhetherAPI sInstance;

    public static WhetherAPI getInstance() {
        if (sInstance == null) {
            sInstance = newInstance();
        }
        return sInstance;
    }

    private static WhetherAPI newInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient())
                .build();

        return retrofit.create(WhetherAPI.class);

    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        return httpClient.build();
    }
}
