package com.uet.anhdt.soccerschedule.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by anhdt on 4/2/2017.
 */

public class BaseFragment extends Fragment {

    public final String TAG = this.getClass().getSimpleName();

    //Log if have tag
    public void Log(String tag, String content) {
        Log.v(tag, content);
    }

    //Log if not have tag
    public void Log(String content) {
        Log.v(TAG, content);
    }

    public int dp2px(float dp) {
        // Get the screen's density scale
        final float density = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * density + 0.5f);
    }

}
