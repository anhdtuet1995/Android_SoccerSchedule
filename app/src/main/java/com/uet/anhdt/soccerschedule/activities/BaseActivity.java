package com.uet.anhdt.soccerschedule.activities;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by anhdt on 4/1/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    public final String TAG = this.getClass().getSimpleName();

    //Log if have tag
    public void Log(String tag, String content) {
        Log.v(tag, content);
    }

    //Log if not have tag
    public void Log(String content) {
        Log.v(TAG, content);
    }

}


