package com.bwei.qizhiwei20171211.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.qizhiwei20171211.R;
import com.bwei.qizhiwei20171211.acticity.LoginActivity;
import com.bwei.qizhiwei20171211.acticity.RagActivity;
import com.bwei.qizhiwei20171211.bean.LoginBean;
import com.bwei.qizhiwei20171211.personter.IView;
import com.bwei.qizhiwei20171211.personter.OkPersonter;

import java.util.HashMap;
import java.util.Map;
//登录页面
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPassWord;
    private EditText etPhone;
    private Button butRag;
    private Button butLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initData();




    }
    //请求数据

    private void getData() {
        OkPersonter okPersonter = new OkPersonter();
        Map<String ,String> map = new HashMap<>();
        map.put("mobile",etPhone.getText().toString());
        map.put("password",etPassWord.getText().toString());
        okPersonter.postData("http://120.27.23.105/user/login", map, LoginBean.class, new IView() {
            @Override
            public void success(Object o) {
                LoginBean loginBean= (LoginBean) o;
                String code = loginBean.getCode();
                int i = Integer.parseInt(code);
                if (i==0){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(MainActivity.this,""+loginBean.getMsg(),Toast.LENGTH_SHORT).show();


            }

            @Override
            public void failed(Exception e) {
                Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        etPhone = (EditText) findViewById(R.id.et_phone_login);
        etPassWord = (EditText) findViewById(R.id.et_password_login);
        butLogin = (Button) findViewById(R.id.but_login);
        butRag = (Button) findViewById(R.id.but_rag);
        butLogin.setOnClickListener(this);
        butRag.setOnClickListener(this);
    }

    //点击监听
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_login:
                getData();
                break;
            case R.id.but_rag:
                Intent intent = new Intent(MainActivity.this, RagActivity.class);
                startActivity(intent);
                break;
        }
    }
    //当跳出这个页面将密码与手机号设置为空
    @Override
    protected void onStop() {
        super.onStop();
        etPassWord.setText("");
        etPhone.setText("");
    }
}
