package com.bwei.qizhiwei20171121;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/11/21.
 */

public class PullListView extends ListView {
    public PullListView(Context context) {
        this(context,null);
    }

    public PullListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addAdapter(context);
    }


    private void addAdapter(Context context) {
        ListView listView = new ListView(context);
        List<String> list=new ArrayList<>();
        String[] name={"宋江","卢俊义","吴用","公孙胜" };
        for (int i = 0; i < 30; i++) {
            list.add(name[i%4]+i);
        }
        ListAdapter listAdapter = new ListAdapter(context, list);
        listView.setAdapter(listAdapter);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }


}
