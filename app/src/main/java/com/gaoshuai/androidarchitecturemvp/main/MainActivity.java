package com.gaoshuai.androidarchitecturemvp.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.gaoshuai.androidarchitecturemvp.R;
import com.gaoshuai.androidarchitecturemvp.base.BaseActivity;
import com.gaoshuai.androidarchitecturemvp.bean.CookBean;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent;
import com.jakewharton.rxbinding3.widget.TextViewEditorActionEvent;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.edit_query)
    EditText editQuery;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private MainContract.Presenter mainPresenter;
    private CooksAdapter cooksAdapter;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void init() {
        super.init();
        // 构造  presenter 管理器
        new MainPresenter(this, this);
        // 进入首次获取数据
        mainPresenter.initData();
        // 设置下拉刷新监听
        refreshLayout.setOnRefreshListener(this);

        Glide.with(this).load("").into(new ImageView(this));
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
        this.mainPresenter = presenter;
    }

    @Override
    public void setRefreshFinish() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setCooksData(List<CookBean> cooksData, boolean isAdd) {
        if (cooksAdapter != null) {
            if (isAdd)
                cooksAdapter.addData(cooksData);
            else
                cooksAdapter.setNewData(cooksData);
        }
    }

    @Override
    public void initSearchBar() {
        RxTextView.textChangeEvents(editQuery)
                .skip(1)
                .debounce(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        LogUtils.e(MainActivity.class.getName(), textViewTextChangeEvent.getText());
                        mainPresenter.refreshData(textViewTextChangeEvent.getText().toString());
                    }
                });
    }

    @Override
    public void initRecyclerView() {
        if (cooksAdapter == null)
            cooksAdapter = new CooksAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cooksAdapter);
    }

    /**
     * 下来刷新
     */
    @Override
    public void onRefresh() {
        mainPresenter.refreshData(editQuery.getText().toString());
    }
}
