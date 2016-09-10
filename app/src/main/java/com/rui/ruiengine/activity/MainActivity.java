package com.rui.ruiengine.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;
import com.rui.ruiengine.R;

/**
 * Created by rhuang on 3/8/16.
 */
public class MainActivity extends AppCompatActivity {
    final String PREFS_NAME = "prefs_file";

    SharedPreferences mPrefs = null;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    CirclePageIndicator mIndicator;


    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fragmentManager) {

            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String SECTION_NUM_ARG = "section_num";

        public static PlaceholderFragment newInstance(int sectionNum) {
            PlaceholderFragment fragment = new PlaceholderFragment();

            Bundle args = new Bundle();
            args.putInt(SECTION_NUM_ARG, sectionNum);
            fragment.setArguments(args);

            return fragment;
        }

        public PlaceholderFragment() {


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            Bundle bundle = getArguments();
            int page = bundle.getInt(SECTION_NUM_ARG);

            int layout = getResources().getIdentifier("activity_main_ads_page" + page, "layout", "com.rui.ruiengine");
            View rootView = inflater.inflate(layout, container, false);

            if(page == 4) {
                Button startBtn = (Button)rootView.findViewById(R.id.start_btn);
                startBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(), SigninActivity.class);
                        startActivity(intent);
                        AppCompatActivity activity = (AppCompatActivity) getContext();
                        activity.finish();
                    }
                });

            }
            return rootView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);

        final float density = getResources().getDisplayMetrics().density;
        mIndicator.setRadius(7 * density);
        mIndicator.setFillColor(0xFFFFFFFF);
        mIndicator.setPageColor(0xFF466066);
        mIndicator.setStrokeColor(0xFFFFFFFF);
        mIndicator.setStrokeWidth(2 * density);
        mIndicator.setSnap(false);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if(mPrefs.getBoolean("firstRun", true)) {
            mPrefs.edit().putBoolean("firstRun", false).commit();

        }
        else
        {

            // We start MainActivity
            Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
