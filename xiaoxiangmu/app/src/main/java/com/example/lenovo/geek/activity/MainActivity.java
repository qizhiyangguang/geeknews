package com.example.lenovo.geek.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.base.BaseActivity;
import com.example.lenovo.geek.fragment.AboutFragment;
import com.example.lenovo.geek.fragment.CollectFragment;
import com.example.lenovo.geek.fragment.GankFragment;
import com.example.lenovo.geek.fragment.GoldFragment;
import com.example.lenovo.geek.fragment.SettingFragment;
import com.example.lenovo.geek.fragment.V2exFragment;
import com.example.lenovo.geek.fragment.WeChatFragment;
import com.example.lenovo.geek.fragment.ZhihuDailyNewsFragment;
import com.example.lenovo.geek.presenter.MainP;
import com.example.lenovo.geek.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;

//马畔畔-1808D-15：35
public class MainActivity extends BaseActivity<MainView, MainP> implements MainView {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> strings;
    private FragmentManager supportFragmentManager;
    private int TYPE_ZHIHU = 0;
    private int TYPE_WECHAT = 1;
    private int TYPE_GANK = 2;
    private int TYPE_GOLD = 3;
    private int TYPE_V2EX = 4;
    private int TYPE_COLLECT = 5;
    private int TYPE_SETTINGS = 6;
    private int TYPE_ABOUT = 7;
    private int mLastFragmentPosition;
    private MenuItem item;

    @Override
    protected MainP initPresenter() {
        return new MainP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initTitle() {
        strings = new ArrayList<>();
        strings.add(R.id.zhihu);
        strings.add(R.id.wechat);
        strings.add(R.id.gank);
        strings.add(R.id.gold);
        strings.add(R.id.v2ex);
        strings.add(R.id.collect);
        strings.add(R.id.settings);
        strings.add(R.id.about);

    }

    protected void initView() {
        supportFragmentManager = getSupportFragmentManager();
        toolBar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolBar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolBar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        initFragment();
        initTitle();
        addZhihuDailyNewsFragment();
    }

    private void addZhihuDailyNewsFragment() {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragments.get(0));
        fragmentTransaction.commit();
        toolBar.setTitle(strings.get(0));
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ZhihuDailyNewsFragment());
        fragments.add(new WeChatFragment());
        fragments.add(new GankFragment());
        fragments.add(new GoldFragment());
        fragments.add(new V2exFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingFragment());
        fragments.add(new AboutFragment());
    }

    @Override
    protected void initListener() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.info && itemId != R.id.options_title) {
                    item.setCheckable(true);
                    switch (itemId) {
                        case R.id.zhihu:
                            switchFragment(TYPE_ZHIHU);
                            break;
                        case R.id.wechat:
                            switchFragment(TYPE_WECHAT);
                            break;
                        case R.id.gank:
                            switchFragment(TYPE_GANK);
                            break;
                        case R.id.gold:
                            switchFragment(TYPE_GOLD);
                            break;
                        case R.id.v2ex:
                            mSearchView.setVisibility(View.GONE);
                            switchFragment(TYPE_V2EX);
                            break;
                        case R.id.collect:
                            switchFragment(TYPE_COLLECT);
                            break;
                        case R.id.settings:
                            switchFragment(TYPE_SETTINGS);
                            break;
                        case R.id.about:
                            switchFragment(TYPE_ABOUT);
                            break;
                    }
                    dl.closeDrawer(Gravity.LEFT);
                } else {
                    item.setCheckable(false);
                }
                return false;
            }
        });

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }

    private void switchFragment(int type) {
        //显示一个Fragment,隐藏一个fragment
        //显示
        Fragment fragment = fragments.get(type);
        //需要隐藏
        Fragment hideFragment = fragments.get(mLastFragmentPosition);
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        }
        fragmentTransaction.hide(hideFragment);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
        mLastFragmentPosition = type;
        //显示或者隐藏搜索框
        if (type == TYPE_WECHAT || type == TYPE_GANK) {
            item.setVisible(true);
        } else {
            item.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        item = menu.findItem(R.id.sousuo);
        //隐藏搜索框
        item.setVisible(false);
        mSearchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
