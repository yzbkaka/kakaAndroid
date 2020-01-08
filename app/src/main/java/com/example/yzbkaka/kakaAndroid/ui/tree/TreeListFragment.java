package com.example.yzbkaka.kakaAndroid.ui.tree;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.inter.OnArticleListItemClickListener;
import com.example.yzbkaka.kakaAndroid.ui.adapter.BaseListAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseAbListFragment;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-8.
 */

public class TreeListFragment extends BaseAbListFragment<TreeListPresenter, Article> implements TreeListContract.ITreeListView, OnArticleListItemClickListener {


    @Override
    public void onItemClick(int position, Article bean) {

    }

    @Override
    public void setData(List<Article> data) {

    }

    @Override
    public void onDeleteCollectClick(int position, int id, int originId) {

    }

    @Override
    public void onCollectClick(int position, int id) {

    }

    @Override
    public int getCid() {
        return 0;
    }

    @Override
    public int getArticleId() {
        return 0;
    }

    @Override
    public void collect(boolean isCollect, String result) {

    }

    @Override
    protected TreeListPresenter createPresenter() {
        return null;
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    protected BaseListAdapter<Article> getListAdapter() {
        return null;
    }
}
