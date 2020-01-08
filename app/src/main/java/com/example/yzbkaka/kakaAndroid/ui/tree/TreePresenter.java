package com.example.yzbkaka.kakaAndroid.ui.tree;

import com.example.yzbkaka.kakaAndroid.bean.Tree;
import com.example.yzbkaka.kakaAndroid.core.model.impl.TreeModel;
import com.example.yzbkaka.kakaAndroid.core.presenter.BasePresenter;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-8.
 */

public class TreePresenter extends BasePresenter<TreeContract.ITreeView> implements TreeContract.ITreePresenter {

    private TreeModel mTreeModel;

    private TreeContract.ITreeView mSystemView;


    public TreePresenter(){
        mTreeModel = new TreeModel();
    }


    /**
     * 获取知识体系
     */
    @Override
    public void loadTree() {
        mSystemView = getView();
        RxObserver<List<Tree>> mTreeRxObserver = new RxObserver<List<Tree>>(this) {

            @Override
            protected void onSuccess(List<Tree> data) {
                mSystemView.setData(data);
                if (mSystemView.getData().size() == 0) {  //如果数据为空
                    mSystemView.showEmpty();
                } else {
                    mSystemView.showContent();
                }
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                mSystemView.showFail(errorMsg);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mSystemView.showError();
            }
        };
        mTreeModel.getTree(mTreeRxObserver);
        addDisposable(mTreeRxObserver);
    }
}
