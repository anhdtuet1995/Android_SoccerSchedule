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
    public static final String API_COMPETITION_FIXTURE = "competitions/{id}/fixtures";
    public static final String API_COMPETITION_TABLE = "competitions/{id}/leagueTable";


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

    //competition tablayout
    public static final String COMPETITION_FIXTURE = "Lịch thi đấu";
    public static final String COMPETITION_RESULT = "Kết quả";
    public static final String COMPETION_TABLE = "Bảng xếp hạng";

    //Main -> Competition
    public static final String MAIN_COMPETITION_NAME = "main_competition_name";
    public static final String MAIN_COMPETITION_ID = "main_competition_id";
    public static final String MAIN_COMPETITION_CURRENT = "main_competition_current";
    public static final String MAIN_COMPETITION_NUMBER_MATCH = "main_competition_number_match";
}
