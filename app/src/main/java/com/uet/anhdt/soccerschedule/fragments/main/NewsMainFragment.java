package com.uet.anhdt.soccerschedule.fragments.main;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.adapters.FixtureAdapter;
import com.uet.anhdt.soccerschedule.fragments.BaseFragment;
import com.uet.anhdt.soccerschedule.models.competition.CompetitionInfomation;
import com.uet.anhdt.soccerschedule.models.fixture.FixtureRootObject;
import com.uet.anhdt.soccerschedule.models.fixture.FixtureToday;
import com.uet.anhdt.soccerschedule.services.FixtureTodayAPI;
import com.uet.anhdt.soccerschedule.services.InitService;
import com.uet.anhdt.soccerschedule.utils.Constant;
import com.uet.anhdt.soccerschedule.utils.FixtureUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsMainFragment extends BaseFragment {

    private ExpandableListView expandableListMatch;
    private FixtureAdapter fixtureAdapter;
    private List<String> nameOfCompetition;
    private HashMap<String, List<FixtureToday>> dataOfMatch;

    private Activity activityContainsFragment;

    public NewsMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContainsFragment = (Activity) context;
    }

    public static NewsMainFragment newInstance() {
        NewsMainFragment fragment = new NewsMainFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameOfCompetition = new ArrayList<>();
        dataOfMatch = new HashMap<>();

        fixtureAdapter = new FixtureAdapter(activityContainsFragment, nameOfCompetition, dataOfMatch);

        getMatches();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandableListMatch = (ExpandableListView) view.findViewById(R.id.expandableListMatch);
        DisplayMetrics metrics = new DisplayMetrics();
        activityContainsFragment.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        expandableListMatch.setIndicatorBounds(width - dp2px(50), width - dp2px(10));
        expandableListMatch.setAdapter(fixtureAdapter);
    }

    private void getMatches() {
        FixtureTodayAPI fixtureTodayAPI = InitService.getInstance().createService(FixtureTodayAPI.class);
        fixtureTodayAPI.getFixtureToday(Constant.API_SEASON_USAGE, Constant.API_DEFAULT_TIME_FRAME)
                .enqueue(new Callback<FixtureRootObject>() {
                    @Override
                    public void onResponse(Call<FixtureRootObject> call, Response<FixtureRootObject> response) {
                        if (response.isSuccessful()) {
                            FixtureRootObject fixtureRootObject = response.body();
                            ArrayList<FixtureToday> fixtureTodays = fixtureRootObject.getFixtures();
                            for (FixtureToday fixtureToday: fixtureTodays) {
                                String name = FixtureUtils.getNameOfCompetition(fixtureToday.getLinks().getCompetition().getHref());
                                if (!nameOfCompetition.contains(name)) {
                                    nameOfCompetition.add(name);
                                }
                                if (!dataOfMatch.containsKey(name)) {
                                    dataOfMatch.put(name, new ArrayList<FixtureToday>());
                                }
                                dataOfMatch.get(name).add(fixtureToday);
                                fixtureAdapter.notifyDataSetChanged();
                            }
                        }
                        else {
                            Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                            try {
                                Log(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //Log(t.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<FixtureRootObject> call, Throwable t) {
                        Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                        Log(t.getMessage());
                    }
                });
    }
}
