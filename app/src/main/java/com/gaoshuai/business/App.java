package com.gaoshuai.business;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

/**
 * Created by gaoshuai on 2019/3/20.
 * Describe：
 */
public class App extends Application {
    private static App mInstance;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
        init();
    }

    private void init() {
        Utils.init(this);
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public static synchronized Context getContext() {
        return mContext;
    }
}
