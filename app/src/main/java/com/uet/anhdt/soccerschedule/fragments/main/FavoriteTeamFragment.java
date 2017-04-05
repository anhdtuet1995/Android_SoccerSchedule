package com.uet.anhdt.soccerschedule.fragments.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.fragments.BaseFragment;

public class FavoriteTeamFragment extends BaseFragment {

    public FavoriteTeamFragment() {
        // Required empty public constructor
    }


    public static FavoriteTeamFragment newInstance() {
        FavoriteTeamFragment fragment = new FavoriteTeamFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false);
    }


}
