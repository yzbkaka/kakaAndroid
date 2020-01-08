package com.example.yzbkaka.kakaAndroid.ui.tree;

import com.example.yzbkaka.kakaAndroid.core.model.impl.TreeListModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;

/**
 * Created by yzbkaka on 20-1-8.
 */

public class TreeListPresenter extends BasePresenter<TreeListContract.ITreeListView> implements TreeListContract.ITreePresenter {

    private TreeListModel treeListModel;

    private TreeListContract.ITreeListView treeListView;


    public TreeListPresenter(){
        this.treeListModel = new TreeListModel();
    }


    @Override
    public void loadTreeList() {

    }


    @Override
    public void collectArticle() {

    }


    @Override
    public void unCollectArticle() {

    }
}
