package com.gaoshuai.business.main;

import android.content.Context;

import com.gaoshuai.business.R;
import com.gaoshuai.business.base.basequickadapter.BaseQuickAdapter;
import com.gaoshuai.business.base.basequickadapter.BaseViewHolder;
import com.gaoshuai.business.bean.CookBean;

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
