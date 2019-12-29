package com.example.yzbkaka.kakaAndroid.net.callback;

import com.example.yzbkaka.kakaAndroid.application.AppContext;
import com.example.yzbkaka.kakaAndroid.bean.BaseBean;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.core.view.IView;
import com.example.yzbkaka.kakaAndroid.net.interprctor.NetExceptionHandle;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by yzbkaka on 19-12-29.
 */


public abstract class RxBaseObserver<T> extends DisposableObserver<BaseBean<T>> {

    protected IView view;


    RxBaseObserver(BasePresenter mPresenter) {
        this.view = mPresenter.getView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
    }


    public void showLoading() {
        view.showLoading("");
    }


    @Override
    public void onError(Throwable e) {
        hideLoading();
        NetExceptionHandle.dealException(AppContext.getContext(),e);  //处理异常
    }


    @Override
    public void onComplete() {
        hideLoading();
    }


    private void hideLoading() {
        if (null != view)
            this.view.hideLoading();
    }
}
