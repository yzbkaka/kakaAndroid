package com.example.yzbkaka.kakaAndroid.application;

import android.content.Context;

/**
 * Created by yzbkaka on 19-12-29.
 */

/**
 * 上下文context
 */
public class AppContext {

    private static Context mContext;

    private static AppContext mInstance;


    private AppContext(Context mCon) {
        mContext = mCon;
    }


    public static Context getContext() {
        return mContext;
    }


    public static AppContext getInstance() {
        return mInstance;
    }


    static void initialize(Context context) {
        if (mInstance == null) {
            synchronized (AppContext.class) {
                if (mInstance == null) {
                    mInstance = new AppContext(context.getApplicationContext());
                    AppConfig.init(mContext);
                }
            }
        }
    }
}
