package com.swetabh.whetherapidemo;

import android.content.Context;

/**
 * Created by swets on 21-07-2017.
 */

public interface MainContract {

    interface MainCommunicator {

    }

    interface BangalorePresenter extends BasePresenter {
        void checkNetworkConnectivity(Context mContext);
    }

    interface BangaloreView extends BaseView<BangalorePresenter, MainCommunicator> {

        void internetConnected();

        void internetNotConnected();
    }


    interface DelhiPresenter extends BasePresenter {
        void checkNetworkConnectivity(Context mContext);
    }

    interface DelhiView extends BaseView<DelhiPresenter, MainCommunicator> {
        void internetConnected();

        void internetNotConnected();
    }


    interface ChennaiPresenter extends BasePresenter {
        void checkNetworkConnectivity(Context mContext);
    }

    interface ChennaiView extends BaseView<ChennaiPresenter, MainCommunicator> {
        void internetConnected();

        void internetNotConnected();
    }
}
