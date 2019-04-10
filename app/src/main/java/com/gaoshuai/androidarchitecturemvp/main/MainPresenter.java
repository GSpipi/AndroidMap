package com.gaoshuai.androidarchitecturemvp.main;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.gaoshuai.androidarchitecturemvp.base.BasePresent;
import com.gaoshuai.androidarchitecturemvp.bean.BaseResponsePageBean;
import com.gaoshuai.androidarchitecturemvp.bean.CookBean;
import com.gaoshuai.androidarchitecturemvp.http.CustomSubscriber;
import com.gaoshuai.androidarchitecturemvp.http.RequestModel;

import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaoshuai on 2019/3/20.
 * Describe：
 */
public class MainPresenter extends BasePresent implements MainContract.Presenter {
    private MainContract.View view;
    private Context context;
    private Subscription subscription;

    public MainPresenter(Context context, MainContract.View view) {
        this.view = view;
        this.context = context;
        view.setPresenter(this);
    }

    @Override
    public void initData() {
        getData("", true, false);
    }

    @Override
    public void refreshData(String searchStr) {
        getData(searchStr, false, true);
    }

    @Override
    public void loadMore(String searchStr) {
        getData(searchStr, false, false);

    }

    private void getData(String searchStr, final boolean isDefaultGetData, final boolean isPull) {
        Map<String, String> map = new HashMap<>();
        map.put("key", "580a810a4747ac748f2e715b0d8ea8ca");
        if (!searchStr.isEmpty()) {
            map.put("menu", searchStr);
        }
        if (isDefaultGetData || isPull)
            map.put("pn", "0");
        RequestModel.getInstance().queryCooks(map, new CustomSubscriber<BaseResponsePageBean<CookBean>>(context) {
            @Override
            public void onSubscribe(Subscription s) {
                super.onSubscribe(s);
                subscription = s;
            }

            @Override
            public void onNext(BaseResponsePageBean<CookBean> bean) {
                super.onNext(bean);
                view.setRefreshFinish();
                if (bean != null && bean.data != null) {
                    if (isDefaultGetData) {
                        view.setCooksData(bean.data, false);
                    } else {
                        if (isPull) {
                            view.setCooksData(bean.data, false);
                        } else {
                            view.setCooksData(bean.data, true);
                        }
                    }
                } else {
                    ToastUtils.showShort("请求数据为空");
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
                view.setRefreshFinish();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null)
            subscription.cancel();
    }
}
