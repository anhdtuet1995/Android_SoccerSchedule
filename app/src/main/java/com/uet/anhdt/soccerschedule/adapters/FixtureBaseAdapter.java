package com.uet.anhdt.soccerschedule.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
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

public abstract class FixtureBaseAdapter<T> extends BaseExpandableListAdapter {

    private static final String TAG = "FixtureAdapter";
    protected final String FT = "FT";
    protected final String LIVE = "LIVE";

    protected Context mContext;
    private List<String> nameOfCompetition;
    private HashMap<String, List<T>> dataOfMatch;

    public FixtureBaseAdapter(Context context, List<String> nameOfCompetition, HashMap<String, List<T>> dataOfMatch) {
        this.mContext = context;
        this.nameOfCompetition = nameOfCompetition;
        this.dataOfMatch = dataOfMatch;
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
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(mContext);
            convertView = li.inflate(R.layout.group_item_match, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvHeader = (TextView) convertView.findViewById(R.id.tvGroupMatchName);
            convertView.setTag(groupViewHolder);
        }
        else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        ExpandableListView mExpand = (ExpandableListView) parent;
        mExpand.expandGroup(groupPosition);

        groupViewHolder.tvHeader.setText(nameOfCompetition.get(groupPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
        TextView tvHeader;
    }

    static class ChildViewHolder {
        RelativeLayout relativeItemHome;
        TextView tvItemHomeName;
        RelativeLayout relativeItemAway;
        TextView tvItemHomeAway;
        RelativeLayout relativeItemTime;
        TextView tvItemDate;
        TextView tvItemTime;
    }

}
