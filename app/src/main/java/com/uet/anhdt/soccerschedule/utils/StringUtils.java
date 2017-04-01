package com.uet.anhdt.soccerschedule.utils;

/**
 * Created by anhdt on 4/1/2017.
 */

public class StringUtils {

    public static String summarizeSoccerTeamName(String name) {
        String sName = name.substring(0, 3);
        sName = sName.trim();
        if (sName.length() == 2) {
            sName += name.charAt(3);
        }
        else if (sName.length() == 3 && sName.charAt(1) == ' ') {
            sName = name.substring(0,1) + name.substring(2,4);
        }
        return sName.toUpperCase();
    }

}
