package com.example.yzbkaka.kakaAndroid.ui.chapter;

import com.example.yzbkaka.kakaAndroid.bean.Chapter;
import com.example.yzbkaka.kakaAndroid.core.view.IListDataView;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface ChapterContract {

    interface IChaptersPresenter {
        void getChapters();
    }

    interface IChaptersView extends IListDataView<Chapter> {}
}
