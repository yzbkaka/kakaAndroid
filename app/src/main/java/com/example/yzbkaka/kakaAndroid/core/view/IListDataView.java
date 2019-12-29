package com.example.yzbkaka.kakaAndroid.core.view;

import java.util.List;

/**
 * Created by yzbkaka on 19-12-29.
 */

public interface IListDataView<T> extends IView {

    void setData(List<T> data);

    List<T> getData();

    /**
     * 显示内容
     */
    void showContent();
}
