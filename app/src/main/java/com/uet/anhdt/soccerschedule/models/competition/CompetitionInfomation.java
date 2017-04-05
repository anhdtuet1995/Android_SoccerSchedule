package com.uet.anhdt.soccerschedule.models.competition;

/**
 * Created by anhdt on 4/2/2017.
 */

public class CompetitionInfomation {

    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String caption;

    public String getCaption() { return this.caption; }

    public void setCaption(String caption) { this.caption = caption; }

    private String league;

    public String getLeague() { return this.league; }

    public void setLeague(String league) { this.league = league; }

    private String year;

    public String getYear() { return this.year; }

    public void setYear(String year) { this.year = year; }

    private int currentMatchday;

    public int getCurrentMatchday() { return this.currentMatchday; }

    public void setCurrentMatchday(int currentMatchday) { this.currentMatchday = currentMatchday; }

    private int numberOfMatchdays;

    public int getNumberOfMatchdays() { return this.numberOfMatchdays; }

    public void setNumberOfMatchdays(int numberOfMatchdays) { this.numberOfMatchdays = numberOfMatchdays; }

    private int numberOfTeams;

    public int getNumberOfTeams() { return this.numberOfTeams; }

    public void setNumberOfTeams(int numberOfTeams) { this.numberOfTeams = numberOfTeams; }

    private int numberOfGames;

    public int getNumberOfGames() { return this.numberOfGames; }

    public void setNumberOfGames(int numberOfGames) { this.numberOfGames = numberOfGames; }

}
