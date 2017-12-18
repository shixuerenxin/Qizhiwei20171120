package com.bwei.qizhiwei20171120;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.bwei.qizhiwei20171120.fragment.FragmentShow1;
import com.bwei.qizhiwei20171120.fragment.FragmentShow2;
import com.bwei.qizhiwei20171120.fragment.FragmentShow3;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager viewById;
    private TextView pending;
    private TextView tvYi;
    private TextView tvCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (ViewPager) findViewById(R.id.vp_show);
        pending = (TextView)findViewById(R.id.tv_pending);
        tvYi = (TextView)findViewById(R.id.tv_yi);
        tvCancel = (TextView)findViewById(R.id.tv_cancel);
        pending.setOnClickListener(this);
        tvYi.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        this.viewById.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment =null;
                switch (position){
                    case 0:
                        fragment=new FragmentShow1();
                        break;
                    case 1:
                        fragment=new FragmentShow2();
                        break;
                    case 2:
                        fragment=new FragmentShow3();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_pending:
                viewById.setCurrentItem(0);
                break;
            case R.id.tv_yi:
                viewById.setCurrentItem(1);
                break;
            case R.id.tv_cancel:
                viewById.setCurrentItem(2);
                break;
        }
    }
}
