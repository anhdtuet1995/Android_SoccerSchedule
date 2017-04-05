package com.uet.anhdt.soccerschedule.utils;

/**
 * Created by anhdt on 4/1/2017.
 */

public class Constant {

    //api link
    public static final String API_BASE_URL = "http://api.football-data.org/v1/";

    public static final String API_COMPETITION = "competitions/";
    public static final String API_FIXTURE_TODAY = "fixtures/";
    public static final String API_TEAM = "teams/{id}";


    //season select
    public static final int API_SEASON_USAGE = 2016;
    public static final String API_DEFAULT_TIME_FRAME = "n1";
    //auth token for api football
    public static final String AUTH_TOKEN_API_KEY = "X-Auth-Token";
    public static final String AUTH_TOKEN_API_VALUE = "554999b5eb5847ddb35132b22da2a0a6";

    //main title tablayout
    public static final String NEWS_MAIN = "Lịch thi đấu";
    public static final String FAVORITE_MAIN = "Đội yêu thích";
    public static final String COMPETITION_MAIN = "Danh sách giải đấu";
}
