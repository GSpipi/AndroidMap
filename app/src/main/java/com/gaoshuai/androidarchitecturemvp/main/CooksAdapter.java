package com.gaoshuai.androidarchitecturemvp.main;

import android.content.Context;
import android.support.annotation.Nullable;

import com.gaoshuai.androidarchitecturemvp.R;
import com.gaoshuai.androidarchitecturemvp.base.basequickadapter.BaseQuickAdapter;
import com.gaoshuai.androidarchitecturemvp.base.basequickadapter.BaseViewHolder;
import com.gaoshuai.androidarchitecturemvp.bean.CookBean;

import java.util.List;

/**
 * Created by gaoshuai on 2019/3/25.
 * Describe：菜单列表
 */
public class CooksAdapter extends BaseQuickAdapter<CookBean, BaseViewHolder> {

    public CooksAdapter(Context context) {
        super( context,R.layout.item_cook);
    }

    @Override
    protected void convert(BaseViewHolder helper, CookBean item) {

    }

}
