package com.swetabh.whetherapidemo;

import rx.Subscription;

/**
 * Created by swets on 21-07-2017.
 */

public interface BasePresenter {

    void start();

    void attachView(BaseView view);

    void detachView(Subscription subscription);
}
