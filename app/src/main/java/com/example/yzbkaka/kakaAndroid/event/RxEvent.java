package com.example.yzbkaka.kakaAndroid.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by yzbkaka on 19-12-28.
 */

public class RxEvent {

    private static RxEvent mInstance;

    private Map<String, List<PublishSubject>> mSubjectMaps = new HashMap<>();


    /**
     * 单例模式
     */
    public static RxEvent getInstance() {
        if (mInstance == null) {
            synchronized (RxEvent.class) {
                if (mInstance == null)
                    mInstance = new RxEvent();
            }
        }
        return mInstance;
    }


    /**
     * 注册事件
     */
    public PublishSubject registerEvent(String mAction){
        List<PublishSubject> mSubjectList = mSubjectMaps.get(mAction);
        if(mSubjectList == null){
            mSubjectList = new ArrayList<>();
        }
        mSubjectMaps.put(mAction,mSubjectList);
        PublishSubject mPublishSubject = PublishSubject.create();
        mSubjectList.add(mPublishSubject);
        return mPublishSubject;
    }


    /**
     * 发送事件
     */
    public void postEvent(String mAction,Object object){
        List<PublishSubject> mSubjectList = mSubjectMaps.get(mAction);
        if(mSubjectList != null && !mSubjectList.isEmpty()){
            for(PublishSubject mPublishSubject:mSubjectList){
                mPublishSubject.onNext(object);
            }
        }
    }


    /**
     * 注销事件
     */
    public void unRegisterEvent(String mAction, PublishSubject mSubject, DisposableObserver mDisposable){
        List<PublishSubject> mSubjectList = mSubjectMaps.get(mAction);
        //中断事件
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
        if (mSubjectList != null){
            mSubjectList.remove(mSubject);
            if (mSubjectList.isEmpty()){
                mSubjectMaps.remove(mAction);
            }
        }
    }
}
