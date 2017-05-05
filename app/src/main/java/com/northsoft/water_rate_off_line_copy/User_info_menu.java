package com.northsoft.water_rate_off_line_copy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.northsoft.User_page.Copy_water_meter;
import com.northsoft.User_page.User_bill_and_check;
import com.northsoft.User_page.User_info_detail_page;

/**
 * Created by chensiqi on 2016/11/28.
 */

public class User_info_menu extends AppCompatActivity implements View.OnClickListener{

    private Button yonghuziliao,yonghuzhangdanhejiaofeichaxun,chaobiao,xiugaiyonghuxinxi;
    private String yonghubanhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_menu);

        Intent intent1 = getIntent();
        yonghubanhao = intent1.getStringExtra("yonghubianhao");

        //初始化4个RelativeLayout
        yonghuziliao = (Button) findViewById(R.id.yonghuziliao);
        yonghuzhangdanhejiaofeichaxun = (Button) findViewById(R.id.yonghuzhangdanhejiaofeichaxun);
        chaobiao = (Button) findViewById(R.id.chaobiao);
      //  xiugaiyonghuxinxi = (Button) findViewById(R.id.xiugaiyonghuxinxi);

        //设置点击事件
        yonghuziliao.setOnClickListener(this);
        yonghuzhangdanhejiaofeichaxun.setOnClickListener(this);
        chaobiao.setOnClickListener(this);
      //  xiugaiyonghuxinxi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.yonghuziliao:
                intent.setClass(User_info_menu.this,User_info_detail_page.class);
                intent.putExtra("yonghubianhao",yonghubanhao);
                startActivity(intent);
                break;
            case R.id.yonghuzhangdanhejiaofeichaxun:
                intent.setClass(User_info_menu.this,User_bill_and_check.class);
                intent.putExtra("yonghubianhao",yonghubanhao);
                startActivity(intent);
                break;
            case R.id.chaobiao:
                intent.setClass(User_info_menu.this,Copy_water_meter.class);
                intent.putExtra("yonghubianhao",yonghubanhao);
                startActivity(intent);
      /*      case R.id.xiugaiyonghuxinxi:
                intent.setClass(User_info_menu.this,User_change_personal_info.class);
                intent.putExtra("yonghubianhao",yonghubanhao);
                startActivity(intent);
                break;*/
            break;
        }
    }
}
