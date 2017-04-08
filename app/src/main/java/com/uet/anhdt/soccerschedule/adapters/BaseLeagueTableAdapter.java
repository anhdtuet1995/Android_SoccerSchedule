package com.uet.anhdt.soccerschedule.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uet.anhdt.soccerschedule.R;

/**
 * Created by anhdt on 4/8/2017.
 */

public abstract class BaseLeagueTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final int TYPE_HEADER = 0;
    protected final int TYPE_ITEM = 1;
    protected Context mContext;


    public BaseLeagueTableAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_HEADER:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_header_league_table, parent, false);
                return new HeaderLeagueTableViewHolder(view);
            case TYPE_ITEM:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_league_table, parent, false);
                return new ItemLeagueTableViewHolder(view);
        }
        return null;
    }

    static class ItemLeagueTableViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPosition;
        public TextView tvTeamName;
        public TextView tvPlayedMatch;
        public TextView tvDifferenceGoals;
        public TextView tvPoints;

        public ItemLeagueTableViewHolder(View view) {
            super(view);
            tvPosition = (TextView) view.findViewById(R.id.tvPosition);
            tvTeamName = (TextView) view.findViewById(R.id.tvTeamName);
            tvPlayedMatch = (TextView) view.findViewById(R.id.tvPlayedMatch);
            tvDifferenceGoals = (TextView) view.findViewById(R.id.tvDifferenceGoals);
            tvPoints = (TextView) view.findViewById(R.id.tvPoints);
        }
    }

    static class HeaderLeagueTableViewHolder extends RecyclerView.ViewHolder {

        public TextView tvGroupName;

        public HeaderLeagueTableViewHolder(View view) {
            super(view);
            tvGroupName = (TextView) view.findViewById(R.id.tvGroupName);

        }
    }
}
