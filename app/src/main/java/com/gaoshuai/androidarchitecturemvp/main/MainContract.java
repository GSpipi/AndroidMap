package com.gaoshuai.androidarchitecturemvp.main;

import com.gaoshuai.androidarchitecturemvp.base.BasePresenterInterface;
import com.gaoshuai.androidarchitecturemvp.base.BaseViewInterface;
import com.gaoshuai.androidarchitecturemvp.bean.CookBean;

import java.util.List;

/**
 * Created by gaoshuai on 2019/3/20.
 * Describe：
 */
public interface MainContract {
    interface View extends BaseViewInterface<Presenter> {
        void setRefreshFinish();

        void setCooksData(List<CookBean> cooksData,boolean isAdd);

        void initSearchBar();

        void initRecyclerView();
    }

    interface Presenter extends BasePresenterInterface {

        void initData();

        void refreshData(String searchStr);

        void loadMore(String searchStr);

    }
}
