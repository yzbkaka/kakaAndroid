package com.example.yzbkaka.kakaAndroid.ui.tree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.event.Event;
import com.example.yzbkaka.kakaAndroid.inter.OnArticleListItemClickListener;
import com.example.yzbkaka.kakaAndroid.ui.adapter.ArticleListAdapter;
import com.example.yzbkaka.kakaAndroid.ui.adapter.BaseListAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseAbListFragment;
import com.example.yzbkaka.kakaAndroid.utils.ToastUtils;
import com.example.yzbkaka.kakaAndroid.web.WebViewActivity;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-8.
 */

public class TreeListFragment extends BaseAbListFragment<TreeListPresenter, Article> implements TreeListContract.ITreeListView, OnArticleListItemClickListener {

    /**
     * 分类id
     */
    private int cid;

    /**
     * 文章id
     */
    private int id;

    private int position;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.addOnScrollListener(onScrollListener);
    }


    @Override
    protected String registerEvent() {
        return Const.EVENT_ACTION.SYSTEM_LIST;
    }


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (getActivity() == null) return;
            ((TreeActivity)getActivity()).scroll(dy);
        }
    };


    /**
     * 实例化
     */
    public static TreeListFragment instantiate(int cid){
        TreeListFragment instance = new TreeListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Const.BUNDLE_KEY.ID, cid);
        instance.setArguments(bundle);  //存放数据
        return instance;
    }


    @Override
    protected void getBundle(Bundle bundle){
        cid = bundle.getInt(Const.BUNDLE_KEY.ID);
    }


    @Override
    protected boolean isEnableLazy() {
        return true;
    }


    @Override
    protected boolean isCanLoadMore() {
        return true;
    }


    @Override
    protected BaseListAdapter<Article> getListAdapter() {
        return new ArticleListAdapter(this, Const.LIST_TYPE.TREE);
    }


    /**
     * 列表数据
     */
    @Override
    public void setData(List<Article> data) {
        mListData.addAll(data);
    }


    @Override
    public int getCid() {
        return this.cid;
    }


    @Override
    public int getArticleId() {
        return this.id;
    }


    @Override
    protected TreeListPresenter createPresenter() {
        return new TreeListPresenter();
    }


    /**
     * 加载列表数据
     */
    @Override
    protected void loadDatas() {
        mPresenter.loadTreeList();
    }


    /**
     * 点击之后进入详情
     */
    @Override
    public void onItemClick(int position, Article bean) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Const.BUNDLE_KEY.OBJ, bean);
        bundle.putString(Const.BUNDLE_KEY.TYPE, Const.EVENT_ACTION.SYSTEM_LIST);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 接收事件
     */
    @Override
    protected void receiveEvent(Object object) {
        Event mEvent = (Event) object;
        if (mEvent.type == Event.Type.REFRESH_ITEM) {  //收藏之后刷新子项
            Article bean = (Article) mEvent.object;
            for (int i = 0; i < mListData.size(); i++) {
                if (bean.equals(mListData.get(i))) {
                    position = i;
                    notifyItemData(bean.isCollect(), getString(R.string.collect_success));
                }
            }
        }else if (mEvent.type == Event.Type.SCROLL_TOP && (int) mEvent.object == cid){
            mRecyclerView.smoothScrollToPosition(0);
        }else if (mEvent.type == Event.Type.REFRESH_LIST){
            refreshData();
        }
    }


    /**
     * 刷新子项
     */
    private void notifyItemData(boolean isCollect, String result) {
       /* mListData.get(position).setCollect(isCollect);
        mListAdapter.notifyItemDataChanged(position, mRecyclerView);
        ToastUtils.showToast(getActivity(), result);*/
    }


    @Override
    public void onCollectClick(int position, int id) {}


    @Override
    public void collect(boolean isCollect, String result) {}


    @Override
    public void onDeleteCollectClick(int position, int id, int originId) {}
}
