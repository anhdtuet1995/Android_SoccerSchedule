package com.uet.anhdt.soccerschedule.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.uet.anhdt.soccerschedule.models.championleague.ChampionLeagueGroup;
import com.uet.anhdt.soccerschedule.models.championleague.ChampionLeagueStanding;

import java.util.Locale;

/**
 * Created by anhdt on 4/8/2017.
 */

public class ChampionLeagueTableAdapter extends BaseLeagueTableAdapter {

    private ChampionLeagueStanding championLeagueStanding;

    public ChampionLeagueTableAdapter(Context mContext, ChampionLeagueStanding championLeagueStanding) {
        super(mContext);
        this.championLeagueStanding = championLeagueStanding;
    }

    public void setChampionLeagueStanding(ChampionLeagueStanding championLeagueStanding) {
        this.championLeagueStanding = championLeagueStanding;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case TYPE_ITEM:
                ItemLeagueTableViewHolder itemLeagueTableViewHolder = (ItemLeagueTableViewHolder) holder;
                switch (position / 5) {
                    case 0:
                        //Group A
                        ChampionLeagueGroup groupA = championLeagueStanding.getA().get(getRealPos(position));
                        setTeam(itemLeagueTableViewHolder, groupA);
                        break;
                    case 1:
                        //Group B
                        ChampionLeagueGroup groupB = championLeagueStanding.getB().get(getRealPos(position));
                        setTeam(itemLeagueTableViewHolder, groupB);
                        break;
                    case 2:
                        //Group C
                        ChampionLeagueGroup groupC = championLeagueStanding.getC().get(getRealPos(position));
                        setTeam(itemLeagueTableViewHolder, groupC);
                        break;
                    case 3:
                        //Group A
                        ChampionLeagueGroup groupD = championLeagueStanding.getD().get(getRealPos(position));
                        setTeam(itemLeagueTableViewHolder, groupD);
                        break;
                    case 4:
                        //Group A
                        ChampionLeagueGroup groupE = championLeagueStanding.getE().get(getRealPos(position));
                        setTeam(itemLeagueTableViewHolder, groupE);
                        break;
                    case 5:
                        //Group A
                        ChampionLeagueGroup groupF = championLeagueStanding.getF().get(getRealPos(position));
                        setTeam(itemLeagueTableViewHolder, groupF);
                        break;
                    case 6:
                        //Group A
                        ChampionLeagueGroup groupG = championLeagueStanding.getG().get(getRealPos(position));
                        setTeam(itemLeagueTableViewHolder, groupG);
                        break;
                    case 7:
                        //Group A
                        ChampionLeagueGroup groupH = championLeagueStanding.getH().get(getRealPos(position));
                        setTeam(itemLeagueTableViewHolder, groupH);
                        break;
                }
                break;
            case TYPE_HEADER:
                HeaderLeagueTableViewHolder headerLeagueTableViewHolder = (HeaderLeagueTableViewHolder) holder;
                headerLeagueTableViewHolder.tvGroupName.setText(String.format(Locale.getDefault(), "Bảng %d", position / 5 + 1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (championLeagueStanding.getA() != null) {
            return championLeagueStanding.getA().size() * 8 + 8;
        }
        return 0;
    }

    private void setTeam(ItemLeagueTableViewHolder itemLeagueTableViewHolder, ChampionLeagueGroup championLeagueGroup) {
        itemLeagueTableViewHolder.tvTeamName.setText(championLeagueGroup.getTeam());
        itemLeagueTableViewHolder.tvPosition.setText(String.format(Locale.getDefault(), "%d", championLeagueGroup.getRank()));
        itemLeagueTableViewHolder.tvDifferenceGoals.setText(String.format(Locale.getDefault(), "%d", championLeagueGroup.getGoalDifference()));
        itemLeagueTableViewHolder.tvPlayedMatch.setText(String.format(Locale.getDefault(), "%d", championLeagueGroup.getPlayedGames()));
        itemLeagueTableViewHolder.tvPoints.setText(String.format(Locale.getDefault(), "%d", championLeagueGroup.getPoints()));
    }

    private int getRealPos(int position) {
        return position % 5 - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 5 == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }
}
