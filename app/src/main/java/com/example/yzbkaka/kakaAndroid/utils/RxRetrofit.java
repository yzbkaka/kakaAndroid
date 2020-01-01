package com.example.yzbkaka.kakaAndroid.utils;

/**
 * Created by yzbkaka on 19-12-29.
 */

import android.content.Context;

import com.example.yzbkaka.kakaAndroid.api.ApiServer;
import com.example.yzbkaka.kakaAndroid.net.interprctor.CacheInterceptor;
import com.example.yzbkaka.kakaAndroid.net.interprctor.LoadCookieInterceptor;
import com.example.yzbkaka.kakaAndroid.net.interprctor.RequestInterceptor;
import com.example.yzbkaka.kakaAndroid.net.interprctor.SaveCookieInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 发送网络请求
 */
public class RxRetrofit {

    private Retrofit retrofit;

    private static ApiServer apiServer;


    private static final class Holder{
        private static final RxRetrofit INSTANCE = new RxRetrofit();
    }


    public static RxRetrofit getInstance(){
        return Holder.INSTANCE;
    }


    public void initRxRetrofit(final Context context,String baseUrl){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //链接超时
                .connectTimeout(10, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(10, TimeUnit.SECONDS)
                //缓存
                .cache(new Cache(context.getExternalFilesDir("http_cache"), 10 << 20))
                //添加Cookie拦截器
                .addInterceptor(new SaveCookieInterceptor())
                .addInterceptor(new LoadCookieInterceptor())
                //添加缓存拦截器
                .addInterceptor(new RequestInterceptor())  //无网
                .addNetworkInterceptor(new CacheInterceptor())  //有网
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServer = retrofit.create(ApiServer.class);
    }


    /**
     * 获取ApiServer对象
     */
    public static ApiServer Api() {
        if (apiServer == null)
            throw new IllegalStateException("You must invoke init method first in Application");
        return apiServer;
    }
}
