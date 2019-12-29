package com.example.yzbkaka.kakaAndroid.ui.main;

import android.os.Bundle;
import android.view.View;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }


    @Override
    protected void initViews() {

    }


    @Override
    public void onClick(View view) {

    }
}
