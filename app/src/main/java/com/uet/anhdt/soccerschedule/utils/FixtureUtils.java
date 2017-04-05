package com.uet.anhdt.soccerschedule.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anhdt on 4/3/2017.
 */

public class FixtureUtils {

    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String getNameOfCompetition(String url) {
        int id = getIdFromHref(url);
        switch (id) {
            case 426:
                return "Premier League 2016/17";
            case 427:
                return "Championship 2016/17";
            case 428:
                return "League One 2016/17";
            case 430:
                return "1. Bundesliga 2016/17";
            case 431:
                return "2. Bundesliga 2016/17";
            case 432:
                return "DFB-Pokal 2016/17";
            case 433:
                return "Eredivisie 2016/17";
            case 434:
                return "Ligue 1 2016/17";
            case 435:
                return "Ligue 2 2016/17";
            case 436:
                return "Primera Division 2016/17";
            case 437:
                return "Liga Adelante 2016/17";
            case 438:
                return "Serie A 2016/17";
            case 439:
                return "Primeira Liga 2016/17";
            case 440:
                return "Champions League 2016/17";
            default:
                return "Other";
        }
    }

    public static int getIdFromHref(String url) {
        int i = url.lastIndexOf("/");
        String temp = url.substring(i+1, url.length());
        int id = Integer.parseInt(temp);
        return id;
    }

    public static Date getDate(String text) {
        try {

            Date date = df.parse(text);
            System.out.println(date);
            System.out.println(df.format(date));
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
