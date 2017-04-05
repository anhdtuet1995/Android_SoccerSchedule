package com.uet.anhdt.soccerschedule.services;

import com.uet.anhdt.soccerschedule.models.team.Team;
import com.uet.anhdt.soccerschedule.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by anhdt on 4/3/2017.
 */

public interface TeamAPI {

    @GET(Constant.API_TEAM)
    Call<Team> getTeam(
            @Path("id") int id
    );
}
