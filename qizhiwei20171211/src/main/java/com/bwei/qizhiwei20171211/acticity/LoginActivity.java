package com.bwei.qizhiwei20171211.acticity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.bwei.qizhiwei20171211.R;
import com.bwei.qizhiwei20171211.adapter.HomeAdapter;
import com.bwei.qizhiwei20171211.bean.HomeBean;
import com.bwei.qizhiwei20171211.personter.IView;
import com.bwei.qizhiwei20171211.personter.OkPersonter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//首页

public class LoginActivity extends AppCompatActivity {

    private XRecyclerView xrvShow;
    private int page=0;
    private List<HomeBean.DataBean> list;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        xrvShow = (XRecyclerView) findViewById(R.id.xrv_show);
        list = new ArrayList<>();
        //设置布局
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(LoginActivity.this,LinearLayoutManager.VERTICAL,false);
        xrvShow.setLayoutManager(linearLayoutManager);
        //实例化适配器
        homeAdapter = new HomeAdapter(LoginActivity.this, list);
        xrvShow.setAdapter(homeAdapter);

        //添加上啦加载下拉刷新
        xrvShow.setPullRefreshEnabled(true);
        xrvShow.setLoadingMoreEnabled(true);
        xrvShow.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                /*homeAdapter.notifyDataSetChanged();*/
                page=0;
                xrvShow.refreshComplete();
                initData();
            }

            @Override
            public void onLoadMore() {
                list.clear();
                homeAdapter.notifyDataSetChanged();
                page+=1;
                xrvShow.loadMoreComplete();
                initData();

            }
        });
        initData();


    }
//获取数据
    private void initData() {
        //http://120.27.23.105/product/getProducts
        OkPersonter okPersonter = new OkPersonter();
        Map<String ,String> map = new HashMap<>();
        map.put("pscid","1");
        map.put("page",page+"");
        okPersonter.postData("http://120.27.23.105/product/getProducts", map, HomeBean.class, new IView() {
            @Override
            public void success(Object o) {
                HomeBean loginBean= (HomeBean) o;
                String code = loginBean.getCode();
                int i = Integer.parseInt(code);
                if (i==0){
                    list.addAll(loginBean.getData());
                }
                Toast.makeText(LoginActivity.this,""+loginBean.getMsg(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failed(Exception e) {
                Toast.makeText(LoginActivity.this,""+e,Toast.LENGTH_SHORT).show();
            }
        });

    }

}
