package com.swetabh.whetherapidemo;

/**
 * Created by swets on 21-07-2017.
 */

public interface BaseView<T , U> {

    void setPresenter(T presenter);

    void setCommunicator(U communicator);

    void updateActionBar();
}
