package com.example.yzbkaka.kakaAndroid.web;

/**
 * Created by yzbkaka on 20-1-8.
 */


import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.application.AppContext;
import com.example.yzbkaka.kakaAndroid.core.model.impl.CommonModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

/**
 * 文章点击之后的presenter
 */
public class WebViewPresenter extends BasePresenter<WebViewContract.IWebView> implements WebViewContract.IWebViewPresenter {

    private CommonModel commonModel;

    private WebViewContract.IWebView webView;


    WebViewPresenter() {
        this.commonModel = new CommonModel();
    }


    @Override
    public void collectArticle() {
        webView = getView();
        RxObserver<String> mCollectRxObserver = new RxObserver<String>(this) {
            @Override
            protected void onStart() {
            }
            @Override
            protected void onSuccess(String data) {
                webView.collect(true, AppContext.getContext().getString(R.string.collect_success));
            }
            @Override
            protected void onFail(int errorCode, String errorMsg) {
                view.showFail(errorMsg);
            }

        };
        commonModel.collectArticle(webView.getArticleId(), mCollectRxObserver);
        addDisposable(mCollectRxObserver);
    }
}
