package com.swetabh.whetherapidemo;

import android.app.Application;
import android.content.Context;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by swets on 22-07-2017.
 */

public class WhetherApplication extends Application {
    private Scheduler defaultSubscribeScheduler;

    public static WhetherApplication get(Context context) {
        return (WhetherApplication) context.getApplicationContext();
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }
}
