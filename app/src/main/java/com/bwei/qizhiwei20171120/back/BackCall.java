package com.bwei.qizhiwei20171120.back;

/**
 * author:Created by WangZhiQiang on 2017/11/20.
 */

public interface BackCall {
    void onSuccess(Object o);
    void  onfailed(Exception e);
}
