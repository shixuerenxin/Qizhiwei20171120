package com.bwei.shoppingcartest.model;

import android.os.Handler;

import com.bwei.shoppingcartest.utils.GsonUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Created by QiZhiWei on 2017/12/8.
 */

public class OkhttpUtils {

    private static Handler handler=new Handler();
    private static volatile OkhttpUtils instance;
    public OkhttpUtils(){}
    public static OkhttpUtils getInstance(){
        if (instance==null){
            synchronized (OkhttpUtils.class){
                if (null==instance){
                    instance=new OkhttpUtils();
                }
            }
        }
        return instance;
    }
    public void get(String path, final Class cls, final IModel iModel){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request build = new Request.Builder()
                .get()
                .url(path)
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iModel.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                handler.post(new Runnable() {
                    Object o = GsonUtil.getInstance().fromJson(string, cls);
                    @Override
                    public void run() {
                        iModel.onSuccess(o);
                    }
                });

            }
        });
    }
    public void post(String path, Map<String,String> map, final Class cls, final IModel iModel){
        FormBody.Builder formBody=new FormBody.Builder();
        for (Map.Entry<String,String> entry: map.entrySet()) {
            formBody.add(entry.getKey(),entry.getValue());
        }
        OkHttpClient builder = new OkHttpClient();
        Request build = new Request.Builder()
                .url(path)
                .post(formBody.build())
                .build();
        Call call = builder.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iModel.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    Object o = GsonUtil.getInstance().fromJson(string, cls);
                    @Override
                    public void run() {
                        iModel.onSuccess(o);
                    }
                });

            }
        });


    }



}
