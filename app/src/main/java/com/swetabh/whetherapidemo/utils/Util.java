package com.swetabh.whetherapidemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by swets on 22-07-2017.
 */

public class Util {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connManager != null) {
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                if (networkInfo.isFailover()) {
                    return false;
                }
                return networkInfo.isConnected();
            } else {
                return false;
            }
        }
        return false;
    }

}
