package com.example.yzbkaka.kakaAndroid.core.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.bean.Banner;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.event.Event;
import com.example.yzbkaka.kakaAndroid.event.RxEvent;
import com.example.yzbkaka.kakaAndroid.inter.OnArticleListItemClickListener;
import com.example.yzbkaka.kakaAndroid.ui.adapter.ArticleListAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseAbListFragment;
import com.example.yzbkaka.kakaAndroid.ui.adapter.BannerAdapter;
import com.example.yzbkaka.kakaAndroid.ui.adapter.BaseListAdapter;
import com.example.yzbkaka.kakaAndroid.utils.ToastUtils;
import com.example.yzbkaka.kakaAndroid.web.WebViewActivity;
import com.example.yzbkaka.kakaAndroid.widget.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzbkaka on 19-12-29.
 */

public class HomeFragment extends BaseAbListFragment<HomePresenter,Article> implements HomeContract.IHomeView, OnArticleListItemClickListener {

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
    private BannerViewPager mViewPager;

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
        mViewPager = headerView.findViewById(R.id.viewPager);
        return headerView;
    }


    private void setCurrentItem(final int position){
        mViewPager.setCurrentItem(position);
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
        return new ArticleListAdapter(this,Const.LIST_TYPE.HOME);
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
            mBannerAdapter = new BannerAdapter(mBannerList);
            mViewPager.setAdapter(mBannerAdapter);
            mViewPager.setOffscreenPageLimit(mBannerList.size());
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


    @Override
    public int getArticleId() {
        return id;
    }


    @Override
    public void onResume() {
        super.onResume();
        mViewPager.start();
    }


    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stop();
    }


    /**
     * 对fragment显示进行监听
     */
    @Override
    public void onHiddenChanged(boolean hidden){
        if (hidden) {  //隐藏
            mViewPager.stop();
        } else {  //显示
            mViewPager.start();
        }
    }


    @Override
    protected void receiveEvent(Object object){
        Event mEvent = (Event)object;
        if(mEvent.type == Event.Type.REFRESH_ITEM){  //事件是收藏子项
            Article bean = (Article) mEvent.object;
            for (int i = 0; i < mListData.size(); i++) {
                if (bean.equals(mListData.get(i))) {
                    position = i;
                    notifyItemData(bean.isCollect(), getString(R.string.collect_success));
                }
            }
        }else if (mEvent.type == Event.Type.SCROLL_TOP){  //跳到顶部
            mRecyclerView.smoothScrollToPosition(0);
        }else if (mEvent.type == Event.Type.REFRESH_LIST){  //刷新列表
            refreshData();
        }
    }


    @Override
    protected String registerEvent() {
        return Const.EVENT_ACTION.HOME;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.addOnScrollListener(onScrollListener);
    }


    /**
     * 滑动监听
     */
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            RxEvent.getInstance().postEvent(Const.EVENT_ACTION.MAIN,new Event(Event.Type.SCALE,dy));
        }
    };


    /**
     * 点击文章进入详情
     */
    @Override
    public void onItemClick(int position, Article bean) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Const.BUNDLE_KEY.OBJ, bean);
        bundle.putString(Const.BUNDLE_KEY.TYPE, Const.EVENT_ACTION.HOME);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 当点击收藏
     */
    @Override
    public void onCollectClick(int position, int id) {}


    @Override
    public void onDeleteCollectClick(int position, int id, int originId) {}
}
