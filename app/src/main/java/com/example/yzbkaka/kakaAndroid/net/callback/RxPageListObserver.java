package com.example.yzbkaka.kakaAndroid.net.callback;

/**
 * Created by yzbkaka on 19-12-29.
 */

import com.example.yzbkaka.kakaAndroid.bean.BaseBean;
import com.example.yzbkaka.kakaAndroid.bean.PageListData;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.core.view.IPageLoadDataView;
import com.example.yzbkaka.kakaAndroid.net.interprctor.NetConfig;

import java.util.List;

/**
 * 分页加载接口回调，处理分页加载逻辑
 */
public abstract class RxPageListObserver<T> extends RxBaseObserver<PageListData<T>>  {

    private IPageLoadDataView  mListDataView;


    public RxPageListObserver(BasePresenter basePresenter){
        super(basePresenter);
        this.mListDataView = (IPageLoadDataView) basePresenter.getView();
    }


    @Override
    public void onNext(BaseBean<PageListData<T>> baseBean) {
        if(baseBean.errorCode == NetConfig.REQUEST_SUCCESS){  //请求成功
            PageListData<T> mListData = baseBean.data;
            if(mListDataView.getPage() == mListDataView.getFirstPage()){  //第一页
                mListDataView.clearListData();
            }
            if(mListData.isOver()){  //该页结束
                mListDataView.showNoMore();
            }
            else{  //加载更多分页
                mListDataView.autoLoadMore();
            }
            onSuccess(mListData.getDatas());
        }
        else{
            onFail(baseBean.errorCode, baseBean.errorMsg);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void showLoading() {
        super.showLoading();
    }


    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }


    @Override
    public void onComplete() {
        super.onComplete();
    }


    public abstract void onSuccess(List<T> mData);

    public abstract void onFail(int errorCode, String errorMsg);
}
