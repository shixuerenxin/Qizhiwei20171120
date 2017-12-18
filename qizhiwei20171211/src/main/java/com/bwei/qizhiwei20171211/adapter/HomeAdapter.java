package com.bwei.qizhiwei20171211.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.qizhiwei20171211.R;
import com.bwei.qizhiwei20171211.bean.HomeBean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/11.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHandler>{

    private Context context;
    private List<HomeBean.DataBean> list;

    public HomeAdapter(Context context, List<HomeBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
//
    @Override
    public MyViewHandler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_home, null);
        MyViewHandler myViewHandler = new MyViewHandler(inflate);
        return myViewHandler;
    }
//绑定
    @Override
    public void onBindViewHolder(MyViewHandler holder, int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("!");
        Glide.with(context).load(split[0]).into(holder.ivShow);
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvprice.setText("原价："+list.get(position).getPrice());
        holder.tvprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvBargainPrice.setText("优惠价："+list.get(position).getBargainPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHandler extends RecyclerView.ViewHolder{

        private final TextView tvprice;
        private final TextView tvBargainPrice;
        private final TextView tvTitle;
        private final ImageView ivShow;

        public MyViewHandler(View itemView) {
            super(itemView);
            ivShow = (ImageView)itemView.findViewById(R.id.iv_show);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvBargainPrice = (TextView)itemView.findViewById(R.id.tv_bargainPrice);
            tvprice = (TextView)itemView.findViewById(R.id.tv_price);

        }
    }

}
