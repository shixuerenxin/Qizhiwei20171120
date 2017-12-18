package com.bwei.qizhiwei20171120.Presenter;

import com.bwei.qizhiwei20171120.back.BackCall;
import com.bwei.qizhiwei20171120.back.IView;
import com.bwei.qizhiwei20171120.util.OkhttpUtils;

import java.util.Map;

/**
 * author:Created by WangZhiQiang on 2017/11/20.
 */

public class PVeiw {
    private IView iView;

    public PVeiw() {
    }
    public void gethttp(String path, Map<String ,String > map, Class cls){
        OkhttpUtils.get(path, map, cls, new BackCall() {
            @Override
            public void onSuccess(Object o) {
                iView.success(o);
            }

            @Override
            public void onfailed(Exception e) {
                iView.failed(e);
            }
        });


    }

    public void destroy(){
        if (iView!=null){
            iView=null;
        }
    }

}