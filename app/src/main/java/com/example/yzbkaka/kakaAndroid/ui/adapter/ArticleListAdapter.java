package com.example.yzbkaka.kakaAndroid.ui.adapter;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.common.ListDataHolder;
import com.example.yzbkaka.kakaAndroid.inter.OnArticleListItemClickListener;

/**
 * Created by yzbkaka on 19-12-31.
 */

public class ArticleListAdapter extends BaseListAdapter<Article> {

    private int type;

    private OnArticleListItemClickListener listener;


    public ArticleListAdapter(int type,OnArticleListItemClickListener listener){
        this.type = type;
        this.listener = listener;
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_home_article_list;
    }


    @Override
    public void bindDatas(ListDataHolder holder, final Article bean, int itemType, final int position) {
        TextView tv_tag = holder.getView(R.id.tv_tag);
        TextView tv_author = holder.getView(R.id.tv_author);
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_type = holder.getView(R.id.tv_type);
        ImageView img_collect = holder.getView(R.id.img_collect);

        //设置文章标题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_title.setText(Html.fromHtml(bean.getTitle(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_title.setText(Html.fromHtml(bean.getTitle()));
        }

        tv_author.setText(bean.getAuthor());  //设置作者
        tv_time.setText(DateUtils.parseTime(bean.getPublishTime()));  //设置时间（按照格式）

        if(type == Const.LIST_TYPE.HOME || type == Const.LIST_TYPE.SEARCH){  //如果是主页或者是搜索
            coverToArticleList(tv_type,tv_tag,img_collect,position,bean);
        }else if(type == Const.LIST_TYPE.TREE){  //知识体系

        }else if(type == Const.LIST_TYPE.COLLECT){  //收藏的文章

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onItemClick(position,bean);
                }
            }
        });
    }


    private void coverToArticleList(TextView tv_type,TextView tv_tag, ImageView img_collect, final int position, final Article bean){

    }
}
