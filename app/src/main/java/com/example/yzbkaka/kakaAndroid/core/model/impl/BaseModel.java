package com.example.yzbkaka.kakaAndroid.core.model.impl;

import com.example.yzbkaka.kakaAndroid.api.ApiServer;
import com.example.yzbkaka.kakaAndroid.core.model.IModel;
import com.example.yzbkaka.kakaAndroid.utils.RxRetrofit;

/**
 * Created by yzbkaka on 20-1-1.
 */

public class BaseModel implements IModel {

    @Override
    public ApiServer doRxRequest() {
        return RxRetrofit.Api();
    }
}
