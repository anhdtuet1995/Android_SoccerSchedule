package com.uet.anhdt.soccerschedule.application;

import android.app.Application;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.squareup.leakcanary.LeakCanary;
import com.uet.anhdt.soccerschedule.R;
import com.uet.anhdt.soccerschedule.views.SvgDecoder;

import java.io.InputStream;

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
