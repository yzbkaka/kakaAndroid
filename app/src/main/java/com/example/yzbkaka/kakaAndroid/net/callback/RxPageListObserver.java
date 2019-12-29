package com.example.yzbkaka.kakaAndroid.net.callback;

/**
 * Created by yzbkaka on 19-12-29.
 */

import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.core.view.IPageLoadDataView;

/**
 * 分页加载接口回调
 */
public abstract class RxPageListObserver extends RxBaseObserver  {

    private IPageLoadDataView  mListDataView;


    public RxPageListObserver(BasePresenter basePresenter){

    }
}
