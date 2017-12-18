package com.bwei.shoppingcartest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.shoppingcartest.R;
import com.bwei.shoppingcartest.ShoppingAdapter;
import com.bwei.shoppingcartest.bean.ShopBean;
import com.bwei.shoppingcartest.bean.ShoppingBean;
import com.bwei.shoppingcartest.personter.IView;
import com.bwei.shoppingcartest.personter.OkPersonter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<ShopBean.ListBean> list;
    private List<ShopBean> shopBeanList;
    private ExpandableListView elvShow;
    private ShoppingAdapter shoppingAdapter;
    private List<ShoppingBean.DataBean> shoppingBeanlist;
    private TextView tvAllPrice;
    private CheckBox cbAllCheck;
    private Boolean child=false;
    private Boolean group=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elvShow = (ExpandableListView) findViewById(R.id.elv_show);
        tvAllPrice = (TextView) findViewById(R.id.tv_all_price);
        cbAllCheck = (CheckBox) findViewById(R.id.cb_all_check);
        initData();
        shopBeanList = new ArrayList<>();

        shoppingAdapter = new ShoppingAdapter(this, shopBeanList,tvAllPrice);
        elvShow.setAdapter(shoppingAdapter);
        cbAllCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shopBeanList.clear();
                /*shoppingAdapter.notifyDataSetChanged();*/
                    child=b;
                    group=b;
                initData();
            }
        });



    }

    private void initData() {
        OkPersonter okPersonter = new OkPersonter();
        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","71");

        okPersonter.postData("https://www.zhaoapi.cn/product/getCarts", map, ShoppingBean.class, new IView() {
            @Override
            public void success(Object o) {
                ShoppingBean shoppingBean= (ShoppingBean) o;
                List<ShoppingBean.DataBean> data = shoppingBean.getData();

                /*shoppingBeanlist.addAll(data);*/
                for (int i = 0; i < data.size(); i++) {
            if (i != 0) {
                list = new ArrayList<>();
                    ShopBean shopBean = new ShopBean();
                    shopBean.setGroup(group);
                    shopBean.setSellerName(data.get(i).getSellerName());
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        ShopBean.ListBean listBean = new ShopBean.ListBean();
                        listBean.setChild(child);
                        listBean.setNum(data.get(i).getList().get(j).getNum());
                        listBean.setPrice(data.get(i).getList().get(j).getPrice());
                        listBean.setImages(data.get(i).getList().get(j).getImages());
                        listBean.setSubhead(data.get(i).getList().get(j).getSubhead());
                        listBean.setTitle(data.get(i).getList().get(j).getTitle());
                        list.add(listBean);
                    }
                    shopBean.setList(list);
                    shopBeanList.add(shopBean);
            }
                }
                shoppingAdapter.notifyDataSetChanged();
                for(int i = 0; i < shoppingAdapter.getGroupCount(); i++){
                    elvShow.expandGroup(i);
                }

            }

            @Override
            public void failed(Exception e) {
                Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
