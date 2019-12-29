package com.example.yzbkaka.kakaAndroid.ui.base;

/**
 * Created by yzbkaka on 19-12-29.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yzbkaka.kakaAndroid.event.RxEvent;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

/**
 * fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private PublishSubject mSubject;

    private DisposableObserver mDisposableObserver;

    /**
     * 内部事件类
     */
    private class ReceiveEvent extends DisposableObserver{

        @Override
        public void onNext(Object o) {
            receiveEvent(o);
        }

        @Override
        public void onError(Throwable e) {}

        @Override
        public void onComplete() {}
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            getBundle(bundle);
        }
        //注册事件
        mDisposableObserver = new ReceiveEvent();
        mSubject = RxEvent.getInstance().registerEvent(registerEvent());
        mSubject.subscribe(mDisposableObserver);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        int layoutId = getLayoutId();
        View view = null;
        if(layoutId != 0){
            view = inflater.inflate(layoutId,container,false);
            initViews(view);
        }
        return view;
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        registerEvent();  //注销事件
    }


    protected abstract void initViews(View view);

    protected abstract int getLayoutId();

    protected void receiveEvent(Object object){}

    protected String registerEvent(){ return null; }

    protected void getBundle(Bundle bundle){}
}
