package com.bwei.shoppingcartest.utils;

import com.google.gson.Gson;

/**
 * author:Created by QiZhiWei on 2017/12/8.
 */

public class GsonUtil {

    private static volatile Gson instance;
    public static Gson getInstance(){
        if (instance==null){
            instance=new Gson();
        }
        return instance;
    }

}
