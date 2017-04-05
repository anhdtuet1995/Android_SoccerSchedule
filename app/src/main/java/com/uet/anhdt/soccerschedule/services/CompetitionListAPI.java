package com.uet.anhdt.soccerschedule.services;

import com.uet.anhdt.soccerschedule.models.competition.CompetitionInfomation;
import com.uet.anhdt.soccerschedule.utils.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anhdt on 4/2/2017.
 */

public interface CompetitionListAPI {

    @GET(Constant.API_COMPETITION)
    Call<ArrayList<CompetitionInfomation>> getCompetitionInfo(
            @Query("season") int season
    );

}
