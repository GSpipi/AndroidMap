package com.gaoshuai.androidarchitecturemvp.http;

import android.content.Context;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by gaoshuai on 2019/3/22.
 * Describe：
 */
public class CustomSubscriber<T> implements Observer<T> {
    private Context context;
    private Disposable subscription;

    public CustomSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Throwable throwable) {
        try {
            if (throwable instanceof CustomException) {
                CustomException exception = (CustomException) throwable;
                unifiedHandling(exception);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        subscription = d;

    }

    @Override
    public void onNext(T bean) {

    }

    public Disposable getSubscription() {
        return subscription;
    }

    /**
     * 错误码统一拦截处理
     *
     * @param exception
     */
    private void unifiedHandling(CustomException exception) {
        if (exception == null || exception.getErrCode() == 0) {
            Toast.makeText(context, "错误码为空！！！", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(context, "错误码：" + exception.getErrCode() + "---错误信息：" + exception.getErrMsg(), Toast.LENGTH_SHORT).show();
    }
}
