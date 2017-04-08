package com.uet.anhdt.soccerschedule.utils;

import com.uet.anhdt.soccerschedule.R;

/**
 * Created by anhdt on 4/6/2017.
 */

public class CompetitionUtil {

    private enum League {
        EC, PL, EL1, BL1,
        BL2, DFB, DED, FL1,
        FL2, PD, SD, SA,
        PPL, CL, ELC;


        @Override
        public String toString() {
            switch (this.ordinal()) {
                case 0:
                    return "EC";
                case 1:
                    return "PL";
                case 2:
                    return "EL1";
                case 3:
                    return "BL1";
                case 4:
                    return "BL2";
                case 5:
                    return "DFB";
                case 6:
                    return "DED";
                case 7:
                    return "FL1";
                case 8:
                    return "FL2";
                case 9:
                    return "PD";
                case 10:
                    return "SD";
                case 11:
                    return "SA";
                case 12:
                    return "PPL";
                case 13:
                    return "CL";
                case 14:
                    return "ELC";
            }
            return super.toString();
        }
    }

    public static int getResource(String league) {
        if (league.equals(League.PL.toString())) {
            return R.mipmap.premierleague;
        }
        else if (league.equals(League.CL.toString())) {
            return R.mipmap.champions;
        }
        else if (league.equals(League.BL1.toString())) {
            return R.mipmap.bundesliga;
        }
        else if (league.equals(League.PD.toString())) {
            return R.mipmap.laliga;
        }
        else if (league.equals(League.FL1.toString())) {
            return R.mipmap.ligue1;
        }
        else if (league.equals((League.SA.toString()))) {
            return R.mipmap.seria;
        }
        else if (league.equals((League.BL2.toString()))) {
            return R.mipmap.bl2;
        }
        else if (league.equals((League.DED.toString()))) {
            return R.mipmap.ded;
        }
        else if (league.equals((League.DFB.toString()))) {
            return R.mipmap.dfb;
        }
        else if (league.equals((League.EL1.toString()))) {
            return R.mipmap.el1;
        }
        else if (league.equals((League.ELC.toString()))) {
            return R.mipmap.elc;
        }
        else if (league.equals((League.FL2.toString()))) {
            return R.mipmap.fl2;
        }
        else if (league.equals((League.PPL.toString()))) {
            return R.mipmap.ppl;
        }
        else if (league.equals((League.SD.toString()))) {
            return R.mipmap.sd;
        }
        else {
            return R.mipmap.ic_ball;
        }
    }

}
