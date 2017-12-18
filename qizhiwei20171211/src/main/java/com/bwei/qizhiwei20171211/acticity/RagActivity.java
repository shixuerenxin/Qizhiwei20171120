package com.bwei.qizhiwei20171211.acticity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.qizhiwei20171211.R;
import com.bwei.qizhiwei20171211.bean.RagBean;
import com.bwei.qizhiwei20171211.personter.IView;
import com.bwei.qizhiwei20171211.personter.OkPersonter;
import com.bwei.qizhiwei20171211.view.MainActivity;

import java.util.HashMap;
import java.util.Map;
//注册页面
public class RagActivity extends AppCompatActivity {

    private ImageView ivBackRag;
    private EditText etPassword;
    private EditText etPhone;
    private Button rag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rag);
        ivBackRag = (ImageView) findViewById(R.id.iv_back_rag);
        etPhone = (EditText) findViewById(R.id.et_phone_rag);
        etPassword = (EditText) findViewById(R.id.et_password_rag);
        rag = (Button) findViewById(R.id.rag);
        ivBackRag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });


    }

    private void initData() {
        OkPersonter okPersonter = new OkPersonter();
        Map<String ,String> map = new HashMap<>();
        map.put("mobile",etPhone.getText().toString());
        map.put("password",etPassword.getText().toString());
        okPersonter.postData("http://120.27.23.105/user/reg", map, RagBean.class, new IView() {
            @Override
            public void success(Object o) {
                RagBean loginBean= (RagBean) o;
                String code = loginBean.getCode();
                int i = Integer.parseInt(code);
                if (i==0){
                    Intent intent = new Intent(RagActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(RagActivity.this,""+loginBean.getMsg(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failed(Exception e) {
                Toast.makeText(RagActivity.this,""+e,Toast.LENGTH_SHORT).show();
            }
        });
    }
    //当跳出这个页面将密码与手机号设置为空
    @Override
    protected void onStop() {
        super.onStop();
        etPassword.setText("");
        etPhone.setText("");
    }
}
