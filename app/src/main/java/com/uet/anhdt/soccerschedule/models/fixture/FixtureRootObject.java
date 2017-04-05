package com.uet.anhdt.soccerschedule.models.fixture;

import java.util.ArrayList;

/**
 * Created by anhdt on 4/2/2017.
 */

public class FixtureRootObject {

    private String timeFrameStart;

    public String getTimeFrameStart() { return this.timeFrameStart; }

    public void setTimeFrameStart(String timeFrameStart) { this.timeFrameStart = timeFrameStart; }

    private String timeFrameEnd;

    public String getTimeFrameEnd() { return this.timeFrameEnd; }

    public void setTimeFrameEnd(String timeFrameEnd) { this.timeFrameEnd = timeFrameEnd; }

    private int count;

    public int getCount() { return this.count; }

    public void setCount(int count) { this.count = count; }

    private ArrayList<FixtureToday> fixtures;

    public ArrayList<FixtureToday> getFixtures() { return this.fixtures; }

    public void setFixtures(ArrayList<FixtureToday> fixtures) { this.fixtures = fixtures; }

}
