package com.gaoshuai.androidarchitecturemvp.base;

/**
 * Created by gaoshuai on 2019/3/20.
 * Describe：view base 接口
 */
public interface BaseViewInterface<T extends BasePresenterInterface> {
    void setPresenter(T presenter);
}
