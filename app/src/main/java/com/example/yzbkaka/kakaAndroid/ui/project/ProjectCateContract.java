package com.example.yzbkaka.kakaAndroid.ui.project;


import com.example.yzbkaka.kakaAndroid.bean.ProjectCate;
import com.example.yzbkaka.kakaAndroid.core.view.IListDataView;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface ProjectCateContract {

    interface IProjectCatePresenter {
        void getProjectCate();
    }

    interface IProjectCateView extends IListDataView<ProjectCate>{}
}
