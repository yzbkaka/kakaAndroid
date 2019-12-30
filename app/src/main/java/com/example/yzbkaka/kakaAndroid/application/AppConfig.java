package com.example.yzbkaka.kakaAndroid.application;

import android.content.Context;

import com.example.yzbkaka.kakaAndroid.common.UrlConstainer;
import com.example.yzbkaka.kakaAndroid.utils.PreUtils;
import com.example.yzbkaka.kakaAndroid.utils.RxRetrofit;

/**
 * Created by yzbkaka on 19-12-29.
 */


/**
 * 初始化网络类
 */
public class AppConfig {

    static void init(Context context){
        RxRetrofit.getInstance().initRxRetrofit(context, UrlConstainer.baseUrl);   //初始化网络框架
        PreUtils.init(context);  //初始化缓存
    }
}
