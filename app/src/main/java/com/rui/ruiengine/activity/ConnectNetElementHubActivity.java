package com.rui.ruiengine.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rui.ruiengine.R;
import com.rui.ruiengine.adapter.ViewPagerAdapter;
import com.rui.ruiengine.fragment.FragmentNetElementList;
import com.rui.ruiengine.fragment.FragmentAppHelp;

/**
 * Created by rhuang on 1/2/16.
 */
public class ConnectNetElementHubActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connect_ne_hub);

        mToolbar = (Toolbar)findViewById(R.id.connect_ne_hub_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mViewPager = (ViewPager)findViewById(R.id.connect_ne_hub_viewpager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.connect_ne_hub_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentNetElementList(), "Net Element List");
        adapter.addFragment(new FragmentAppHelp(), "App Help");
        viewPager.setAdapter(adapter);

    }


}
