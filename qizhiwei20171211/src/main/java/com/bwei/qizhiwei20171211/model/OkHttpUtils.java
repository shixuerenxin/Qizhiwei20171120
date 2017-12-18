package com.bwei.qizhiwei20171211.model;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Created by QiZhiWei on 2017/12/11.
 */

public class OkHttpUtils {
    private Handler handler=new Handler();

        private IBack callBack;
        private static volatile OkHttpUtils instance;

        public OkHttpUtils() {
        }

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
        public void get(String path, final Class cls, final IBack callBack){
            OkHttpClient okHttpClient = new OkHttpClient();
            Request build = new Request.Builder()
                    .get()
                    .url(path)
                    .build();
            okhttp3.Call call = okHttpClient.newCall(build);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(e);
                        }
                    });
                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    Log.e("", "-------请求到的数据--------" + string);
                    final Gson gson = new Gson();
                    handler.post(new Runnable() {
                        Object o = gson.fromJson(string, cls);

                        @Override
                        public void run() {
                            callBack.onSuccess(o);
                        }
                    });
                }
            });
        }
        public void post(String path, Map<String,String> map, final Class cls, final IBack callBack){
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String,String> entry:map.entrySet()) {
                builder.add(entry.getKey(),entry.getValue());
            }
            OkHttpClient builder1 = new OkHttpClient();
            Request build = new Request.Builder()
                    .post(builder.build())
                    .url(path)
                    .build();
            okhttp3.Call call = builder1.newCall(build);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(e);
                        }
                    });
                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    Log.e("", "-------请求到的数据--------" + string);
                    final Gson gson = new Gson();
                    handler.post(new Runnable() {
                        Object o = gson.fromJson(string, cls);

                        @Override
                        public void run() {
                            callBack.onSuccess(o);
                        }
                    });
                }
            });

        }
}
