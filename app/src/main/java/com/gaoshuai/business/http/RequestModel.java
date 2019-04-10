package com.gaoshuai.business.http;

import com.gaoshuai.business.bean.BaseResponsePageBean;
import com.gaoshuai.business.bean.CookBean;
import com.gaoshuai.business.constant.HttpConstant;

import java.util.Map;

/**
 * Created by gaoshuai on 2019/3/20.
 * Describe：同一base地址 请求类
 */
public class RequestModel extends BaseRequestModel {
    private static RequestModel requestModel;
    private final RetrofitInterface retrofitService;

    public RequestModel() {
        retrofitService = RetrofitWrapper
                .getInstance(HttpConstant.BASE_URL)
                .create(RetrofitInterface.class);
    }

    public static RequestModel getInstance() {
        if (requestModel == null) {
            requestModel = new RequestModel();
        }
        return requestModel;
    }

    public void queryCooks(Map<String, String> map, CustomSubscriber<BaseResponsePageBean<CookBean>> subscriber) {
        subscribe(retrofitService.queryCooks(map)
                        .flatMap(new HttpResponseFunc<BaseResponsePageBean<CookBean>>())
                , subscriber);
    }
}
