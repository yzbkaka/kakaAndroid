package com.example.yzbkaka.kakaAndroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yzbkaka on 19-12-29.
 */

public class NetworkUtils {

    /**
     * 判断是否有网络链接
     */
    public static boolean isAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
            return networkInfo.isAvailable();
        return false;
    }
}
