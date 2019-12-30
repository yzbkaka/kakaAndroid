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

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isAvailable(AppContext.getContext())) {  //无网络时从缓存中获取
            int maxStale = 30 * 24 * 60 * 60;  //无网络时,设置超时为30天
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .removeHeader("Pragma")
                    .header("Cache-Control", "only-if-cached,max-stale=" + maxStale)
                    .build();
        }
        return chain.proceed(request);
    }
}
