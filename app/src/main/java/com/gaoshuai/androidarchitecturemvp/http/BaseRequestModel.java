package com.gaoshuai.androidarchitecturemvp.http;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by gaoshuai on 2019/3/23.
 * Describe：同一请求接口类  基类
 */
public class BaseRequestModel {
    public <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
