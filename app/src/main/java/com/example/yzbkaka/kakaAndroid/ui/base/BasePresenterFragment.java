package com.example.yzbkaka.kakaAndroid.ui.base;

import android.os.Bundle;

import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.core.view.IView;

/**
 * Created by yzbkaka on 19-12-29.
 */

public abstract class BasePresenterFragment<P extends BasePresenter> extends BaseFragment implements IView {

    private P mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        attachView();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        detachView();
    }


    @Override
    protected int getLayoutId(){
        return 0;
    }


    /**
     * 进行关联
     */
    private void attachView(){
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }


    /**
     * 解除关联
     */
    private void detachView(){
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.removeAllDisposable();
            mPresenter = null;
        }
    }


    protected abstract P createPresenter();

}
