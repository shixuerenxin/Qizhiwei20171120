package com.bwei.qizhiwei20171120.interce;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Created by WangZhiQiang on 2017/11/20.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl url=original.url().newBuilder()
                .addQueryParameter("source","android")
                .build();
        //添加请求头
        Request request = original.newBuilder()
                .url(url)
                .build();
        return chain.proceed(request);


    }
}
