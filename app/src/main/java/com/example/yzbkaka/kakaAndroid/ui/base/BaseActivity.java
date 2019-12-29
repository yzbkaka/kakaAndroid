package com.example.yzbkaka.kakaAndroid.ui.base;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.event.RxEvent;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    protected FrameLayout mContainerLayout;

    private ProgressDialog loadingDialog = null;

    private PublishSubject mSubject;

    private DisposableObserver mDisposableObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            String FRAGMENTS_TAG = "android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);  //移除旧的fragment
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Intent intent = getIntent();
        if(intent != null){
            getIntent(intent);
        }
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mContainerLayout = (FrameLayout)findViewById(R.id.frameLayout);
        boolean isToolbar = initToolbar();
        if (isToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationClick();
                }
            });
        }else {
            mToolbar.setVisibility(View.GONE);
        }

        initContent(getLayoutId());
        mSubject = RxEvent.getInstance().registerEvent(registerEvent());
        mDisposableObserver = new ReceiveEvent();
        mSubject.subscribe(mDisposableObserver);
    }


    private class ReceiveEvent extends DisposableObserver{
        @Override
        public void onNext(Object o) {
            receiveEvent(o);
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxEvent.getInstance().unRegisterEvent(registerEvent(), mSubject, mDisposableObserver);
    }


    /**
     * 初始化内容
     */
    private void initContent(int layoutId){
        if(layoutId != 0){
            View contentView = LayoutInflater.from(this).inflate(layoutId,mContainerLayout,false);
            mContainerLayout.addView(contentView);
            initViews();
        }
    }


    /**
     * 展示加载框
     */
    protected void showLoadingDialog(String title) {
        createLoadingDialog();
        loadingDialog.setMessage(title);
        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }

    protected void showLoadingDialog() {
        createLoadingDialog();
        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }


    /**
     * 创建加载框
     */
    private void createLoadingDialog(){
        if(loadingDialog == null){
            loadingDialog = new ProgressDialog(this);
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
    }


    /**
     * 隐藏进度框
     */
    protected void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    protected void receiveEvent(Object object){ }


    protected String registerEvent(){
        return null;
    }


    protected void onNavigationClick() {
        finish();
    }


    protected abstract int getLayoutId();


    protected boolean initToolbar(){
        return false;
    }


    protected void getIntent(Intent intent){}


    protected abstract void initViews();

}
