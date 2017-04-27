package com.northsoft.User_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.water_rate_off_line_copy.R;

/**
 * Created by chensiqi on 2016/11/29.
 */

public class User_Bill_Detail_Page extends AppCompatActivity {

    private MyApplication application;

    private TextView shuibiaoxuhao, yongshuixingzhi, qishu, zhishu, shuiliang, paiwufei, shuifei, hejijine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zuijinyonghu_zhangdanliebiao_detail_cell);
        //初始化
        application = (MyApplication) getApplication();
        //初始化textview
        shuibiaoxuhao = (TextView) findViewById(R.id.shuibiaoxuhao);
        yongshuixingzhi = (TextView) findViewById(R.id.yongshuixingzhi);
        qishu = (TextView) findViewById(R.id.qishu);
        zhishu = (TextView) findViewById(R.id.zhishu);
        shuiliang = (TextView) findViewById(R.id.shuiliang);
        paiwufei = (TextView) findViewById(R.id.paiwufei);
        shuifei = (TextView) findViewById(R.id.shuifei);
        hejijine = (TextView) findViewById(R.id.hejijine);

        Intent intent = getIntent();
        String str = intent.getStringExtra("billID");

        //  Toast.makeText(User_Bill_Detail_Page.this, str, Toast.LENGTH_LONG).show();

        int s = application.getJiekou4_1_BILLID_str_list().size();
        int i;

        for (i = 0; i < s; i++) {
            if (str.equals(application.getJiekou4_1_BILLID_str_list().get(i))) {
                //  Toast.makeText(application, i + "", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (application.getJiekou4_1_BILLID_str_list().get(i) != "") {
            shuibiaoxuhao.setText(application.getJiekou4_1Model().get(i).getData().get(0).getMeterid() + "");
            yongshuixingzhi.setText(application.getJiekou4_1Model().get(i).getData().get(0).getMetertype() + "");
            qishu.setText(application.getJiekou4_1Model().get(i).getData().get(0).getMeterstart() + "");
            zhishu.setText(application.getJiekou4_1Model().get(i).getData().get(0).getMeterend() + "");
            shuiliang.setText(application.getJiekou4_1Model().get(i).getData().get(0).getWaterused() + "");
            paiwufei.setText(String.valueOf(application.getJiekou4_1Model().get(i).getData().get(0).getDrainfee()));
            shuifei.setText(String.valueOf(application.getJiekou4_1Model().get(i).getData().get(0).getWaterfee()));
            hejijine.setText(String.valueOf((application.getJiekou4_1Model().get(i).getData().get(0).getDrainfee() +
                    application.getJiekou4_1Model().get(i).getData().get(0).getWaterfee())));
        } else {
            shuibiaoxuhao.setText("无数据");
            yongshuixingzhi.setText("无数据");
            qishu.setText("无数据");
            zhishu.setText("无数据");
            shuiliang.setText("无数据");
            paiwufei.setText("无数据");
            shuifei.setText("无数据");
            hejijine.setText("无数据");
        }
    }
}
