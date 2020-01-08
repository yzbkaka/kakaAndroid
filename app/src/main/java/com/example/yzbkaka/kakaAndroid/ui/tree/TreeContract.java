package com.example.yzbkaka.kakaAndroid.ui.tree;

import com.example.yzbkaka.kakaAndroid.bean.Tree;
import com.example.yzbkaka.kakaAndroid.core.view.IPageLoadDataView;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-8.
 */


/**
 * 知识体系contract
 */
public interface TreeContract {

    interface ITreePresenter{
        void loadTree();
    }

    interface ITreeView extends IPageLoadDataView<Tree>{
        void setData(List<Tree> tree);
    }
}
