package com.gaoshuai.business.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    protected BasePresenterInterface presenter;
    private static List<Activity> activityList = new ArrayList<>();
    protected int page = 0;
    protected int size = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateView(savedInstanceState);
        ButterKnife.bind(this);
        activityList.add(this);
        init();
    }

    private static void finishAllActivity() {
        if (activityList == null || activityList.size() <= 0)
            return;
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 设置activity view
     *
     * @param savedInstanceState
     */
    protected void onCreateView(Bundle savedInstanceState) {

    }

    /**
     * 初始化设置一些信息
     */
    protected void init() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
