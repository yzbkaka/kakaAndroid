package com.example.yzbkaka.kakaAndroid.core.home;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.application.AppContext;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.Banner;
import com.example.yzbkaka.kakaAndroid.bean.BaseBean;
import com.example.yzbkaka.kakaAndroid.bean.HomeData;
import com.example.yzbkaka.kakaAndroid.bean.PageListData;
import com.example.yzbkaka.kakaAndroid.core.model.impl.HomeModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;
import com.example.yzbkaka.kakaAndroid.net.callback.RxZipObserver;

import java.util.List;

import io.reactivex.functions.Function3;

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
        homeView = getView();
        Function3<BaseBean<List<Banner>>, BaseBean<List<Article>>, BaseBean<PageListData<Article>>, HomeData> function3 = new Function3<BaseBean<List<Banner>>, BaseBean<List<Article>>, BaseBean<PageListData<Article>>, HomeData>() {
            @Override
            public HomeData apply(BaseBean<List<Banner>> banner, BaseBean<List<Article>> homeTop, BaseBean<PageListData<Article>> home) throws Exception {
                HomeData homeData = new HomeData();
                homeData.setBanner(banner);
                for (Article bean : homeTop.data){
                    bean.setTop(true);  //置顶
                }
                homeData.setHomeTop(homeTop);
                homeData.setHome(home);
                return homeData;
            }
        };


        RxZipObserver<HomeData> rxZipObserver = new RxZipObserver<HomeData>(this) {
            @Override
            public void onNext(HomeData homeData) {
                homeView.setBannerData(homeData.getBanner().data);
                List<Article> list = homeData.getHome().data.getDatas();
                list.addAll(0,homeData.getHomeTop().data);
                homeView.clearListData();
                homeView.autoLoadMore();
                homeView.setData(list);
                if (homeView.getData().size() == 0) {
                    homeView.showEmpty();
                }else {
                    homeView.showContent();
                }
            }
        };
        homeModel.getHomeData(homeView.getPage(),function3, rxZipObserver);
        addDisposable(rxZipObserver);
    }


    /**
     * 获取更多文章
     */
    @Override
    public void getMoreArticleList() {
        homeView = getView();
        RxPageListObserver<Article> rxPageListObserver = new RxPageListObserver<Article>(this) {
            @Override
            public void onSuccess(List<Article> homeList) {
                homeView.getData().addAll(homeList);
                homeView.showContent();
            }
            @Override
            public void onFail(int errorCode, String errorMsg) {
                homeView.showFail(errorMsg);
            }
        };
        homeModel.getMoreArticleList(homeView.getPage(),rxPageListObserver);
        addDisposable(rxPageListObserver);
    }


    /**
     * 收藏
     */
    @Override
    public void collectArticle() {
        RxObserver<String> mCollectRxObserver = new RxObserver<String>(this) {
            @Override
            protected void onStart() {
            }
            @Override
            protected void onSuccess(String data) {
                homeView.collect(true, AppContext.getContext().getString(R.string.collect_success));
            }
            @Override
            protected void onFail(int errorCode, String errorMsg) {
                view.showFail(errorMsg);
            }

        };
        homeModel.collectArticle(homeView.getArticleId(), mCollectRxObserver);
        addDisposable(mCollectRxObserver);

    }


    @Override
    public void unCollectArticle() {
        RxObserver<String> unCollectRxObserver = new RxObserver<String>(this) {
            @Override
            protected void onStart() {}

            @Override
            protected void onSuccess(String data) {
                homeView.collect(false, AppContext.getContext().getString(R.string.uncollect_success));
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                view.showFail(errorMsg);
            }
        };
        homeModel.unCollectArticle(homeView.getArticleId(), unCollectRxObserver);
        addDisposable(unCollectRxObserver);
    }
}
