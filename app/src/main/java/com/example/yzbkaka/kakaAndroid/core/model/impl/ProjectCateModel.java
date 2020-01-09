package com.example.yzbkaka.kakaAndroid.core.model.impl;

import com.example.yzbkaka.kakaAndroid.bean.ProjectCate;
import com.example.yzbkaka.kakaAndroid.core.model.IProjectCateModel;
import com.example.yzbkaka.kakaAndroid.net.RxSchedulers;
import com.example.yzbkaka.kakaAndroid.net.callback.RxObserver;

import java.util.List;

/**
 * Created by yzbkaka on 20-1-9.
 */

public class ProjectCateModel extends BaseModel implements IProjectCateModel {

    @Override
    public void getProjectCate(RxObserver<List<ProjectCate>> rxObserver) {
        doRxRequest()
                .getProjectCate()
                .compose(RxSchedulers.<List<ProjectCate>>io_main())
                .subscribe(rxObserver);
    }
}
