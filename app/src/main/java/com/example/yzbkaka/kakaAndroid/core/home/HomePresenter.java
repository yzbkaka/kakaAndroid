package com.example.yzbkaka.kakaAndroid.core.home;

import com.example.yzbkaka.kakaAndroid.core.model.impl.HomeModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;

/**
 * Created by yzbkaka on 19-12-29.
 */

public class HomePresenter extends BasePresenter<HomeContract.IHomeView> implements HomeContract.IHomePresenter {

    private HomeContract.IHomeView homeView;

    private HomeModel homeModel;


    public HomePresenter(){
        this.homeModel = new HomeModel();
    }


    @Override
    public void getHomeData() {

    }

    @Override
    public void getMoreArticleList() {

    }

    @Override
    public void collectArticle() {

    }

    @Override
    public void unCollectArticle() {

    }
}
