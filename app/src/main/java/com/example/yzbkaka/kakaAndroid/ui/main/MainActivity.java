package com.example.yzbkaka.kakaAndroid.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.common.Const;
import com.example.yzbkaka.kakaAndroid.core.home.HomeFragment;
import com.example.yzbkaka.kakaAndroid.core.tree.TreeFragment;
import com.example.yzbkaka.kakaAndroid.event.Event;
import com.example.yzbkaka.kakaAndroid.event.RxEvent;
import com.example.yzbkaka.kakaAndroid.ui.base.BaseActivity;
import com.example.yzbkaka.kakaAndroid.utils.ViewAnimatorHelper;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;

    private FloatingActionButton btn_scroll_top;

    private TextView mNameView;

    private ImageView mAvatarView;

    private Button[] btns;

    private Fragment[] fragments;

    /**
     * 当前tab的序号
     */
    private int currentPosition;

    /**
     * 切换的fragment的序号
     */
    private int index;

    /**
     * 动画类
     */
    private ViewAnimatorHelper viewAnimatorHelper;

    /**
     * 双击退出时间
     */
    private long exitTime = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  //调用父类直接进行加载
        setContentView(R.layout.activity_main);
        //设置旋转开关
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        mNavigationView.setItemIconTintList(null);  //显示图片自身的颜色
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_favorite_article:
                        Toast.makeText(MainActivity.this, "喜欢的文章", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about:
                        Toast.makeText(MainActivity.this, "关于我们", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_exit:
                        Toast.makeText(MainActivity.this, "退出", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        initNavigationHeaderView();
        initFragments();
        viewAnimatorHelper = new ViewAnimatorHelper();
        viewAnimatorHelper.bindView(btn_scroll_top);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViews() {
        mDrawerLayout =  findViewById(R.id.drawerLayout);
        mNavigationView =  findViewById(R.id.navigation_view);
        btn_scroll_top = findViewById(R.id.btn_scroll_top);
        btns = new Button[4];
        btns[0] = findViewById(R.id.btn_main);
        btns[1] = findViewById(R.id.btn_system);
        btns[2] = findViewById(R.id.btn_chapter);
        btns[3] = findViewById(R.id.btn_project);
        btns[0].setSelected(true);
        for(int i = 0;i < 4;i++){
            btns[i].setOnClickListener(this);
            if(i != currentPosition){  //设置缩放
                btns[i].setScaleX(0.9f);
                btns[i].setScaleY(0.9f);
            }
        }

        btn_scroll_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String action = "";
                switch (currentPosition){
                    case 0:
                        action = Const.EVENT_ACTION.HOME;
                        break;
                    case 1:
                        action = Const.EVENT_ACTION.SYSTEM;
                        break;
                    case 2:
                        ((ChaptersFragment)fragments[2]).scrollToTop();
                        return;
                    case 3:
                        ((ProjectFragment)fragments[3]).scrollToTop();
                        return;
                }
                RxEvent.getInstance().postEvent(action,new Event(Event.Type.SCROLL_TOP));  //传递事件
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main:
                index = 0;
                break;
            case R.id.btn_system:
                index = 1;
                break;
            case R.id.btn_chapter:
                index = 2;
                break;
            case R.id.btn_project:
                index = 3;
                break;
            default:
                break;
        }
        showCurrentFragment(index);
    }


    @Override
    protected boolean initToolbar(){
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        return true;
    }


    /**
     * 初始化侧滑栏头部
     */
    private void initNavigationHeaderView(){
        View mHeaderView = mNavigationView.getHeaderView(0);
        mAvatarView = mHeaderView.findViewById(R.id.img_avatar);
        mNameView = mHeaderView.findViewById(R.id.tv_name);
    }


    /**
     * 初始化fragment
     */
    private void initFragments(){
        fragments = new Fragment[]{new HomeFragment(), new TreeFragment(),new ChaptersFragment(),new ProjectFragment()};
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container,fragments[0]).show(fragments[0]).commitAllowingStateLoss();
    }


    /**
     * 切换fragment
     */
    private void showCurrentFragment(int index){
        if (currentPosition != index) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(fragments[currentPosition]);
            if (!fragments[index].isAdded()) {
                fragmentTransaction.add(R.id.container, fragments[index]);
            }
            fragmentTransaction.show(fragments[index]).commit();  //进行切换展示
            btns[currentPosition].setSelected(false);  //不再选中
            btns[index].setSelected(true);  //设置选中
            scaleView();
            currentPosition = index;
            setCurrentTitle();
        }
    }


    /**
     * 进行缩放/恢复
     */
    private void scaleView(){
        btns[currentPosition].animate().scaleX(0.9f).scaleY(0.9f)
                .setDuration(150).start();
        btns[index].animate().scaleX(1.0f).scaleY(1.0f)
                .setDuration(150).start();
    }


    /**
     * 切换toolbar上的标题
     */
    private void setCurrentTitle(){
        if (currentPosition == 0)
            mToolbar.setTitle(R.string.app_name);
        else if (currentPosition == 1)
            mToolbar.setTitle(R.string.system);
        else if(currentPosition == 2)
            mToolbar.setTitle(R.string.chapter);
        else if (currentPosition == 3)
            mToolbar.setTitle(R.string.project);
    }


    @Override
    protected void onResume(){
        super.onResume();
        setUserData();  //设置用户登录
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu_setting,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    protected void receiveEvent(Object object){
        Event event = (Event)object;
        if(event.type == Event.Type.SCALE){
            scroll((int)event.object);
        }
    }


    /**
     * 设置FloatingActionBar
     */
    private void scroll(int offsetY){
        if (offsetY > 0 && btn_scroll_top.getVisibility() != View.INVISIBLE && !viewAnimatorHelper.isAnimating()){
            viewAnimatorHelper.hideFloatActionButton();
        }else if (offsetY < 0 && btn_scroll_top.getVisibility() != View.VISIBLE){
            viewAnimatorHelper.showFloatActionButton();
        }
    }


    @Override
    protected String registerEvent() {
        return Const.EVENT_ACTION.MAIN;
    }


    /**
     * 设置双击退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()- exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次切换到桌面", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
            }
            return true;
        }
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
