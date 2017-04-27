package com.northsoft.User_page;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.model.user_change_info;
import com.northsoft.order.DBManager;
import com.northsoft.water_rate_off_line_copy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensiqi on 2016/11/28.
 */

public class User_change_personal_info extends AppCompatActivity implements View.OnClickListener {

    private Button xiugai_btn;
    //SQLite数据库管理类的实体化
    private DBManager mgr;
    EditText phoneNum, peopleNum;
    String yonghubianhao;
    String peopleNum_str = "";
    String phone_str = "";
    private MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_user_info_page);
        mgr = new DBManager(this);
        application = (MyApplication) getApplication();
        Intent intent = getIntent();
        yonghubianhao = intent.getStringExtra("yonghubianhao");
        xiugai_btn = (Button) findViewById(R.id.id_xiugai);
        phoneNum = (EditText) findViewById(R.id.id_phone);
        peopleNum = (EditText) findViewById(R.id.id_people);
        xiugai_btn.setOnClickListener(this);
        phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 获得文本框中的用户
                phone_str = phoneNum.getText().toString().trim();
                //     Toast.makeText(User_change_personal_info.this, user, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        peopleNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 获得文本框中的用户
                peopleNum_str = peopleNum.getText().toString().trim();
                //     Toast.makeText(User_change_personal_info.this, people, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 获得文本框中的用户
                peopleNum_str = peopleNum.getText().toString().trim();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_xiugai:
                //do something
                //将手机号存入sqlite表
                if (!peopleNum_str.equals("") && !phone_str.equals("")) {
                    initSQLite();
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    User_change_personal_info.this.finish();
                } else {
                    Toast.makeText(this, "手机号和人口数不能为空！！！", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    //这个方法是点击保存按钮的时候调用的
    public void initSQLite() {
        //首先查询一下这个数据有没有存在数据库中
        //根据RoomID查询
        Cursor c = mgr.search1(yonghubianhao, "yonghubianhao");
        while (c.moveToNext()) {
            if (!"".equals(c.getString(c.getColumnIndex("yonghubianhao")))) {
                //删除这行
                user_change_info userChangeInfo = new user_change_info();
                userChangeInfo.yonghubianhao = yonghubianhao;
                mgr.deleteOldPerson1(userChangeInfo);
            }
        }
        //   String userid = null;
        String userid = application.getLogin_id_str_list().get(application.getLogin_id_str_list().size() - 1);
        ArrayList<user_change_info> userChangeInfoArrayList = new ArrayList<>();
        user_change_info userChangeInfo = new user_change_info(
                yonghubianhao,
                phone_str,
                peopleNum_str,
                userid,
                "no");
        userChangeInfoArrayList.add(userChangeInfo);
        mgr.add_userChangeInfo(userChangeInfoArrayList);

        List<user_change_info> list = mgr.query1();
//        Toast.makeText(User_Info.this,list.get(0).Type,Toast.LENGTH_LONG).show();

    }
}
