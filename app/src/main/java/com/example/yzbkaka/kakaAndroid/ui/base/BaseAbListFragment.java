package com.example.yzbkaka.kakaAndroid.ui.base;

/**
 * Created by yzbkaka on 19-12-29.
 */


import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.core.view.IPageLoadDataView;

/**
 * 页面列表的基类
 */
public class BaseAbListFragment<P extends BasePresenter, T> extends BasePresenterFragment<P> implements LMRecyclerView.OnFooterAutoLoadMoreListener, IPageLoadDataView<T> {
}
