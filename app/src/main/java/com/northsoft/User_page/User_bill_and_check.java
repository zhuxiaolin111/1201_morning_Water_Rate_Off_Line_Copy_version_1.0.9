package com.northsoft.User_page;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.northsoft.Fragment_cell.Near_User_JiaoFei_list;
import com.northsoft.Fragment_cell.Nearly_User_Bill_List;
import com.northsoft.water_rate_off_line_copy.R;

/**
 * Created by chensiqi on 2016/11/28.
 */

public class User_bill_and_check extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout bill_and_check_Rel;

    private Nearly_User_Bill_List nearly_user_bill_list;
    private Near_User_JiaoFei_list nearUserJiaoFeiList;

    private LinearLayout zuijinzhangdan_Lin,zuijinjiaofei_Lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_bill_and_check);

        //初始化每个Fragment和底层的RelativeLayout
        bill_and_check_Rel = (RelativeLayout) findViewById(R.id.bill_and_check_Rel);
        zuijinzhangdan_Lin = (LinearLayout) findViewById(R.id.zuijinzhangdan_Lin);
        zuijinjiaofei_Lin = (LinearLayout) findViewById(R.id.zuijinjiaofei_Lin);

        //设置点击事件
        zuijinzhangdan_Lin.setOnClickListener(this);
        zuijinjiaofei_Lin.setOnClickListener(this);

        //设置默认的Fragment
        setDafultFragment();
    }

    public void setDafultFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        nearly_user_bill_list = new Nearly_User_Bill_List();
        nearUserJiaoFeiList = new Near_User_JiaoFei_list();
        ft.replace(R.id.bill_and_check_Rel,nearly_user_bill_list);
        ft.commit();
    }



    @Override
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (view.getId()){
            case R.id.zuijinzhangdan_Lin:
                if (nearly_user_bill_list == null){
                    nearly_user_bill_list = new Nearly_User_Bill_List();
                }
                ft.replace(R.id.bill_and_check_Rel,nearly_user_bill_list);
                break;
            case R.id.zuijinjiaofei_Lin:
                if (nearUserJiaoFeiList == null){
                    nearUserJiaoFeiList = new Near_User_JiaoFei_list();
                }
                ft.replace(R.id.bill_and_check_Rel,nearUserJiaoFeiList);
                break;
        }
        ft.commit();

    }
}
