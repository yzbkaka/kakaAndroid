package com.example.yzbkaka.kakaAndroid.ui.tree;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.bean.Tree;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.event.Event;
import com.example.yzbkaka.kakaAndroid.event.RxEvent;
import com.example.yzbkaka.kakaAndroid.ui.adapter.TreeFragPagerAdapter;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseTabActivity;
import com.example.yzbkaka.kakaAndroid.utils.ViewAnimatorHelper;

import java.util.ArrayList;
import java.util.List;


public class TreeActivity extends BaseTabActivity {

    public ViewAnimatorHelper viewAnimatorHelper;

    private String mTitle;

    /**
     * tree的子项
     */
    private List<Tree.ChildrenBean> mTreeDatas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        viewAnimatorHelper = new ViewAnimatorHelper();
        viewAnimatorHelper.bindView(btn_scroll_top);
    }


    @Override
    protected void getIntent(Intent intent){
        Bundle bundle = intent.getExtras();
        Tree mTree = null;
        if(bundle != null){
            mTree = (Tree)bundle.getSerializable(Const.BUNDLE_KEY.OBJ);
            this.mTitle = mTree.getName();  //获得标题
            this.mTreeDatas = mTree.getChildren();  //获得子项
        }
    }


    @Override
    protected boolean initToolbar(){
        mToolbar.setTitle(this.mTitle);
        return true;
    }


    @SuppressLint("RestrictedApi")
    @Override
    protected FragmentPagerAdapter createFragPagerAdapter() {
        btn_scroll_top.setVisibility(View.VISIBLE);
        btn_scroll_top.setOnClickListener(onScrollTopListener);
        viewPager.setOffscreenPageLimit(mTreeDatas.size());  //设置选项卡的数量
        return new TreeFragPagerAdapter(getSupportFragmentManager(), mTreeDatas);  //加入选项卡和标题
    }


    private View.OnClickListener onScrollTopListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = mTreeDatas.get(viewPager.getCurrentItem()).getId();  //保证选项卡切换时滑动按钮状态一致
            RxEvent.getInstance().postEvent(Const.EVENT_ACTION.SYSTEM_LIST,new Event(Event.Type.SCROLL_TOP,id));
        }
    };


    public void scroll(int offsetY){
        if (offsetY > 0 && btn_scroll_top.getVisibility() != View.INVISIBLE && !viewAnimatorHelper.isAnimating()){
            viewAnimatorHelper.hideFloatActionButton();
        }else if (offsetY < 0 && btn_scroll_top.getVisibility() != View.VISIBLE){
            viewAnimatorHelper.showFloatActionButton();
        }
    }
}
