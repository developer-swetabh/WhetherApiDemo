package com.swetabh.whetherapidemo.network;

import com.swetabh.whetherapidemo.models.WhetherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by swets on 22-07-2017.
 */

public interface WhetherAPI {

    @GET(EndApi.WHETHER_API)
    Observable<WhetherResponse> getWhetherReport(@Query("id") String id, @Query("appid") String appId);
}
