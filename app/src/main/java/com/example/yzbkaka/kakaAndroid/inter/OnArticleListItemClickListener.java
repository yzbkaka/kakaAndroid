package com.example.yzbkaka.kakaAndroid.inter;


import com.example.yzbkaka.kakaAndroid.bean.Article;

/**
 * Created by yzbkaka on 19-12-31.
 */


/**
 * 文章列表点击
 */
public interface OnArticleListItemClickListener extends OnItemClickListener<Article> {
    void onDeleteCollectClick(int position,int id,int originId);
    void onCollectClick(int position,int id);
}
