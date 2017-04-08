package com.uet.anhdt.soccerschedule.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.uet.anhdt.soccerschedule.fragments.competition.FixtureFragment;
import com.uet.anhdt.soccerschedule.fragments.competition.LeagueTableFragment;
import com.uet.anhdt.soccerschedule.fragments.competition.ResultFragment;
import com.uet.anhdt.soccerschedule.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhdt on 4/6/2017.
 */

public class ViewPagerCompetitionAdapter extends FragmentPagerAdapter {

    private int competitionId;
    private int currentMatch;
    private int numberMatches;

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerCompetitionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
