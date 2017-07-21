package com.swetabh.whetherapidemo.fragments.delhi;

import android.content.Context;

import com.swetabh.whetherapidemo.BaseView;
import com.swetabh.whetherapidemo.MainContract;
import com.swetabh.whetherapidemo.utils.Util;

/**
 * Created by swets on 21-07-2017.
 */

public class DelhiPresenterImp implements MainContract.DelhiPresenter {

    private MainContract.DelhiView mView;


    @Override
    public void start() {

    }

    @Override
    public void attachView(BaseView view) {
        mView = (MainContract.DelhiView) view;
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
