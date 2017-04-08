package com.uet.anhdt.soccerschedule.fragments.competition;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.adapters.ChampionLeagueTableAdapter;
import com.uet.anhdt.soccerschedule.adapters.LeagueTableAdapter;
import com.uet.anhdt.soccerschedule.fragments.BaseFragment;
import com.uet.anhdt.soccerschedule.models.championleague.ChampionLeagueRootObject;
import com.uet.anhdt.soccerschedule.models.championleague.ChampionLeagueStanding;
import com.uet.anhdt.soccerschedule.models.competition.fixture.CompetitionFixture;
import com.uet.anhdt.soccerschedule.models.competition.fixture.CompetitionFixtureRootObject;
import com.uet.anhdt.soccerschedule.models.league.LeagueRootObject;
import com.uet.anhdt.soccerschedule.models.league.LeagueStandings;
import com.uet.anhdt.soccerschedule.services.CompetitionFixtureAPI;
import com.uet.anhdt.soccerschedule.services.InitService;
import com.uet.anhdt.soccerschedule.services.LeagueTableAPI;
import com.uet.anhdt.soccerschedule.utils.Constant;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeagueTableFragment extends BaseFragment {

    private final int TYPE_LEAGUE = 0;
    private final int TYPE_GROUP = 1;


    private int competitionId;
    private int leagueTableType;

    private RecyclerView recyclerViewLeagueTable;

    //champion
    private ChampionLeagueTableAdapter championLeagueTableAdapter;
    private ChampionLeagueStanding championLeagueStanding;

    //normal league
    private LeagueTableAdapter leagueTableAdapter;
    private ArrayList<LeagueStandings> leagueStandingsArrayList;

    private Activity activityContainsFragment;

    public LeagueTableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContainsFragment = (Activity) context;
    }


    public static LeagueTableFragment newInstance(int competitionId) {
        LeagueTableFragment fragment = new LeagueTableFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.MAIN_COMPETITION_ID, competitionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            competitionId = getArguments().getInt(Constant.MAIN_COMPETITION_ID);
            leagueTableType = getType(competitionId);
        }

        switch (leagueTableType) {
            case TYPE_GROUP:
                championLeagueStanding = new ChampionLeagueStanding();
                championLeagueTableAdapter = new ChampionLeagueTableAdapter(activityContainsFragment, championLeagueStanding);
                getChampionLeague();
                break;
            case TYPE_LEAGUE:
                leagueStandingsArrayList = new ArrayList<>();
                leagueTableAdapter = new LeagueTableAdapter(activityContainsFragment, leagueStandingsArrayList);
                getLeague();
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_table, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewLeagueTable = (RecyclerView) view.findViewById(R.id.recyclerViewLeagueTable);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activityContainsFragment);
        recyclerViewLeagueTable.setLayoutManager(linearLayoutManager);

        switch (leagueTableType) {
            case TYPE_GROUP:
                recyclerViewLeagueTable.setAdapter(championLeagueTableAdapter);
                break;
            case TYPE_LEAGUE:
                recyclerViewLeagueTable.setAdapter(leagueTableAdapter);
                break;
        }
    }

    private int getType(int competitionId) {
        if (competitionId == 440) {
            return TYPE_GROUP;
        }
        else {
            return TYPE_LEAGUE;
        }
    }

    private void getLeague() {
        LeagueTableAPI leagueTableAPI = InitService.getInstance().createService(LeagueTableAPI.class);
        leagueTableAPI.getLeagueTable(competitionId, Constant.API_SEASON_USAGE)
                .enqueue(new Callback<LeagueRootObject>() {
                    @Override
                    public void onResponse(Call<LeagueRootObject> call, Response<LeagueRootObject> response) {
                        if (response.isSuccessful()) {
                            ArrayList<LeagueStandings> leagueStandingses = response.body().getStanding();
                            leagueTableAdapter.setLeagueStandingsArrayList(leagueStandingses);
                        }
                        else {
                            Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                            try {
                                Log(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LeagueRootObject> call, Throwable t) {
                        Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                        Log(t.getMessage());
                    }
                });
    }

    private void getChampionLeague() {
        LeagueTableAPI leagueTableAPI = InitService.getInstance().createService(LeagueTableAPI.class);
        leagueTableAPI.getChampionLeagueTable(competitionId, Constant.API_SEASON_USAGE)
                .enqueue(new Callback<ChampionLeagueRootObject>() {
                    @Override
                    public void onResponse(Call<ChampionLeagueRootObject> call, Response<ChampionLeagueRootObject> response) {
                        if (response.isSuccessful()) {
                            ChampionLeagueStanding championLeagueStanding = response.body().getStandings();
                            championLeagueTableAdapter.setChampionLeagueStanding(championLeagueStanding);
                        }
                        else {
                            Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                            try {
                                Log(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChampionLeagueRootObject> call, Throwable t) {
                        Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                        Log(t.getMessage());
                    }
                });
    }

}
