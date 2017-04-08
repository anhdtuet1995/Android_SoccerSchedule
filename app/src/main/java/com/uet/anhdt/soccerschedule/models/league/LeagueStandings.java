package com.uet.anhdt.soccerschedule.models.league;

/**
 * Created by anhdt on 4/8/2017.
 */

public class LeagueStandings {

    private int position;

    public int getPosition() { return this.position; }

    public void setPosition(int position) { this.position = position; }

    private String teamName;

    public String getTeamName() { return this.teamName; }

    public void setTeamName(String teamName) { this.teamName = teamName; }

    private String crestURI;

    public String getCrestURI() { return this.crestURI; }

    public void setCrestURI(String crestURI) { this.crestURI = crestURI; }

    private int playedGames;

    public int getPlayedGames() { return this.playedGames; }

    public void setPlayedGames(int playedGames) { this.playedGames = playedGames; }

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

    private int wins;

    public int getWins() { return this.wins; }

    public void setWins(int wins) { this.wins = wins; }

    private int draws;

    public int getDraws() { return this.draws; }

    public void setDraws(int draws) { this.draws = draws; }

    private int losses;

    public int getLosses() { return this.losses; }

    public void setLosses(int losses) { this.losses = losses; }

}
