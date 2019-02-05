package com.icc.commerce.productsdashboard.net.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
            if (infos != null) {
                for (int i = 0; i < infos.length; i++) {
                    if (infos[i].isConnected()) {
                        return true;
                    }

                }
            }
        }
        return false;
    }
}
