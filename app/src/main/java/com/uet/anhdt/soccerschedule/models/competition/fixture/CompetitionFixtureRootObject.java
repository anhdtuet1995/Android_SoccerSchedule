package com.uet.anhdt.soccerschedule.models.competition.fixture;

import java.util.ArrayList;

/**
 * Created by anhdt on 4/6/2017.
 */

public class CompetitionFixtureRootObject {

    private int count;

    public int getCount() { return this.count; }

    public void setCount(int count) { this.count = count; }

    private ArrayList<CompetitionFixture> fixtures;

    public ArrayList<CompetitionFixture> getFixtures() { return this.fixtures; }

    public void setFixtures(ArrayList<CompetitionFixture> fixtures) { this.fixtures = fixtures; }

}
