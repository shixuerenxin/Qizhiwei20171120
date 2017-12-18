package com.bwei.qizhiwei20171120.util;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bwei.qizhiwei20171120.back.BackCall;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * author:Created by WangZhiQiang on 2017/11/20.
 */

public class OkhttpUtils {

    private static Handler handler=new Handler();
    private static volatile OkhttpUtils instance;
    public OkhttpUtils(){}
    public OkhttpUtils getInstance(){
        if (instance==null){
            synchronized (OkhttpUtils.class){
                if (null==instance){
                    instance=new OkhttpUtils();
                }
            }
        }
        return instance;
    }
     public static void get(String path, Map<String,String> map, final Class cls, final BackCall backCall){
         StringBuffer stringBuffer = new StringBuffer();
         stringBuffer.append(path);
         if (stringBuffer.indexOf("?")!=-1){
             if (stringBuffer.indexOf("?")!=stringBuffer.length()-1){
                 stringBuffer.append("&");
             }
         }else {
             stringBuffer.append("?");
         }
         for (Map.Entry<String,String> entry:map.entrySet()){
             stringBuffer.append(entry.getKey())
                     .append("=")
                     .append(entry.getValue())
                     .append("&");
         }
         stringBuffer.deleteCharAt(stringBuffer.lastIndexOf("&"));
         Log.e("*******String*****",""+stringBuffer.toString());
        /* OkHttpClient build = new OkHttpClient.Builder()
                 .addInterceptor(new CacheInterceptor())
                 .build();*/
         OkHttpClient okHttpClient = new OkHttpClient();
         final RequestBody requestBody = new RequestBody() {
             @Nullable
             @Override
             public MediaType contentType() {
                 return null;
             }

             @Override
             public void writeTo(BufferedSink sink) throws IOException {

             }
         };
         Request build1 = new Request.Builder()
                 .url(stringBuffer.toString())
                 .get()
                 .build();
         Call call = okHttpClient.newCall(build1);
         call.enqueue(new Callback() {
             @Override
             public void onFailure(final Call call, final IOException e) {
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                         backCall.onfailed(e);
                     }
                 });
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 final String string = response.body().string();
                 Log.e("*******String*****",""+string);
                 final Gson gson = new Gson();
                 handler.post(new Runnable() {
                     Object o = gson.fromJson(string, cls);
                     @Override
                     public void run() {
                         backCall.onSuccess(o);

                     }
                 });

             }
         });


     }


}
