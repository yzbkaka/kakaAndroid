package com.example.yzbkaka.kakaAndroid.ui.User;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.yzbkaka.kakaAndroid.R;

public class AboutUsActivity extends AppCompatActivity {

    /**
     * 版本信息
     */
    private TextView mVersionView;

    /**
     * 介绍信息
     */
    private TextView mIntroduceView;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingbarlayout);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.about_us);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);  //设置上方展开后字体的颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);  //设置缩放后字体的颜色
        mVersionView = (TextView)findViewById(R.id.version);
        mIntroduceView = (TextView)findViewById(R.id.introduce);

        mVersionView.setText("1.0");
        setIntroduce();
    }


    /**
     * 设置介绍信息
     */
    private void setIntroduce() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mIntroduceView.setText(Html.fromHtml(getString(R.string.about_us_introduce), Html.FROM_HTML_MODE_LEGACY));
        } else {
            mIntroduceView.setText(Html.fromHtml(getString(R.string.about_us_introduce)));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
