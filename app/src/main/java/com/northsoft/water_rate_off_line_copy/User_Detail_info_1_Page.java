package com.northsoft.water_rate_off_line_copy;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.northsoft.Fragment_cell.User_Detail_Info_2_Fragment_1WeiChao;
import com.northsoft.Fragment_cell.User_Detail_Info_2_Fragment_2YiChao;
import com.northsoft.Fragment_cell.User_Detail_Info_2_Fragment_3QuanBu;
import com.northsoft.Fragment_cell.User_Detail_Info_2_Fragment_4QianFei;
import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.model.jiekou2_model;

import java.util.ArrayList;

/**
 * Created by chensiqi on 2016/11/23.
 */

public class User_Detail_info_1_Page extends AppCompatActivity implements View.OnClickListener {

    //定义页面内的变量
    private LinearLayout weichao_Lin, yichao_Lin, quanbu_Lin, qianfei_Lin;
    private User_Detail_Info_2_Fragment_3QuanBu mQuanbu;
    private User_Detail_Info_2_Fragment_1WeiChao mWeichao;
    private User_Detail_Info_2_Fragment_2YiChao mYichao;
    private User_Detail_Info_2_Fragment_4QianFei mQianfei;
    private TextView weichao, yichao, quanbu, qianfei;
    View view_weichao, view_yichao, view_quanbu, view_qianfei;
    // 布局管理器
    private FragmentManager fManager;
    FragmentTransaction ft;
    private jiekou2_model jiekou2Model;
    private MyApplication appli;

