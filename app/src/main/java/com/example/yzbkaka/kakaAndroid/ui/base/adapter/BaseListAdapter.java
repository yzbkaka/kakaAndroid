package com.example.yzbkaka.kakaAndroid.ui.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.yzbkaka.kakaAndroid.common.ListDataHolder;
import com.example.yzbkaka.kakaAndroid.widget.LMRecyclerView;

import java.util.List;

/**
 * Created by yzbkaka on 19-12-30.
 */

/**
 * 适配器基类
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<ListDataHolder> {

    private List<T> mList;


    /**
     * 刷新所有数据
     */
    public void notifyAllDatas(List<T> mList, LMRecyclerView recyclerView){
        this.mList = mList;
        recyclerView.notifyDataSetChanged();
    }


    /**
     * 刷新单条数据
     */
    public void notifyItemDataChanged(int position,LMRecyclerView recyclerView){
        recyclerView.notifyItemChanged(position);
    }


    /**
     * 移除单条数据
     */
    public void notifyItemDataRemove(int position,LMRecyclerView recyclerView){
        recyclerView.notifyItemRemoved(position);
    }


    @Override
    public ListDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ListDataHolder.createViewHolder(parent, getLayoutId(viewType));
    }


    protected abstract int getLayoutId(int viewType);


    @Override
    public void onBindViewHolder(ListDataHolder holder, int position) {
        T bean = mList.get(position);  //初始化View
        bindDatas(holder, bean, holder.getItemViewType(), position);  //绑定数据
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public abstract void bindDatas(ListDataHolder holder, T bean, int itemType, int position);
}
