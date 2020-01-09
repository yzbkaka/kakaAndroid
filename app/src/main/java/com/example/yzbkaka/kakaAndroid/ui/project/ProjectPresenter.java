package com.example.yzbkaka.kakaAndroid.ui.project;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.core.model.impl.ProjectModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ProjectPresenter extends BasePresenter<ProjectContract.IProjectView> implements ProjectContract.IProjectPresenter {

    private ProjectModel projectModel;

    private ProjectContract.IProjectView projectView;


    ProjectPresenter() {
        this.projectModel = new ProjectModel();
    }


    @Override
    public void getProjectList() {
        projectView = getView();
        RxPageListObserver<Article> rxPageListObserver = new RxPageListObserver<Article>(this) {
            @Override
            public void onSuccess(List<Article> mData) {
                projectView.setData(mData);
                if (projectView.getData().size() == 0){
                    projectView.showEmpty();
                }else {
                    projectView.showContent();
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                projectView.showFail(errorMsg);
            }
        };
        projectModel.getProjectList(projectView.getPage(),projectView.getCid(),rxPageListObserver);
        addDisposable(rxPageListObserver);
    }


    @Override
    public void collectArticle() {}


    @Override
    public void unCollectArticle() {}
}
