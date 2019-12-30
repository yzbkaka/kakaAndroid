package com.example.yzbkaka.kakaAndroid.net.interprctor;

import com.example.yzbkaka.kakaAndroid.application.AppContext;
import com.example.yzbkaka.kakaAndroid.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yzbkaka on 19-12-30.
 */


/**
 * 缓存策略
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtils.isAvailable(AppContext.getContext())) {  //有网络时只从网络获取
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        }
        Response response = chain.proceed(request);
        if (NetworkUtils.isAvailable(AppContext.getContext())) {   //设置有网不缓存
            int maxAge = 0;  //有网络时，设置缓存超时为0
            response = response.newBuilder()
                    .removeHeader("Pramga")//清除头消息
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .build();
        }
        return response;
    }
}
