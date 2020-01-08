package com.example.yzbkaka.kakaAndroid.ui.base;

/**
 * Created by yzbkaka on 20-1-8.
 */

import android.os.Bundle;

import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.core.view.IView;
import com.example.yzbkaka.kakaAndroid.utils.ToastUtils;

/**
 * 管理activity的presenter的基类
 */
public abstract class BasePresenterActivity <P extends BasePresenter> extends BaseActivity implements IView {

    protected P mPresenter;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mPresenter = createPresenter();
        attachView();
    }


    /**
     * 进行关联
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    /**
     * 解除关联
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    /**
     * 移除所有请求
     */
    protected void removeAllDisposable() {
        if (mPresenter != null) {
            mPresenter.removeAllDisposable();
        }
    }


    @Override
    protected void onNavigationClick() {
        finish();
    }


    @Override
    protected void onDestroy() {
        detachView();
        removeAllDisposable();
        super.onDestroy();
    }


    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }


    @Override
    public void showFail(String msg) {
        ToastUtils.showToast(this, msg);
    }


    protected abstract P createPresenter();
}
