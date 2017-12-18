package com.bwei.qizhiwei20171121;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/11/21.
 */

public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public ListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (i==0){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.e);
        }else {
        if (view==null){

        }}
        return view;
    }
    class ViewHandler {
       /* ImageView imageView;*/
        TextView textView;
    }
}
