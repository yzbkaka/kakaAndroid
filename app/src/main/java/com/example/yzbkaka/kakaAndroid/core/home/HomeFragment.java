package com.example.yzbkaka.kakaAndroid.core.home;

import android.view.LayoutInflater;
import android.view.View;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.Banner;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseAbListFragment;
import com.example.yzbkaka.kakaAndroid.ui.base.adapter.BannerAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.adapter.BaseListAdapter;
import com.example.yzbkaka.kakaAndroid.utils.ToastUtils;
import com.example.yzbkaka.kakaAndroid.widget.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzbkaka on 19-12-29.
 */

public class HomeFragment extends BaseAbListFragment<HomePresenter,Article> implements HomeContract.IHomeView, OnArticleListItemClickListener{

    /**
     * 文章id
     */
    private int id;

    private int position;

    /**
     * banner列表
     */
    private List<Banner> mBannerList = new ArrayList<>();

    /**
     * banner组
     */
    private BannerViewPager mBannerViewPager;

    private BannerAdapter mBannerAdapter;


    @Override
    protected HomePresenter createPresenter(){
        return new HomePresenter();
    }


    @Override
    protected boolean isCanLoadMore(){
        return true;
    }


    /**
     * home页滚动view
     */
    @Override
    protected View initHeaderView(){
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.main_header_banner,mRecyclerView,false);
        mBannerViewPager = headerView.findViewById(R.id.viewPager);
        return headerView;
    }


    private void setCurrentItem(final int position){
        mBannerViewPager.setCurrentItem(position);
    }


    @Override
    public int getArticleId() {
        return id;
    }


    /**
     * 加载列表数据
     */
    @Override
    protected void loadDatas() {
        if(page == getFirstPage()){
            mPresenter.getHomeData();
        }else{
            mPresenter.getMoreArticleList();
        }
    }


    /**
     * 设置列表适配器
     */
    @Override
    protected BaseListAdapter<Article> getListAdapter() {
        return new ArticleListAdapter(this, Const.LIST_TYPE.HOME);
    }


    /**
     * 设置banner数据
     */
    @Override
    public void setBannerData(List<Banner> banner) {
        mBannerList.clear();
        mBannerList.addAll(banner);
    }


    /**
     * 设置列表数据
     */
    @Override
    public void setData(List<Article> data) {
        mListData.addAll(data);
    }


    /**
     * 显示内容
     */
    @Override
    public void showContent(){
        notifyDatas();
        super.showContent();
    }


    /**
     * 刷新内容
     */
    public void notifyDatas(){
        if(mBannerAdapter == null){
            mBannerAdapter = new BannerAdapter(mListData);
            mBannerViewPager.setAdapter(mBannerAdapter);
            mBannerViewPager.setOffscreenPageLimit(mBannerList.size());
            setCurrentItem(1000 * mBannerList.size());
        }else{
            mBannerAdapter.notifyDatas(mBannerList);
        }
    }


    /**
     * 收藏的文章
     */
    @Override
    public void collect(boolean isCollect,String result){
        notifyItemData(isCollect, result);
    }


    /**
     * 刷新单条Item
     */
    private void notifyItemData(boolean isCollect, String result) {
        mListData.get(position).setCollect(isCollect);
        position++;
        mListAdapter.notifyItemDataChanged(position, mRecyclerView);
        ToastUtils.showToast(getActivity(), result);
    }



}
