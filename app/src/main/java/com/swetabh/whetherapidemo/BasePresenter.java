package com.swetabh.whetherapidemo;

import com.swetabh.whetherapidemo.BaseView;

/**
 * Created by swets on 21-07-2017.
 */

public interface BasePresenter {

    void start();

    void attachView(BaseView view);
}
