package com.swetabh.whetherapidemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.swetabh.whetherapidemo.fragments.bangalore.BangaloreFragment;
import com.swetabh.whetherapidemo.fragments.bangalore.BangalorePresenterImp;
import com.swetabh.whetherapidemo.fragments.chennai.ChennaiFragment;
import com.swetabh.whetherapidemo.fragments.chennai.ChennaiPresenterImp;
import com.swetabh.whetherapidemo.fragments.delhi.DelhiFragment;
import com.swetabh.whetherapidemo.fragments.delhi.DelhiPresenterImp;

public class MainActivity extends AppCompatActivity implements MainContract.MainCommunicator {

    private FragmentManager mFragmentManager;
    private BasePresenter mPresenter;
    private Fragment mCurrentFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    addFragment(BangaloreFragment.newInstance(), new BangalorePresenterImp());
                    return true;
                case R.id.navigation_dashboard:
                    addFragment(DelhiFragment.newInstance(), new DelhiPresenterImp());
                    return true;
                case R.id.navigation_notifications:
                    addFragment(ChennaiFragment.newInstance(), new ChennaiPresenterImp());
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        mFragmentManager = getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        openDefaultFragment();
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(R.string.app_name);
        }
    }

    private void openDefaultFragment() {
        addFragment(BangaloreFragment.newInstance(), new BangalorePresenterImp());
    }

    private void addFragment(Fragment fragment, BasePresenter presenter) {
        mCurrentFragment = fragment;
        mPresenter = presenter;
        attachCommunicator();
        attachView();

        mFragmentManager.beginTransaction()
                .replace(R.id.content, mCurrentFragment)
                .commit();
    }

    private void attachView() {
        if (mPresenter != null)
            mPresenter.attachView((BaseView) mCurrentFragment);
    }

    private void attachCommunicator() {
        if (mCurrentFragment != null && mCurrentFragment instanceof BaseView)
            ((BaseView) mCurrentFragment).setCommunicator(this);
    }

}
