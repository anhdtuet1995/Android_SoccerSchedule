package com.uet.anhdt.soccerschedule.models.league;

import java.util.ArrayList;

/**
 * Created by anhdt on 4/8/2017.
 */

public class LeagueRootObject {

    private String leagueCaption;

    public String getLeagueCaption() { return this.leagueCaption; }

    public void setLeagueCaption(String leagueCaption) { this.leagueCaption = leagueCaption; }

    private int matchday;

    public int getMatchday() { return this.matchday; }

    public void setMatchday(int matchday) { this.matchday = matchday; }

    private ArrayList<LeagueStandings> standing;

    public ArrayList<LeagueStandings> getStanding() { return this.standing; }

    public void setStanding(ArrayList<LeagueStandings> standing) { this.standing = standing; }

}
