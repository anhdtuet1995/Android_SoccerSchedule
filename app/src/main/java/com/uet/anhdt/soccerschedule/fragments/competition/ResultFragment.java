package com.uet.anhdt.soccerschedule.fragments.competition;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.diegocarloslima.fgelv.lib.FloatingGroupExpandableListView;
import com.diegocarloslima.fgelv.lib.WrapperExpandableListAdapter;
import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.adapters.FixtureCompetitionAdapter;
import com.uet.anhdt.soccerschedule.fragments.BaseFragment;
import com.uet.anhdt.soccerschedule.models.competition.fixture.CompetitionFixture;
import com.uet.anhdt.soccerschedule.models.competition.fixture.CompetitionFixtureRootObject;
import com.uet.anhdt.soccerschedule.services.CompetitionFixtureAPI;
import com.uet.anhdt.soccerschedule.services.InitService;
import com.uet.anhdt.soccerschedule.utils.Constant;
import com.uet.anhdt.soccerschedule.views.PinnedHeaderExpListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResultFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private FloatingGroupExpandableListView expandableResultListMatch;
    private SwipeRefreshLayout swipeRefreshResultLayout;
    private RelativeLayout relativeResultProgress;

    private int competitionId;
    private int currentMatch;
    private int numberMatches;


    private FixtureCompetitionAdapter fixtureAdapter;
    private List<String> nameOfCompetition;
    private HashMap<String, List<CompetitionFixture>> dataOfMatch;

    private Activity activityContainsFragment;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContainsFragment = (Activity) context;
    }

    public static ResultFragment newInstance(int competitionId, int currentMatch, int numberMatches) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.MAIN_COMPETITION_ID, competitionId);
        args.putInt(Constant.MAIN_COMPETITION_CURRENT, currentMatch);
        args.putInt(Constant.MAIN_COMPETITION_NUMBER_MATCH, numberMatches);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            competitionId = getArguments().getInt(Constant.MAIN_COMPETITION_ID);
            currentMatch = getArguments().getInt(Constant.MAIN_COMPETITION_CURRENT);
            numberMatches = getArguments().getInt(Constant.MAIN_COMPETITION_NUMBER_MATCH);
        }
        nameOfCompetition = new ArrayList<>();
        dataOfMatch = new HashMap<>();

        fixtureAdapter = new FixtureCompetitionAdapter(activityContainsFragment, nameOfCompetition, dataOfMatch);

        getMatches();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //swipe to reload list
        swipeRefreshResultLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshResultLayout);
        relativeResultProgress = (RelativeLayout) view.findViewById(R.id.relativeResultProgress);

        expandableResultListMatch = (FloatingGroupExpandableListView) view.findViewById(R.id.expandableResultListMatch);
        WrapperExpandableListAdapter wrapperAdapter = new WrapperExpandableListAdapter(fixtureAdapter);
        expandableResultListMatch.setAdapter(wrapperAdapter);
        expandableResultListMatch.setGroupIndicator(null);
        expandableResultListMatch.setDividerHeight(0);

        swipeRefreshResultLayout.setOnRefreshListener(this);

    }

    private void getMatches() {
        CompetitionFixtureAPI competitionFixtureAPI = InitService.getInstance().createService(CompetitionFixtureAPI.class);
        competitionFixtureAPI.getCompetitionFixture(competitionId, Constant.API_SEASON_USAGE)
                .enqueue(new Callback<CompetitionFixtureRootObject>() {
                    @Override
                    public void onResponse(Call<CompetitionFixtureRootObject> call, Response<CompetitionFixtureRootObject> response) {
                        if (response.isSuccessful()) {
                            nameOfCompetition.clear();
                            dataOfMatch.clear();
                            CompetitionFixtureRootObject fixtureRootObject = response.body();
                            ArrayList<CompetitionFixture> fixtureTodays = fixtureRootObject.getFixtures();
                            Collections.sort(fixtureTodays, new Comparator<CompetitionFixture>() {
                                @Override
                                public int compare(CompetitionFixture o1, CompetitionFixture o2) {
                                    if (o1.getMatchday() < o2.getMatchday()) {
                                        return 1;
                                    }
                                    else if (o1.getMatchday() > o2.getMatchday()) {
                                        return -1;
                                    }
                                    else return 0;
                                }
                            });
                            for (CompetitionFixture fixtureToday: fixtureTodays) {
                                if (fixtureToday.getMatchday() < currentMatch) {
                                    String name = "Vòng " + fixtureToday.getMatchday();
                                    if (!nameOfCompetition.contains(name)) {
                                        nameOfCompetition.add(name);
                                    }
                                    if (!dataOfMatch.containsKey(name)) {
                                        dataOfMatch.put(name, new ArrayList<CompetitionFixture>());
                                    }
                                    dataOfMatch.get(name).add(fixtureToday);
                                    fixtureAdapter.notifyDataSetChanged();
                                }

                            }

                            if (relativeResultProgress != null && relativeResultProgress.getVisibility() == View.VISIBLE) {
                                relativeResultProgress.setVisibility(View.GONE);
                            }
                            if (expandableResultListMatch != null && expandableResultListMatch.getVisibility() == View.GONE) {
                                expandableResultListMatch.setVisibility(View.VISIBLE);
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
                            if (relativeResultProgress != null && relativeResultProgress.getVisibility() == View.VISIBLE) {
                                relativeResultProgress.setVisibility(View.GONE);
                            }
                            if (expandableResultListMatch != null && expandableResultListMatch.getVisibility() == View.GONE) {
                                expandableResultListMatch.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CompetitionFixtureRootObject> call, Throwable t) {
                        Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                        Log(t.getMessage());
                    }
                });
    }

    @Override
    public void onRefresh() {
        relativeResultProgress.setVisibility(View.VISIBLE);
        expandableResultListMatch.setVisibility(View.GONE);
        getMatches();
        swipeRefreshResultLayout.setRefreshing(false);
    }
}
