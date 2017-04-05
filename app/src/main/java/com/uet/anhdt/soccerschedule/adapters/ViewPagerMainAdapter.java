package com.uet.anhdt.soccerschedule.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.uet.anhdt.soccerschedule.fragments.main.CompetitionListFragment;
import com.uet.anhdt.soccerschedule.fragments.main.FavoriteTeamFragment;
import com.uet.anhdt.soccerschedule.fragments.main.NewsMainFragment;
import com.uet.anhdt.soccerschedule.utils.Constant;

/**
 * Created by anhdt on 4/1/2017.
 */

public class ViewPagerMainAdapter extends FragmentStatePagerAdapter {

    private NewsMainFragment newsMainFragment = NewsMainFragment.newInstance();
    private CompetitionListFragment competitionListFragment = CompetitionListFragment.newInstance();

    public ViewPagerMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return newsMainFragment;
            case 1:
                return competitionListFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Constant.NEWS_MAIN;
            case 1:
                return Constant.COMPETITION_MAIN;
        }
        return null;
    }
}
