package com.uet.anhdt.soccerschedule.services;

import com.uet.anhdt.soccerschedule.models.competition.fixture.CompetitionFixtureRootObject;
import com.uet.anhdt.soccerschedule.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anhdt on 4/6/2017.
 */

public interface CompetitionFixtureAPI {

    @GET(Constant.API_COMPETITION_FIXTURE)
    Call<CompetitionFixtureRootObject> getCompetitionFixture(
            @Path("id") int id,
            @Query("season") int season
    );

}
