package com.example.yzbkaka.kakaAndroid.net.callback;

import com.example.yzbkaka.kakaAndroid.bean.BaseBean;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.interprctor.NetConfig;

/**
 * Created by yzbkaka on 20-1-1.
 */

public abstract class RxObserver<T> extends RxBaseObserver<T> {

    public RxObserver(BasePresenter mPresenter) {
        super(mPresenter);
    }


    @Override
    public void onNext(BaseBean<T> mBaseBean) {
        if (mBaseBean.errorCode == NetConfig.REQUEST_SUCCESS) {  //请求成功
            onSuccess(mBaseBean.data);
        } else {
            onFail(mBaseBean.errorCode, mBaseBean.errorMsg);  //失败
        }
    }


    protected abstract void onSuccess(T data);

    protected abstract void onFail(int errorCode, String errorMsg);
}
