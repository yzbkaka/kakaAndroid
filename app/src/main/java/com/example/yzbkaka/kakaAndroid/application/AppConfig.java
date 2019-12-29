package com.example.yzbkaka.kakaAndroid.application;

import android.content.Context;

/**
 * Created by yzbkaka on 19-12-29.
 */

public class AppConfig {

    static void init(Context context){
        RxRetrofit.getInstance().initRxRetrofit(context, UrlConstainer.baseUrl);   //初始化网络框架
        PreUtils.init(context);  //初始化缓存
    }
}
