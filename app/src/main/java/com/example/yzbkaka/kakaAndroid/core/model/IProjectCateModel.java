package com.example.yzbkaka.kakaAndroid.core.model;

import com.example.yzbkaka.kakaAndroid.bean.ProjectCate;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public interface IProjectCateModel {

    /**
     * 获取项目分类
     */
    void getProjectCate(RxObserver<List<ProjectCate>> rxObserver);
}
