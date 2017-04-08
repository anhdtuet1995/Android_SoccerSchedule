package com.uet.anhdt.soccerschedule.fragments.competition;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.diegocarloslima.fgelv.lib.FloatingGroupExpandableListView;
import com.diegocarloslima.fgelv.lib.WrapperExpandableListAdapter;
import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.adapters.FixtureAdapter;
import com.uet.anhdt.soccerschedule.adapters.FixtureCompetitionAdapter;
import com.uet.anhdt.soccerschedule.fragments.BaseFragment;
import com.uet.anhdt.soccerschedule.models.competition.fixture.CompetitionFixture;
import com.uet.anhdt.soccerschedule.models.competition.fixture.CompetitionFixtureRootObject;
import com.uet.anhdt.soccerschedule.models.fixture.FixtureRootObject;
import com.uet.anhdt.soccerschedule.models.fixture.FixtureToday;
import com.uet.anhdt.soccerschedule.services.CompetitionFixtureAPI;
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

public class FixtureFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private FloatingGroupExpandableListView expandableFixtureListMatch;

    private SwipeRefreshLayout swipeRefreshFixtureLayout;
    private RelativeLayout relativeFixtureProgress;

    private int competitionId;
    private int currentMatch;
    private int numberMatches;

    private FixtureCompetitionAdapter fixtureAdapter;
    private List<String> nameOfCompetition;
    private HashMap<String, List<CompetitionFixture>> dataOfMatch;

    private Activity activityContainsFragment;

    public FixtureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContainsFragment = (Activity) context;
    }

    public static FixtureFragment newInstance(int competitionId, int currentMatch, int numberMatches) {
        FixtureFragment fragment = new FixtureFragment();
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
        Log("onCreateView");
        return inflater.inflate(R.layout.fragment_fixture, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //swipe to reload list
        swipeRefreshFixtureLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshFixtureLayout);
        relativeFixtureProgress = (RelativeLayout) view.findViewById(R.id.relativeFixtureProgress);

        expandableFixtureListMatch = (FloatingGroupExpandableListView) view.findViewById(R.id.expandableFixtureListMatch);
        Log("fixtureAdapter");
        WrapperExpandableListAdapter wrapperAdapter = new WrapperExpandableListAdapter(fixtureAdapter);
        expandableFixtureListMatch.setAdapter(wrapperAdapter);

        swipeRefreshFixtureLayout.setOnRefreshListener(this);
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
                            for (CompetitionFixture fixtureToday: fixtureTodays) {
                                if (fixtureToday.getMatchday() >= currentMatch) {
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

                        if (relativeFixtureProgress != null && relativeFixtureProgress.getVisibility() == View.VISIBLE) {
                            relativeFixtureProgress.setVisibility(View.GONE);
                        }
                        if (expandableFixtureListMatch != null && expandableFixtureListMatch.getVisibility() == View.GONE) {
                            expandableFixtureListMatch.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<CompetitionFixtureRootObject> call, Throwable t) {
                        Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                        Log(t.getMessage());

                        if (relativeFixtureProgress != null && relativeFixtureProgress.getVisibility() == View.VISIBLE) {
                            relativeFixtureProgress.setVisibility(View.GONE);
                        }
                        if (expandableFixtureListMatch != null && expandableFixtureListMatch.getVisibility() == View.GONE) {
                            expandableFixtureListMatch.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    public void destroyViews() {


    }

    @Override
    public void onRefresh() {
        relativeFixtureProgress.setVisibility(View.VISIBLE);
        expandableFixtureListMatch.setVisibility(View.GONE);
        getMatches();
        swipeRefreshFixtureLayout.setRefreshing(false);
    }
}
