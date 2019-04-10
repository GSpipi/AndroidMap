package com.gaoshuai.androidarchitecturemvp.base;

/**
 * Created by gaoshuai on 2019/3/20.
 * Describe：presenter base 接口
 */
public interface BasePresenterInterface {
    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
