package com.uet.anhdt.soccerschedule.models.championleague;

/**
 * Created by anhdt on 4/8/2017.
 */

public class ChampionLeagueGroup {

    private String group;

    public String getGroup() { return this.group; }

    public void setGroup(String group) { this.group = group; }

    private int rank;

    public int getRank() { return this.rank; }

    public void setRank(int rank) { this.rank = rank; }

    private String team;

    public String getTeam() { return this.team; }

    public void setTeam(String team) { this.team = team; }

    private int teamId;

    public int getTeamId() { return this.teamId; }

    public void setTeamId(int teamId) { this.teamId = teamId; }

    private int playedGames;

    public int getPlayedGames() { return this.playedGames; }

    public void setPlayedGames(int playedGames) { this.playedGames = playedGames; }

    private String crestURI;

    public String getCrestURI() { return this.crestURI; }

    public void setCrestURI(String crestURI) { this.crestURI = crestURI; }

    private int points;

    public int getPoints() { return this.points; }

    public void setPoints(int points) { this.points = points; }

    private int goals;

    public int getGoals() { return this.goals; }

    public void setGoals(int goals) { this.goals = goals; }

    private int goalsAgainst;

    public int getGoalsAgainst() { return this.goalsAgainst; }

    public void setGoalsAgainst(int goalsAgainst) { this.goalsAgainst = goalsAgainst; }

    private int goalDifference;

    public int getGoalDifference() { return this.goalDifference; }

    public void setGoalDifference(int goalDifference) { this.goalDifference = goalDifference; }

}
