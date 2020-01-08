package com.example.yzbkaka.kakaAndroid.application;

/**
 * Created by yzbkaka on 20-1-2.
 */


import android.app.Application;


/**
 * kakaAndroid全局配置，需要在AndroidManifest里面进行配置
 */
public class KApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        AppContext.initialize(this);
    }
}
