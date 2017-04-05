package com.uet.anhdt.soccerschedule.models.fixture;

/**
 * Created by anhdt on 4/2/2017.
 */

public class FixtureLinks {

    private FixtureCompetition competition;

    public FixtureCompetition getCompetition() { return this.competition; }

    public void setCompetition(FixtureCompetition competition) { this.competition = competition; }

    private FixtureHome homeTeam;

    private FixtureAway awayTeam;

    public FixtureHome getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(FixtureHome homeTeam) {
        this.homeTeam = homeTeam;
    }

    public FixtureAway getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(FixtureAway awayTeam) {
        this.awayTeam = awayTeam;
    }
}
