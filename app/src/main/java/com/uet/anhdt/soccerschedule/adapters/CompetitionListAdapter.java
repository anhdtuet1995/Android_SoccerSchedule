package com.uet.anhdt.soccerschedule.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.activities.CompetitionActivity;
import com.uet.anhdt.soccerschedule.models.competition.CompetitionInfomation;
import com.uet.anhdt.soccerschedule.utils.CompetitionUtil;
import com.uet.anhdt.soccerschedule.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhdt on 4/2/2017.
 */

public class CompetitionListAdapter extends RecyclerView.Adapter<CompetitionListAdapter.CompetitionViewHolder> {

    private ArrayList<CompetitionInfomation> listCompetition;
    private Context context;
    private CompetitionClick mOnClickListener;

    public CompetitionListAdapter(Context context, ArrayList<CompetitionInfomation> listCompetition) {
        this.listCompetition = listCompetition;
        this.context = context;
    }

    interface CompetitionClick extends View.OnClickListener {
        @Override
        void onClick(View v);
    }

    @Override
    public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item_competition, parent, false);
        itemview.setOnClickListener(mOnClickListener);
        return new CompetitionViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CompetitionViewHolder holder, int position) {
        CompetitionInfomation competition = listCompetition.get(position);
        holder.imvItemCompetitionName.setText(competition.getCaption());
        holder.imvItemCompetitionBall.setImageResource(CompetitionUtil.getResource(competition.getLeague()));
    }

    @Override
    public int getItemCount() {
        return listCompetition.size();
    }

    class CompetitionViewHolder extends RecyclerView.ViewHolder implements CompetitionClick{

        TextView imvItemCompetitionName;
        ImageView imvItemCompetitionBall;
        public CompetitionViewHolder(View itemView) {
            super(itemView);
            imvItemCompetitionName = (TextView) itemView.findViewById(R.id.imvItemCompetitionName);
            imvItemCompetitionBall = (ImageView) itemView.findViewById(R.id.imvItemCompetitionBall);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CompetitionInfomation competitionInfomation = listCompetition.get(getAdapterPosition());
            Intent intent = new Intent(context, CompetitionActivity.class);
            intent.putExtra(Constant.MAIN_COMPETITION_ID, competitionInfomation.getId());
            intent.putExtra(Constant.MAIN_COMPETITION_NAME, competitionInfomation.getCaption());
            intent.putExtra(Constant.MAIN_COMPETITION_CURRENT, competitionInfomation.getCurrentMatchday());
            intent.putExtra(Constant.MAIN_COMPETITION_NUMBER_MATCH, competitionInfomation.getNumberOfMatchdays());
            context.startActivity(intent);
        }
    }

}
