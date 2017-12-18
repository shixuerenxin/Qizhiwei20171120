package com.bwei.qizhiwei20171120.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.qizhiwei20171120.R;
import com.bwei.qizhiwei20171120.bean.JsonBean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/11/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHandler>{

    private Context context;
    private List<JsonBean.DataBean> list;

    public MyAdapter(Context context, List<JsonBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public myViewHandler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_zhi, null);
        myViewHandler myViewHandler = new myViewHandler(inflate);

        return myViewHandler;
    }

    @Override
    public void onBindViewHolder(myViewHandler holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.tvTai.setText(list.get(position).getOrderid());
        holder.tvPrice.setText(list.get(position).getPrice()+"");
        holder.tvTime.setText(list.get(position).getCreatetime());
        /*holder.tvDing.setText(list.get(position).getCreatetime());*/
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class myViewHandler extends ViewHolder{

        private final TextView title;
        private final TextView tvTai;
        private final TextView tvPrice;
        private final TextView tvTime;
        private final TextView tvDing;

        public myViewHandler(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.tv_title);
            tvTai = itemView.findViewById(R.id.tv_tai);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvDing = itemView.findViewById(R.id.but_ding);

        }
    }

}
