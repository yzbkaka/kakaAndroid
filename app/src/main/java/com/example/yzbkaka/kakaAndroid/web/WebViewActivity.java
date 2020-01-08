package com.example.yzbkaka.kakaAndroid.web;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.FrameLayout;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.ui.base.BasePresenterActivity;
import com.just.agentweb.AgentWeb;

/**
 * 文章点击详情页
 */
public class WebViewActivity extends BasePresenterActivity<WebViewPresenter> implements WebViewContract.IWebView {

    private FrameLayout mContainer;

    /**
     * web库
     */
    private AgentWeb mAgentWeb;

    private Article bean;

    private String actionType;

    private int id;

    private String title = "";

    private String url = "";


    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        mAgentWeb = AgentWeb.with(this)  //开始访问
                .setAgentWebParent(mContainer, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(R.color.black)
                .createAgentWeb()
                .ready()
                .go(url);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }


    @Override
    protected void getIntent(Intent intent){
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        bean = (Article) bundle.getSerializable(Const.BUNDLE_KEY.OBJ);
        actionType = intent.getStringExtra(Const.BUNDLE_KEY.TYPE);
        if (bean != null) {
            id = bean.getId();
            title = bean.getTitle();  //获得文章标题
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                title = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY).toString();
            } else {
                title = Html.fromHtml(title).toString();
            }
            url = bean.getLink();
        }
    }


    @Override
    protected boolean initToolbar(){
        mToolbar.setTitle(this.title);
        return true;
    }


    @Override
    protected WebViewPresenter createPresenter() {
        return new WebViewPresenter();
    }


    @Override
    protected void initViews() {
        mContainer = (FrameLayout)findViewById(R.id.container);
    }


    @Override
    public void showLoading(String msg) {}


    @Override
    public void showError() {}


    @Override
    public void showEmpty() {}


    @Override
    public int getArticleId() {
        return 0;
    }


    @Override
    public void collect(boolean isCollect, String result) {}
}
