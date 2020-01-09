package com.example.yzbkaka.kakaAndroid.ui.chapter;

import com.example.yzbkaka.kakaAndroid.bean.Chapter;
import com.example.yzbkaka.kakaAndroid.core.model.impl.ChapterModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ChaptersPresenter extends BasePresenter<ChapterContract.IChaptersView> implements ChapterContract.IChaptersPresenter {

    private ChapterModel chapterModel;

    private ChapterContract.IChaptersView chaptersView;


    ChaptersPresenter() {
        this.chapterModel = new ChapterModel();
    }


    @Override
    public void getChapters() {
        chaptersView = getView();
        RxObserver<List<Chapter>> rxObserver = new RxObserver<List<Chapter>>(this) {
            @Override
            protected void onSuccess(List<Chapter> data) {
                chaptersView.setData(data);
                if (chaptersView.getData().size() == 0) {
                    chaptersView.showEmpty();
                }else {
                    chaptersView.showContent();
                }
            }
            @Override
            protected void onFail(int errorCode, String errorMsg) {
                chaptersView.showFail(errorMsg);
            }
        };
        chapterModel.getChapters(rxObserver);
        addDisposable(rxObserver);
    }
}
