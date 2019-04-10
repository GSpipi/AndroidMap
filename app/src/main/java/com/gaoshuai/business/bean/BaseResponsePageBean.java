package com.gaoshuai.business.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaoshuai on 2019/3/22.
 * Describe：分页 列表
 */
public class BaseResponsePageBean<T> implements Serializable {

    public String totalNum;
    public String pn;
    public String rn;
    public List<T> data;
}
