package com.example.yzbkaka.kakaAndroid.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.event.Event;
import com.example.yzbkaka.kakaAndroid.event.RxEvent;
import com.example.yzbkaka.kakaAndroid.inter.OnItemClickListener;
import com.example.yzbkaka.kakaAndroid.inter.OnProjectListItemClickListener;
import com.example.yzbkaka.kakaAndroid.ui.adapter.BaseListAdapter;
import com.example.yzbkaka.kakaAndroid.ui.adapter.ProjectListAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseAbListFragment;
import com.example.yzbkaka.kakaAndroid.utils.ToastUtils;
import com.example.yzbkaka.kakaAndroid.web.WebViewActivity;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ProjectListFragment extends BaseAbListFragment<ProjectPresenter, Article> implements ProjectContract.IProjectView,OnProjectListItemClickListener {

    /**
     * 文章id
     */
    private int id;

    private int position;

    /**
     * 分类id
     */
    private int cid;


    public static ProjectListFragment instantiate(int cid){
        ProjectListFragment instance = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Const.BUNDLE_KEY.ID,cid);
        instance.setArguments(bundle);
        return instance;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.addOnScrollListener(onScrollListener);
    }


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            RxEvent.getInstance().postEvent(Const.EVENT_ACTION.MAIN,new Event(Event.Type.SCALE,dy));
        }
    };


    @Override
    protected String registerEvent() {
        return Const.EVENT_ACTION.PROJECT_LIST;
    }


    @Override
    protected void getBundle(Bundle bundle) {
        cid = bundle.getInt(Const.BUNDLE_KEY.ID);
    }

    @Override
    protected void loadDatas() {
        mPresenter.getProjectList();
    }

    @Override
    protected BaseListAdapter<Article> getListAdapter() {
        return new ProjectListAdapter(this);
    }


    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }


    @Override
    public int getCid() {
        return cid;
    }


    @Override
    public int getArticleId() {
        return id;
    }


    private void notifyItemData(boolean isCollect, String result) {
        mListData.get(position).setCollect(isCollect);
        mListAdapter.notifyItemDataChanged(position, mRecyclerView);
        ToastUtils.showToast(getActivity(), result);
    }


    @Override
    public void setData(List<Article> data) {
        mListData.addAll(data);
    }

    @Override
    public int getFirstPage() {
        return 1;
    }


    @Override
    public void onItemClick(int position, Article bean) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Const.BUNDLE_KEY.OBJ, (Article)bean);
        bundle.putString(Const.BUNDLE_KEY.TYPE, Const.EVENT_ACTION.PROJECT_LIST);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    protected void receiveEvent(Object object) {
        Event mEvent = (Event) object;
        if (mEvent.type == Event.Type.REFRESH_ITEM) {
            Article bean = (Article) mEvent.object;
            for (int i = 0; i < mListData.size(); i++) {
                if (bean.equals(mListData.get(i))) {
                    position = i;
                    notifyItemData(bean.isCollect(), getString(R.string.collect_success));
                }
            }
        }else if (mEvent.type == Event.Type.SCROLL_TOP && (int)mEvent.object == cid){
            mRecyclerView.smoothScrollToPosition(0);
        }else if (mEvent.type == Event.Type.REFRESH_LIST){
            refreshData();
        }
    }


    @Override
    protected boolean isCanLoadMore() {
        return true;
    }


    @Override
    protected boolean isEnableLazy() {
        return true;
    }


    @Override
    public void collect(boolean isCollect, String result) {}


    @Override
    public void onCollectClick(int position, int id) {}
}
