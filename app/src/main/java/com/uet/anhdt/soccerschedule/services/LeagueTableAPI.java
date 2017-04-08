package com.uet.anhdt.soccerschedule.services;

import com.uet.anhdt.soccerschedule.models.championleague.ChampionLeagueRootObject;
import com.uet.anhdt.soccerschedule.models.league.LeagueRootObject;
import com.uet.anhdt.soccerschedule.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anhdt on 4/8/2017.
 */

public interface LeagueTableAPI {

    //normal league table
    @GET(Constant.API_COMPETITION_TABLE)
    Call<LeagueRootObject> getLeagueTable(
            @Path("id") int id,
            @Query("season") int season
    );

    //champion league table
    @GET(Constant.API_COMPETITION_TABLE)
    Call<ChampionLeagueRootObject> getChampionLeagueTable(
            @Path("id") int id,
            @Query("season") int season
    );
}
