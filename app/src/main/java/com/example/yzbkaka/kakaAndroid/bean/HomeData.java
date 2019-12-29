package com.example.yzbkaka.kakaAndroid.bean;

import java.util.List;

/**
 * Created by yzbkaka on 19-12-29.
 */

/**
 * 主页数据
 */
public class HomeData {

    /**
     * 顶部滑动
     */
    private BaseBean<List<Banner>> banner;

    /**
     * 置顶文章
     */
    private BaseBean<List<Article>> homeTop;

    /**
     * 列表文章
     */
    private BaseBean<PageListData<Article>> home;


    public BaseBean<List<Banner>> getBanner() {
        return banner;
    }

    public BaseBean<List<Article>> getHomeTop() {
        return homeTop;
    }

    public BaseBean<PageListData<Article>> getHome() {
        return home;
    }

    public void setBanner(BaseBean<List<Banner>> banner) {
        this.banner = banner;
    }

    public void setHomeTop(BaseBean<List<Article>> homeTop) {
        this.homeTop = homeTop;
    }

    public void setHome(BaseBean<PageListData<Article>> home) {
        this.home = home;
    }
}
