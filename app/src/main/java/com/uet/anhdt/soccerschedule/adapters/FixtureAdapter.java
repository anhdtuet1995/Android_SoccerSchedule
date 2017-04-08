package com.uet.anhdt.soccerschedule.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.models.fixture.FixtureToday;
import com.uet.anhdt.soccerschedule.utils.FixtureUtils;
import com.uet.anhdt.soccerschedule.utils.StatusFixture;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anhdt on 4/6/2017.
 */

public class FixtureAdapter extends FixtureBaseAdapter<FixtureToday> {


    public FixtureAdapter(Context context, List<String> nameOfCompetition, HashMap<String, List<FixtureToday>> dataOfMatch) {
        super(context, nameOfCompetition, dataOfMatch);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(mContext);
            convertView = li.inflate(R.layout.item_match, parent, false);
        }
        FixtureToday fixtureToday = (FixtureToday) getChild(groupPosition, childPosition);

        RelativeLayout relativeItemHome = (RelativeLayout) convertView.findViewById(R.id.relativeItemHome);
        TextView tvItemHomeName = (TextView) convertView.findViewById(R.id.tvItemHomeName);
        RelativeLayout relativeItemAway = (RelativeLayout) convertView.findViewById(R.id.relativeItemAway);
        TextView tvItemHomeAway = (TextView) convertView.findViewById(R.id.tvItemHomeAway);
        RelativeLayout relativeItemTime = (RelativeLayout) convertView.findViewById(R.id.relativeItemTime);
        TextView tvItemDate = (TextView) convertView.findViewById(R.id.tvItemDate);
        TextView tvItemTime = (TextView) convertView.findViewById(R.id.tvItemTime);

        tvItemHomeName.setText(fixtureToday.getHomeTeamName());
        tvItemHomeAway.setText(fixtureToday.getAwayTeamName());

        //set status for match
        if (fixtureToday.getStatus().equals(StatusFixture.FINISHED.toString())
                || fixtureToday.getStatus().equals(StatusFixture.FT.toString())) {
            //if the match finish
            tvItemDate.setText(FT);
            String getResult = fixtureToday.getResult().getGoalsHomeTeam() + " - " + fixtureToday.getResult().getGoalsAwayTeam();
            tvItemTime.setText(getResult);
        }
        else if (fixtureToday.getStatus().equals(StatusFixture.IN_PLAY.toString())) {
            //if the match in play
            tvItemDate.setText(LIVE);
            String getResult = fixtureToday.getResult().getGoalsHomeTeam() + " - " + fixtureToday.getResult().getGoalsAwayTeam();
            tvItemTime.setText(getResult);
        }
        else {
            //if the match timed
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat dfTime = new SimpleDateFormat("HH:mm");
            Date time = FixtureUtils.getDate(fixtureToday.getDate());
            tvItemDate.setText(df.format(time));
            tvItemTime.setText(dfTime.format(time));
        }
        return convertView;

    }
}
