package com.example.yzbkaka.kakaAndroid.ui.project;

import com.example.yzbkaka.kakaAndroid.bean.ProjectCate;
import com.example.yzbkaka.kakaAndroid.core.model.impl.ProjectCateModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ProjectCatePresenter extends BasePresenter<ProjectCateContract.IProjectCateView> implements ProjectCateContract.IProjectCatePresenter {

    private ProjectCateModel projectCateModel;

    private ProjectCateContract.IProjectCateView projectCateView;


    ProjectCatePresenter() {
        this.projectCateModel = new ProjectCateModel();
    }


    @Override
    public void getProjectCate() {
        projectCateView = getView();
        RxObserver<List<ProjectCate>> rxObserver = new RxObserver<List<ProjectCate>>(this) {
            @Override
            protected void onSuccess(List<ProjectCate> data) {
                projectCateView.setData(data);
                if (projectCateView.getData().size() == 0){
                    projectCateView.showEmpty();
                }else {
                    projectCateView.showContent();
                }
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                projectCateView.showFail(errorMsg);
            }
        };

        projectCateModel.getProjectCate(rxObserver);
        addDisposable(rxObserver);
    }
}
