package com.uet.anhdt.soccerschedule.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.uet.anhdt.soccerschedule.models.league.LeagueStandings;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by anhdt on 4/8/2017.
 */

public class LeagueTableAdapter extends BaseLeagueTableAdapter {

    private ArrayList<LeagueStandings> leagueStandingsArrayList;

    public LeagueTableAdapter(Context mContext, ArrayList<LeagueStandings> leagueStandingses) {
        super(mContext);
        leagueStandingsArrayList = leagueStandingses;

    }

    public void setLeagueStandingsArrayList(ArrayList<LeagueStandings> leagueStandingsArrayList) {
        this.leagueStandingsArrayList.clear();
        this.leagueStandingsArrayList.addAll(leagueStandingsArrayList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_ITEM:
                LeagueStandings leagueStandings = leagueStandingsArrayList.get(position-1);
                ItemLeagueTableViewHolder itemLeagueTableViewHolder = (ItemLeagueTableViewHolder) holder;
                itemLeagueTableViewHolder.tvTeamName.setText(leagueStandings.getTeamName());
                itemLeagueTableViewHolder.tvPosition.setText(String.format(Locale.getDefault(), "%d", leagueStandings.getPosition()));
                itemLeagueTableViewHolder.tvDifferenceGoals.setText(String.format(Locale.getDefault(), "%d", leagueStandings.getGoalDifference()));
                itemLeagueTableViewHolder.tvPlayedMatch.setText(String.format(Locale.getDefault(), "%d", leagueStandings.getPlayedGames()));
                itemLeagueTableViewHolder.tvPoints.setText(String.format(Locale.getDefault(), "%d", leagueStandings.getPoints()));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return leagueStandingsArrayList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }
}
