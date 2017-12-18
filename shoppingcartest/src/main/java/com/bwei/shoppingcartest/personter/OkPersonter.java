package com.bwei.shoppingcartest.personter;

import com.bwei.shoppingcartest.model.IModel;
import com.bwei.shoppingcartest.model.OkhttpUtils;

import java.util.Map;

/**
 * author:Created by QiZhiWei on 2017/12/8.
 */

public class OkPersonter {

    public OkPersonter() {
    }
    public void getData(String path, Class cls, final IView iView){

        OkhttpUtils.getInstance().get(path, cls, new IModel() {
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
    public void postData(String path, Map<String ,String >map, Class cls, final IView iView){

        OkhttpUtils.getInstance().post(path, map, cls, new IModel() {
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
