package com.bwei.qizhiwei20171211.personter;

import com.bwei.qizhiwei20171211.model.IBack;
import com.bwei.qizhiwei20171211.model.OkHttpUtils;

import java.util.Map;

/**
 * author:Created by QiZhiWei on 2017/12/11.
 */

public class OkPersonter {

    public OkPersonter() {

    }
    private static volatile OkHttpUtils instance;
    public OkHttpUtils getInstance(){
        if (instance==null){
            synchronized (OkHttpUtils.class){
                if (null==instance){
                    instance=new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    public void getData(String path, Class cls, final IView iView){
        getInstance().get(path, cls, new IBack() {
            @Override
            public void onSuccess(Object o) {
                iView.success(o);
            }

            @Override
            public void onFailed(Exception e) {
                iView.failed(e);
            }
        });


    }
    public void postData(String path, Map<String,String> map, Class cls, final IView iView){
        getInstance().post(path, map, cls, new IBack() {
            @Override
            public void onSuccess(Object o) {
                iView.success(o);
            }

            @Override
            public void onFailed(Exception e) {
                iView.failed(e);
            }
        });

    }

}
