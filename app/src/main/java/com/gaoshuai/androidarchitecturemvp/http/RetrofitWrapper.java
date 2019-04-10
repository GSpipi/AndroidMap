package com.gaoshuai.androidarchitecturemvp.http;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaoshuai on 2019/3/20.
 * Describe：构建 retrofit
 */
public class RetrofitWrapper {
    private static final int CONNECT_TIMEOUT = 10;
    private static Retrofit retrofit;

    public RetrofitWrapper(String baseUrl) {
        // 手动构建一个  OkHttpClient 对象
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        // 设置超时时间
        okHttpClientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        // https 验证

        // 添加拦截器  获取header 内容

        // 构建 retrofit
        retrofit = new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitWrapper getInstance(String baseUrl) {
        RetrofitWrapper retrofitWrapper;
        synchronized (RetrofitWrapper.class) {
            retrofitWrapper = new RetrofitWrapper(baseUrl);
        }
        return retrofitWrapper;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
