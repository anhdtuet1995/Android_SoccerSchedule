package com.uet.anhdt.soccerschedule.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.adapters.ViewPagerCompetitionAdapter;
import com.uet.anhdt.soccerschedule.fragments.competition.FixtureFragment;
import com.uet.anhdt.soccerschedule.fragments.competition.LeagueTableFragment;
import com.uet.anhdt.soccerschedule.fragments.competition.ResultFragment;
import com.uet.anhdt.soccerschedule.utils.CompetitionUtil;
import com.uet.anhdt.soccerschedule.utils.Constant;

public class CompetitionActivity extends AppCompatActivity implements View.OnClickListener{

    private int competitionId;
    private int competitionCurrentMatch;
    private int competitionNumberMatches;
    private String competitionName;

    private ImageView imvCompetitionBack;
    private TextView tvCompetitionName;

    private TabLayout tabLayoutCompetitionTask;
    private ViewPager viewPagerCompetitionTask;

    private ViewPagerCompetitionAdapter viewPagerCompetitionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        if (getIntent().getExtras() != null) {
            competitionId = getIntent().getIntExtra(Constant.MAIN_COMPETITION_ID, 0);
            competitionCurrentMatch = getIntent().getIntExtra(Constant.MAIN_COMPETITION_CURRENT, 0);
            competitionNumberMatches = getIntent().getIntExtra(Constant.MAIN_COMPETITION_NUMBER_MATCH, 0);
            competitionName = getIntent().getStringExtra(Constant.MAIN_COMPETITION_NAME);
        }

        initViews();
    }

    private void initViews() {
        imvCompetitionBack = (ImageView) findViewById(R.id.imvCompetitionBack);
        imvCompetitionBack.setOnClickListener(this);

        tvCompetitionName = (TextView) findViewById(R.id.tvCompetitionName);
        tvCompetitionName.setText(competitionName);

        tabLayoutCompetitionTask = (TabLayout) findViewById(R.id.tabLayoutCompetitionTask);
        viewPagerCompetitionTask = (ViewPager) findViewById(R.id.viewPagerCompetitionTask);

        initTabLayout();
    }

    private void initTabLayout() {
        viewPagerCompetitionAdapter = new ViewPagerCompetitionAdapter(getSupportFragmentManager());
        viewPagerCompetitionAdapter.addFrag(FixtureFragment.newInstance(competitionId, competitionCurrentMatch, competitionNumberMatches), Constant.COMPETITION_FIXTURE);
        viewPagerCompetitionAdapter.addFrag(ResultFragment.newInstance(competitionId, competitionCurrentMatch, competitionNumberMatches), Constant.COMPETITION_RESULT);
        if (!CompetitionUtil.checkCompetition(competitionId)) {
            viewPagerCompetitionAdapter.addFrag(LeagueTableFragment.newInstance(competitionId), Constant.COMPETION_TABLE);
            viewPagerCompetitionTask.setOffscreenPageLimit(3);
        }
        else {
            viewPagerCompetitionTask.setOffscreenPageLimit(2);
        }
        viewPagerCompetitionTask.setAdapter(viewPagerCompetitionAdapter);

        tabLayoutCompetitionTask.setupWithViewPager(viewPagerCompetitionTask);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imvCompetitionBack:
                this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
