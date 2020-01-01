package com.example.yzbkaka.kakaAndroid.net.callback;

import com.example.yzbkaka.kakaAndroid.application.AppContext;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.core.view.IView;
import com.example.yzbkaka.kakaAndroid.net.interprctor.NetExceptionHandle;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by yzbkaka on 20-1-1.
 */

public abstract class RxZipObserver<T> extends DisposableObserver<T> {

    protected IView view;


    protected RxZipObserver(BasePresenter mPresenter) {
        this.view = mPresenter.getView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
    }


    @Override
    public void onError(Throwable e) {
        hideLoading();
        NetExceptionHandle.dealException(AppContext.getContext(),e);
    }


    @Override
    public void onComplete() {
        hideLoading();
    }


    public void showLoading() {
        view.showLoading("");
    }


    private void hideLoading() {
        if (null != view) this.view.hideLoading();
    }
}
