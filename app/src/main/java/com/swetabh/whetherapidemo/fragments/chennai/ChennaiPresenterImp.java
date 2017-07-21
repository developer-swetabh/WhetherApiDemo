package com.swetabh.whetherapidemo.fragments.chennai;

import android.content.Context;

import com.swetabh.whetherapidemo.BaseView;
import com.swetabh.whetherapidemo.MainContract;
import com.swetabh.whetherapidemo.utils.Util;

/**
 * Created by swets on 21-07-2017.
 */

public class ChennaiPresenterImp implements MainContract.ChennaiPresenter {

    private MainContract.ChennaiView mView;


    @Override
    public void start() {

    }

    @Override
    public void attachView(BaseView view) {
        mView = (MainContract.ChennaiView) view;
        mView.setPresenter(this);
    }

    @Override
    public void checkNetworkConnectivity(Context mContext) {
        if (Util.isNetworkAvailable(mContext)) {
            mView.internetConnected();
        } else {
            mView.internetNotConnected();
        }
    }
}
