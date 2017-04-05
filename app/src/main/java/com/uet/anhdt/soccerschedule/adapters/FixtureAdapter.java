package com.uet.anhdt.soccerschedule.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.models.fixture.FixtureRootObject;
import com.uet.anhdt.soccerschedule.models.fixture.FixtureToday;
import com.uet.anhdt.soccerschedule.models.team.Team;
import com.uet.anhdt.soccerschedule.services.InitService;
import com.uet.anhdt.soccerschedule.services.TeamAPI;
import com.uet.anhdt.soccerschedule.utils.FixtureUtils;
import com.uet.anhdt.soccerschedule.utils.StatusFixture;
import com.uet.anhdt.soccerschedule.views.SvgDecoder;
import com.uet.anhdt.soccerschedule.views.SvgDrawableTranscoder;
import com.uet.anhdt.soccerschedule.views.SvgSoftwareLayerSetter;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anhdt on 4/1/2017.
 */

public class FixtureAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "FixtureAdapter";
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;
    private final String FT = "FT";
    private final String LIVE = "LIVE";

    private Context mContext;
    private List<String> nameOfCompetition;
    private HashMap<String, List<FixtureToday>> dataOfMatch;

    public FixtureAdapter(Context context, List<String> nameOfCompetition, HashMap<String, List<FixtureToday>> dataOfMatch) {
        this.mContext = context;
        this.nameOfCompetition = nameOfCompetition;
        this.dataOfMatch = dataOfMatch;

//        requestBuilder = Glide.with(mContext)
//                .using(Glide.buildStreamModelLoader(Uri.class, mContext), InputStream.class)
//                .from(Uri.class)
//                .as(SVG.class)
//                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
//                .sourceEncoder(new StreamEncoder())
//                .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
//                .decoder(new SvgDecoder())
//                .error(R.mipmap.ic_ball)
//                .animate(android.R.anim.fade_in)
//                .listener(new SvgSoftwareLayerSetter<Uri>());
    }


    @Override
    public int getGroupCount() {
        return nameOfCompetition.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataOfMatch.get(nameOfCompetition.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return nameOfCompetition.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataOfMatch.get(nameOfCompetition.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(mContext);
            convertView = li.inflate(R.layout.group_item_match, parent, false);
        }

        TextView tvHeader = (TextView) convertView.findViewById(R.id.tvGroupMatchName);
        tvHeader.setText(nameOfCompetition.get(groupPosition));
        return convertView;
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
        if (fixtureToday.getStatus().equals(StatusFixture.FINISHED.toString())) {
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

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
