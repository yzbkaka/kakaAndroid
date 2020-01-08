package com.example.yzbkaka.kakaAndroid.ui.tree;

import com.example.yzbkaka.kakaAndroid.bean.Article;
import com.example.yzbkaka.kakaAndroid.core.model.impl.TreeListModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-8.
 */

public class TreeListPresenter extends BasePresenter<TreeListContract.ITreeListView> implements TreeListContract.ITreePresenter {

    private TreeListModel treeListModel;

    private TreeListContract.ITreeListView treeListView;


    public TreeListPresenter() {
        this.treeListModel = new TreeListModel();
    }


    @Override
    public void loadTreeList() {
        treeListView = getView();
        RxPageListObserver<Article> mTreeListRxPageListObserver = new RxPageListObserver<Article>(this) {

            @Override
            public void onSuccess(List<Article> mData) {
                treeListView.setData(mData);
                if(treeListView.getData().size() == 0){
                    treeListView.showEmpty();
                }else {
                    treeListView.showContent();
                }
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                treeListView.showFail(errorMsg);
            }
        };
        treeListModel.getTreeList(treeListView.getPage(), treeListView.getCid(), mTreeListRxPageListObserver);
        addDisposable(mTreeListRxPageListObserver);
    }


    @Override
    public void collectArticle() {}


    @Override
    public void unCollectArticle() {}
}
