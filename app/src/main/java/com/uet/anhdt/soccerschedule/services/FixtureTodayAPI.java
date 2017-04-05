package com.uet.anhdt.soccerschedule.services;

import com.uet.anhdt.soccerschedule.models.fixture.FixtureRootObject;
import com.uet.anhdt.soccerschedule.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anhdt on 4/2/2017.
 */

public interface FixtureTodayAPI {

    @GET(Constant.API_FIXTURE_TODAY)
    Call<FixtureRootObject> getFixtureToday(
            @Query("season") int season,
            @Query("timeFrame") String timeFrame

    );

}
