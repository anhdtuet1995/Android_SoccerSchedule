package com.uet.anhdt.soccerschedule.models.competition.fixture;

import java.util.Date;

/**
 * Created by anhdt on 4/6/2017.
 */

public class CompetitionFixture {

    private String date;

    public String getDate() { return this.date; }

    public void setDate(String date) { this.date = date; }

    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

    private int matchday;

    public int getMatchday() { return this.matchday; }

    public void setMatchday(int matchday) { this.matchday = matchday; }

    private String homeTeamName;

    public String getHomeTeamName() { return this.homeTeamName; }

    public void setHomeTeamName(String homeTeamName) { this.homeTeamName = homeTeamName; }

    private String awayTeamName;

    public String getAwayTeamName() { return this.awayTeamName; }

    public void setAwayTeamName(String awayTeamName) { this.awayTeamName = awayTeamName; }

    private CompetitionResult result;

    public CompetitionResult getResult() { return this.result; }

    public void setResult(CompetitionResult result) { this.result = result; }

}
