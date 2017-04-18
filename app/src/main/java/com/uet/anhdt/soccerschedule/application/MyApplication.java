package com.uet.anhdt.soccerschedule.application;

import android.app.Application;

/**
 * Created by anhdt on 4/3/2017.
 */

public class MyApplication extends Application {

    private static MyApplication _instance;

    public MyApplication() {

    }

    public static MyApplication getInstance() {
        if (null == _instance) {
            _instance = new MyApplication();
        }
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
