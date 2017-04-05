package com.uet.anhdt.soccerschedule.fragments.main;

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
import com.uet.anhdt.soccerschedule.adapters.CompetitionListAdapter;
import com.uet.anhdt.soccerschedule.fragments.BaseFragment;
import com.uet.anhdt.soccerschedule.models.competition.CompetitionInfomation;
import com.uet.anhdt.soccerschedule.services.CompetitionListAPI;
import com.uet.anhdt.soccerschedule.services.InitService;
import com.uet.anhdt.soccerschedule.utils.Constant;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompetitionListFragment extends BaseFragment {

    private RecyclerView recyclerViewCompetitionList;
    private ArrayList<CompetitionInfomation> competitionInfomationArrayList;
    private CompetitionListAdapter competitionListAdapter;
    private Activity activityContainsFragment;

    public CompetitionListFragment() {
        // Required empty public constructor
    }

    public static CompetitionListFragment newInstance() {
        CompetitionListFragment fragment = new CompetitionListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContainsFragment = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        competitionInfomationArrayList = new ArrayList<>();
        competitionListAdapter = new CompetitionListAdapter(activityContainsFragment, competitionInfomationArrayList);

        getCompetitions();
        Log("onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_competition_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view) {
        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        recyclerViewCompetitionList = (RecyclerView) view.findViewById(R.id.recyclerViewCompetitionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activityContainsFragment);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCompetitionList.setLayoutManager(linearLayoutManager);
        recyclerViewCompetitionList.setAdapter(competitionListAdapter);
    }

    private void getCompetitions() {
        CompetitionListAPI competitionListAPI = InitService.getInstance().createService(CompetitionListAPI.class);
        competitionListAPI.getCompetitionInfo(Constant.API_SEASON_USAGE)
                .enqueue(new Callback<ArrayList<CompetitionInfomation>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CompetitionInfomation>> call, Response<ArrayList<CompetitionInfomation>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<CompetitionInfomation> competitionInfomations = response.body();
                            competitionInfomationArrayList.addAll(competitionInfomations);
                            competitionListAdapter.notifyDataSetChanged();
                            Log("Ok" + competitionInfomations.size());
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
                    public void onFailure(Call<ArrayList<CompetitionInfomation>> call, Throwable t) {
                        Toast.makeText(activityContainsFragment, R.string.error, Toast.LENGTH_LONG).show();
                        Log(t.getMessage());
                    }
                });
    }
}
