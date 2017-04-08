package com.uet.anhdt.soccerschedule.models.championleague;

/**
 * Created by anhdt on 4/8/2017.
 */

public class ChampionLeagueRootObject {

    private String leagueCaption;

    public String getLeagueCaption() { return this.leagueCaption; }

    public void setLeagueCaption(String leagueCaption) { this.leagueCaption = leagueCaption; }

    private int matchday;

    public int getMatchday() { return this.matchday; }

    public void setMatchday(int matchday) { this.matchday = matchday; }

    private ChampionLeagueStanding standings;

    public ChampionLeagueStanding getStandings() { return this.standings; }

    public void setStandings(ChampionLeagueStanding standings) { this.standings = standings; }

}
