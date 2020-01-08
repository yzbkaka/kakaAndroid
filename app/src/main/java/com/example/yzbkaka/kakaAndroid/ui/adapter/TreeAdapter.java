package com.example.yzbkaka.kakaAndroid.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.bean.Tree;
import com.example.yzbkaka.kakaAndroid.common.ListDataHolder;
import com.example.yzbkaka.kakaAndroid.inter.OnItemClickListener;

/**
 * Created by yzbkaka on 20-1-8.
 */

/**
 * 知识体系适配器
 */
public class TreeAdapter extends BaseListAdapter<Tree> {

    private OnItemClickListener<Tree> listener;


    public TreeAdapter(OnItemClickListener<Tree> listener) {
        this.listener = listener;
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_tree;
    }


    @Override
    public void bindDatas(ListDataHolder holder, final Tree bean, int itemType, final int position) {
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_content = holder.getView(R.id.tv_content);
        tv_title.setText(bean.getName());
        tv_content.setText("");
        for (Tree.ChildrenBean child : bean.getChildren()) {
            tv_content.append(child.getName() + "     ");  //子项之间空2格
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(position,bean);
            }
        });
    }
}
