package com.uet.anhdt.soccerschedule.utils;

/**
 * Created by anhdt on 4/6/2017.
 */

public enum StatusFixture {

    FINISHED, IN_PLAY, TIMED, FT, SCHEDULED;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0:
                return "FINISHED";
            case 1:
                return "IN_PLAY";
            case 2:
                return "TIMED";
            case 3:
                return "FT";
            case 4:
                return "SCHEDULED";
        }
        return super.toString();
    }
}
