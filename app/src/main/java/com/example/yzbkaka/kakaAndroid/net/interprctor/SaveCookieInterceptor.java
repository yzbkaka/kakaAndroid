package com.example.yzbkaka.kakaAndroid.net.interprctor;

import android.util.Log;

import com.example.yzbkaka.kakaAndroid.common.UrlConstainer;
import com.example.yzbkaka.kakaAndroid.utils.PreUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yzbkaka on 19-12-30.
 */

public class SaveCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        List<String> mCookieList = response.headers("Set-Cookie");
        //保存Cookie
        if (!mCookieList.isEmpty() && request.url().toString().endsWith(UrlConstainer.LOGIN)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String cookie : mCookieList) {
                //注意Cookie请求头字段中的每个Cookie之间用逗号或分号分隔
                stringBuilder.append(cookie).append(",");
            }
            PreUtils.put(response.request().url().host(), stringBuilder.toString());
            Log.e(SaveCookieInterceptor.class.getSimpleName(), "intercept: url : " + request.url());
        }
        return response;
    }
}