    //装在所有接口2数据的list
    private ArrayList<jiekou2_model> jiekou2_model_list;
    //定义时间戳
    private String time = getTime();
    String id_str;
    /* 进度条 */
    private ProgressDialog myDialog;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //下载接口2里的数据
                //      download_jiekou2_data();
            } else if (msg.what == 2) {

                appli.setJiekou2_Model_list(jiekou2_model_list);
                myDialog.dismiss();
                //设置默认的frag
                setDefultFrag();
                //下载接口3里的数据
                //download_jiekou3_data();
            } else if (msg.what == 3) {
                //下载接口4里的数据
                //  download_jiekou4_data();
                //下载接口5里的数据
                //    download_jiekou5_data();
            } else if (msg.what == 4) {
                //  appli.setJiekou4_1Model(jiekou4_1_model_list);
                //下载接口4.1里的数据
                // download_jiekou4_1_data();
            } else if (msg.what == 5) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_detail_page1);
        //Myapplication必须初始化
        appli = (MyApplication)getApplication();
      //  circle();
        Intent intent = getIntent();
        id_str = intent.getStringExtra("id_str");
     //   download_jiekou2_data();
        //初始化4个LinearLayout
        weichao_Lin = (LinearLayout) findViewById(R.id.weichao_frag);
        yichao_Lin = (LinearLayout) findViewById(R.id.yichao_frag);
        quanbu_Lin = (LinearLayout) findViewById(R.id.quanbu_frag);
        qianfei_Lin = (LinearLayout) findViewById(R.id.qianfei_frag);
        weichao = (TextView) findViewById(R.id.weichao);
        yichao = (TextView) findViewById(R.id.yichao);
        quanbu = (TextView) findViewById(R.id.quanbu);
        qianfei = (TextView) findViewById(R.id.qianfei);
        view_qianfei = findViewById(R.id.view_qianfei);
        view_weichao = findViewById(R.id.view_weichao);
        view_yichao = findViewById(R.id.view_yichao);
        view_quanbu = findViewById(R.id.view_quanbu);


        weichao_Lin.setOnClickListener(this);
        yichao_Lin.setOnClickListener(this);
        quanbu_Lin.setOnClickListener(this);
        qianfei_Lin.setOnClickListener(this);

       setDefultFrag();
    }

    //获取系统时间戳
    public String getTime() {
        long time = System.currentTimeMillis();
        String str = String.valueOf(time);
        return str;
    }

    //设置默认的frag
    public void setDefultFrag() {
        fManager = getFragmentManager();
        ft = fManager.beginTransaction();
        mQuanbu = new User_Detail_Info_2_Fragment_3QuanBu();
        mWeichao = new User_Detail_Info_2_Fragment_1WeiChao();
        mYichao = new User_Detail_Info_2_Fragment_2YiChao();
        mQianfei = new User_Detail_Info_2_Fragment_4QianFei();
        ft.replace(R.id.xuanzechaobiaofanwei, mQuanbu);
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        fManager = getFragmentManager();
        ft = fManager.beginTransaction();
        switch (view.getId()) {
            case R.id.weichao_frag:
                if (mWeichao == null) {
                    mWeichao = new User_Detail_Info_2_Fragment_1WeiChao();
                    weichao.setTextColor(getResources().getColor(R.color.menu_click));
                    yichao.setTextColor(getResources().getColor(R.color.defult));
                    quanbu.setTextColor(getResources().getColor(R.color.defult));
                    qianfei.setTextColor(getResources().getColor(R.color.defult));
                    view_weichao.setBackgroundColor(getResources().getColor(R.color.menu_click));
                    view_yichao.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_quanbu.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_qianfei.setBackgroundColor(getResources().getColor(R.color.defult));
                }

                weichao.setTextColor(getResources().getColor(R.color.menu_click));
                yichao.setTextColor(getResources().getColor(R.color.defult));
                quanbu.setTextColor(getResources().getColor(R.color.defult));
                qianfei.setTextColor(getResources().getColor(R.color.defult));
                view_weichao.setBackgroundColor(getResources().getColor(R.color.menu_click));
                view_yichao.setBackgroundColor(getResources().getColor(R.color.defult));
                view_quanbu.setBackgroundColor(getResources().getColor(R.color.defult));
                view_qianfei.setBackgroundColor(getResources().getColor(R.color.defult));
                ft.replace(R.id.xuanzechaobiaofanwei, mWeichao);

                break;
            case R.id.yichao_frag:
                if (mYichao == null) {
                    mYichao = new User_Detail_Info_2_Fragment_2YiChao();
                    weichao.setTextColor(getResources().getColor(R.color.defult));
                    yichao.setTextColor(getResources().getColor(R.color.menu_click));
                    quanbu.setTextColor(getResources().getColor(R.color.defult));
                    qianfei.setTextColor(getResources().getColor(R.color.defult));
                    view_weichao.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_yichao.setBackgroundColor(getResources().getColor(R.color.menu_click));
                    view_quanbu.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_qianfei.setBackgroundColor(getResources().getColor(R.color.defult));
                }
                ft.replace(R.id.xuanzechaobiaofanwei, mYichao);
                weichao.setTextColor(getResources().getColor(R.color.defult));
                yichao.setTextColor(getResources().getColor(R.color.menu_click));
                quanbu.setTextColor(getResources().getColor(R.color.defult));
                qianfei.setTextColor(getResources().getColor(R.color.defult));
                view_weichao.setBackgroundColor(getResources().getColor(R.color.defult));
                view_yichao.setBackgroundColor(getResources().getColor(R.color.menu_click));
                view_quanbu.setBackgroundColor(getResources().getColor(R.color.defult));
                view_qianfei.setBackgroundColor(getResources().getColor(R.color.defult));


                break;
            case R.id.qianfei_frag:
                if (mQianfei == null) {
                    mQianfei = new User_Detail_Info_2_Fragment_4QianFei();
                    weichao.setTextColor(getResources().getColor(R.color.defult));
                    yichao.setTextColor(getResources().getColor(R.color.defult));
                    quanbu.setTextColor(getResources().getColor(R.color.defult));
                    qianfei.setTextColor(getResources().getColor(R.color.menu_click));
                    view_weichao.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_yichao.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_quanbu.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_qianfei.setBackgroundColor(getResources().getColor(R.color.menu_click));
                }
                weichao.setTextColor(getResources().getColor(R.color.defult));
                yichao.setTextColor(getResources().getColor(R.color.defult));
                quanbu.setTextColor(getResources().getColor(R.color.defult));
                qianfei.setTextColor(getResources().getColor(R.color.menu_click));
                view_weichao.setBackgroundColor(getResources().getColor(R.color.defult));
                view_yichao.setBackgroundColor(getResources().getColor(R.color.defult));
                view_quanbu.setBackgroundColor(getResources().getColor(R.color.defult));
                view_qianfei.setBackgroundColor(getResources().getColor(R.color.menu_click));
                ft.replace(R.id.xuanzechaobiaofanwei, mQianfei);


                break;
            case R.id.quanbu_frag:
                if (mQuanbu == null) {
                    mQuanbu = new User_Detail_Info_2_Fragment_3QuanBu();
                    weichao.setTextColor(getResources().getColor(R.color.defult));
                    yichao.setTextColor(getResources().getColor(R.color.defult));
                    quanbu.setTextColor(getResources().getColor(R.color.menu_click));
                    qianfei.setTextColor(getResources().getColor(R.color.defult));
                    view_weichao.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_yichao.setBackgroundColor(getResources().getColor(R.color.defult));
                    view_quanbu.setBackgroundColor(getResources().getColor(R.color.menu_click));
                    view_qianfei.setBackgroundColor(getResources().getColor(R.color.defult));
                }

                weichao.setTextColor(getResources().getColor(R.color.defult));
                yichao.setTextColor(getResources().getColor(R.color.defult));
                quanbu.setTextColor(getResources().getColor(R.color.menu_click));
                qianfei.setTextColor(getResources().getColor(R.color.defult));
                view_weichao.setBackgroundColor(getResources().getColor(R.color.defult));
                view_yichao.setBackgroundColor(getResources().getColor(R.color.defult));
                view_quanbu.setBackgroundColor(getResources().getColor(R.color.menu_click));
                view_qianfei.setBackgroundColor(getResources().getColor(R.color.defult));
                ft.replace(R.id.xuanzechaobiaofanwei, mQuanbu);
                break;
        }
        //事务提交
        //    ft.addToBackStack(null);
        ft.commit();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDialog != null) {
            myDialog.dismiss();
            myDialog = null;
        }
    }


}