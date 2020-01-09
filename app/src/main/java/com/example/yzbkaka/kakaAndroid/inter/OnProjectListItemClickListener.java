package com.example.yzbkaka.kakaAndroid.inter;

import com.example.yzbkaka.kakaAndroid.bean.Article;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface OnProjectListItemClickListener extends OnItemClickListener<Article> {

    void onCollectClick(int position,int id);
}
