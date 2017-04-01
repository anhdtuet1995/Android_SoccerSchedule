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

    private NewsMainFragment newsMainFragment;
    private FavoriteTeamFragment favoriteTeamFragment;
    private CompetitionListFragment competitionListFragment;

    public ViewPagerMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NewsMainFragment();
            case 1:
                return new NewsMainFragment();
            case 2:
                return new NewsMainFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Constant.NEWS_MAIN;
            case 1:
                return Constant.FAVORITE_MAIN;
            case 2:
                return Constant.COMPETITION_MAIN;
        }
        return null;
    }
}
