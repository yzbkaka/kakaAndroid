package com.example.yzbkaka.kakaAndroid.api;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.Banner;
import com.example.yzbkaka.kakaAndroid.bean.BaseBean;
import com.example.yzbkaka.kakaAndroid.bean.PageListData;
import com.example.yzbkaka.kakaAndroid.bean.User;
import com.example.yzbkaka.kakaAndroid.common.UrlConstainer;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by yzbkaka on 19-12-29.
 */

public interface ApiServer {

    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST(UrlConstainer.LOGIN)
    Observable<BaseBean<User>> login(@Field("username") String userName, @Field("password") String password);


    /**
     * 用户注册
     */
    @FormUrlEncoded
    @POST(UrlConstainer.REGISTER)
    Observable<BaseBean<User>> register(@Field("username") String userName,@Field("password") String password);


    /**
     * banner
     */
    @GET(UrlConstainer.MAIN_BANNER)
    Observable<BaseBean<List<Banner>>> getBanner();


    /**
     * 置顶文章
     */
    @GET(UrlConstainer.HOME_TOP_LIST)
    Observable<BaseBean<List<Article>>> getHomeTopList();


    /**
     * 列表文章
     */
    @GET(UrlConstainer.HOME_LIST)
    Observable<BaseBean<PageListData<Article>>> getHomeList();


    /**
     * 收藏的文章
     */
    @GET(UrlConstainer.COLLECT_ARTICLE)
    Observable<BaseBean<String>> collectArticle(@Path("id") int id);


    /**
     * 取消收藏文章
     */
    @POST(UrlConstainer.UNCOLLECT_ARTICLE)
    Observable<BaseBean<String>> unCollectArticle(@Path("id") int id);
}
