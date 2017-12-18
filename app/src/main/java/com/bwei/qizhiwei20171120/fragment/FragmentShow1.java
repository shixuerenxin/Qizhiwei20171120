package com.bwei.qizhiwei20171120.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.qizhiwei20171120.Presenter.PVeiw;
import com.bwei.qizhiwei20171120.R;
import com.bwei.qizhiwei20171120.adapter.MyAdapter;
import com.bwei.qizhiwei20171120.back.IView;
import com.bwei.qizhiwei20171120.bean.JsonBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Created by WangZhiQiang on 2017/11/20.
 */

public class FragmentShow1 extends Fragment implements IView{

    private List<JsonBean.DataBean> list;
    private RecyclerView viewById;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_show, null, false);
        viewById = (RecyclerView)inflate.findViewById(R.id.rlv_show);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        viewById.setLayoutManager(linearLayoutManager);
        add();
        myAdapter = new MyAdapter(getContext(), list);
        viewById.setAdapter(myAdapter);
        return inflate;
    }
    public void add(){
        PVeiw pVeiw = new PVeiw();
        Map<String,String> map = new HashMap<>();
        map.put("uid","71");
        map.put("status","0");
        pVeiw.gethttp("https://www.zhaoapi.cn/product/getOrders",map,JsonBean.class);
    }

    @Override
    public void success(Object o) {
        JsonBean jsonBean= (JsonBean) o;
        List<JsonBean.DataBean> data = jsonBean.getData();
        list.addAll(data);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void failed(Exception e) {
        Log.e("","--------"+e);
    }
}
