package com.example.yzbkaka.kakaAndroid.ui.tree;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.yzbkaka.kakaAndroid.bean.Tree;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.event.Event;
import com.example.yzbkaka.kakaAndroid.event.RxEvent;
import com.example.yzbkaka.kakaAndroid.inter.OnItemClickListener;
import com.example.yzbkaka.kakaAndroid.ui.adapter.BaseListAdapter;
import com.example.yzbkaka.kakaAndroid.ui.adapter.TreeAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseAbListFragment;

import java.util.List;


/**
 * 知识体系
 */
public class TreeFragment extends BaseAbListFragment<TreePresenter, Tree> implements TreeContract.ITreeView, OnItemClickListener<Tree> {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.addOnScrollListener(onScrollListener);
    }


    @Override
    public void setData(List<Tree> data) {
        mListData.clear();
        mListData.addAll(data);
    }


    @Override
    protected TreePresenter createPresenter() {
        return new TreePresenter();
    }


    @Override
    protected void loadDatas() {
        mPresenter.loadTree();  //加载数据
    }


    @Override
    protected BaseListAdapter<Tree> getListAdapter() {
        return new TreeAdapter(this);
    }


    /**
     * 设置滑动监听
     */
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            RxEvent.getInstance().postEvent(Const.EVENT_ACTION.MAIN,new Event(Event.Type.SCALE,dy));
        }
    };


    @Override
    protected void receiveEvent(Object object) {
        Event mEvent = (Event) object;
        if (mEvent.type == Event.Type.SCROLL_TOP){
            mRecyclerView.smoothScrollToPosition(0);
        }
    }


    @Override
    protected String registerEvent() {
        return Const.EVENT_ACTION.SYSTEM;
    }


    /**
     * 点击进入children
     */
    @Override
    public void onItemClick(int position,Tree mTree) {
        Intent intent = new Intent(getActivity(), TreeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Const.BUNDLE_KEY.OBJ, mTree);
        intent.putExtras(bundle);  //传输数据
        startActivity(intent);
    }
}
