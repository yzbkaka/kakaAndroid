package com.example.yzbkaka.kakaAndroid.ui.adapter;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.application.AppContext;
import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.common.ListDataHolder;
import com.example.yzbkaka.kakaAndroid.inter.OnArticleListItemClickListener;
import com.example.yzbkaka.kakaAndroid.utils.DateUtils;

/**
 * Created by yzbkaka on 19-12-31.
 */

public class ArticleListAdapter extends BaseListAdapter<Article> {

    private int type;

    private OnArticleListItemClickListener listener;


    public ArticleListAdapter(OnArticleListItemClickListener listener,int type){
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
        tv_time.setText(DateUtils.parseTime(bean.getPublishTime()));  //设置时间（按照格式：xxxx-xx-xx 或者是 x天前）

        if(type == Const.LIST_TYPE.HOME || type == Const.LIST_TYPE.SEARCH){  //如果是主页或者是搜索的文章列表
            coverToArticleList(tv_type,tv_tag,img_collect,position,bean);
        }else if(type == Const.LIST_TYPE.TREE){  //知识体系
            coverToTreeList(tv_type,tv_tag,img_collect,position,bean);
        }else if(type == Const.LIST_TYPE.COLLECT){  //收藏的文章
            //有问题;
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


    /**
     * home和搜索文章列表
     */
    private void coverToArticleList(TextView tv_type,TextView tv_tag, ImageView img_collect, final int position, final Article bean){
        tv_type.setText(String.format("%1$s / %2$s",bean.getSuperChapterName(),bean.getChapterName()));  //文章类型
        tv_tag.setVisibility(View.VISIBLE);  //文章tag

        if(bean.isTop()){  //置顶文章
            tv_tag.setActivated(true);  //设置点击
            tv_tag.setText("置顶");
            tv_tag.setTextColor(Color.RED);
        }else if(bean.isFresh()){  //新文章
            tv_tag.setText("新");
            tv_tag.setTextColor(Color.RED);
            tv_tag.setActivated(true);
        }else if(bean.getTags().size() > 0){  //有多个标签
            tv_tag.setActivated(false);
            tv_tag.setText(bean.getTags().get(0).getName());  //取第一个标签
            tv_tag.setTextColor(ContextCompat.getColor(AppContext.getContext(),R.color._009a61));
        }else{
            tv_tag.setVisibility(View.INVISIBLE);  //不显示
        }

        img_collect.setImageResource(bean.isCollect() ? R.drawable.ic_favorite_light_24dp : R.drawable.ic_favorite_gray_24dp);  //设置喜欢图标
        img_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onCollectClick(position,bean.getId());
                }
            }
        });
    }


    /**
     * tree
     */
    private void coverToTreeList(TextView tv_type, TextView tv_tag, ImageView img_collect, final int position, final Article bean){
        tv_type.setText(String.format("%1$s / %2$s",bean.getSuperChapterName(), bean.getChapterName()));
        tv_tag.setVisibility(View.GONE);
        img_collect.setImageResource(bean.isCollect() ? R.drawable.ic_favorite_light_24dp : R.drawable.ic_favorite_gray_24dp);
        img_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCollectClick(position, bean.getId());
                }
            }
        });
    }
}
