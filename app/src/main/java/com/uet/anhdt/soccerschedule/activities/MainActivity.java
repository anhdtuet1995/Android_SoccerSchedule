package com.uet.anhdt.soccerschedule.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.adapters.ViewPagerMainAdapter;

public class MainActivity extends BaseActivity {

    private TabLayout tabLayoutMainTask;
    private ViewPager viewPagerMainTask;
    private ViewPagerMainAdapter viewPagerMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        handleTabLayout();

        handleEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initViews() {
        tabLayoutMainTask = (TabLayout) findViewById(R.id.tabLayoutMainTask);
        viewPagerMainTask = (ViewPager) findViewById(R.id.viewPagerMainTask);
    }

    private void handleTabLayout() {

        tabLayoutMainTask.setupWithViewPager(viewPagerMainTask);
        setupViewPager();
        animateForTabSelected(tabLayoutMainTask.getSelectedTabPosition());
        tabLayoutMainTask.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                animateForTabSelected(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ViewGroup vg = (ViewGroup) tabLayoutMainTask.getChildAt(0);
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(tab.getPosition());
                vgTab.setScaleX(1f);
                vgTab.setScaleY(1f);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        ViewGroup vg = (ViewGroup) tabLayoutMainTask.getChildAt(0);
//        int tabsCount = vg.getChildCount();
//        for (int i = 0; i < tabsCount; i++)
//        {
//            int delay = (i * 150) + 750; //this is starting delay
//            ViewGroup vgTab = (ViewGroup) vg.getChildAt(i);
//            vgTab.setScaleX(0f);
//            vgTab.setScaleY(0f);
//
//            vgTab.animate()
//                    .scaleX(1f)
//                    .scaleY(1f)
//                    .setStartDelay(delay)
//                    .setInterpolator(new FastOutSlowInInterpolator())
//                    .setDuration(450)
//                    .start();
//        }
    }

    private void animateForTabSelected(int position) {
        ViewGroup vg = (ViewGroup) tabLayoutMainTask.getChildAt(0);
        ViewGroup vgTab = (ViewGroup) vg.getChildAt(position);
        vgTab.setScaleX(1.2f);
        vgTab.setScaleY(1.2f);
    }

    private void setupViewPager() {
        viewPagerMainAdapter = new ViewPagerMainAdapter(getSupportFragmentManager());
        viewPagerMainTask.setAdapter(viewPagerMainAdapter);
    }

    private void handleEvents() {

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:

                break;
        }
    }
}
