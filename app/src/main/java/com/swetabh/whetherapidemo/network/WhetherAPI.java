package com.swetabh.whetherapidemo.network;

import com.swetabh.whetherapidemo.models.WhetherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by swets on 22-07-2017.
 */

public interface WhetherAPI {

    @GET(EndApi.WHETHER_API)
    Call<WhetherResponse> getWhetherReport(@Query("id") String id, @Query("appid") String appId);
}
