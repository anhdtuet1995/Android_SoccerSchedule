package com.uet.anhdt.soccerschedule.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.models.competition.CompetitionInfomation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhdt on 4/2/2017.
 */

public class CompetitionListAdapter extends RecyclerView.Adapter<CompetitionListAdapter.CompetitionViewHolder> {

    private ArrayList<CompetitionInfomation> listCompetition;
    private Context context;

    public CompetitionListAdapter(Context context, ArrayList<CompetitionInfomation> listCompetition) {
        this.listCompetition = listCompetition;
        this.context = context;
    }

    @Override
    public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item_competition, parent, false);
        return new CompetitionViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CompetitionViewHolder holder, int position) {
        CompetitionInfomation competition = listCompetition.get(position);
        holder.imvItemCompetitionName.setText(competition.getCaption());
    }

    @Override
    public int getItemCount() {
        return listCompetition.size();
    }

    class CompetitionViewHolder extends RecyclerView.ViewHolder{

        TextView imvItemCompetitionName;

        public CompetitionViewHolder(View itemView) {
            super(itemView);
            imvItemCompetitionName = (TextView) itemView.findViewById(R.id.imvItemCompetitionName);
        }
    }

}
