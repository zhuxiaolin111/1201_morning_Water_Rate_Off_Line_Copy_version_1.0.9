package com.northsoft.water_rate_off_line_copy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.model.jiekou1_model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chensiqi on 2016/11/22.
 */

public class First_Page extends AppCompatActivity {

    private List<Map<String, String>> listItem;
    private ListView list;
    ArrayList<jiekou1_model> list_last=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);

        MyApplication appli = (MyApplication) getApplication();

        //接收首页数据

        ArrayList<jiekou1_model> list_last = (ArrayList<jiekou1_model>) appli.getJiekou1_model_list();

        listItem = new ArrayList<>();
        int s=0;
        if(list_last.size()>=0){
          s = list_last.size();
            for (int i = 0; i < s; i++) {
                Map<String, String> item = new HashMap<>();

                item.put("biaobu_name", list_last.get(i).getName());
                item.put("biaobu_num", list_last.get(i).getId());
                item.put("yonghu_num", String.valueOf(list_last.get(i).getUsercount()));
                item.put("yichaoshu", String.valueOf(list_last.get(i).getReaded()));
                item.put("weichaoshu", String.valueOf(list_last.get(i).getUnread()));
                item.put("chaojianshuiliang", list_last.get(i).getWaterused());
                listItem.add(item);
            }
            SimpleAdapter simleAdapter = new SimpleAdapter(First_Page.this, listItem,
                    R.layout.first_page_biao_bu_cell,
                    new String[]{"biaobu_name", "biaobu_num", "yonghu_num", "yichaoshu", "weichaoshu", "chaojianshuiliang"},
                    new int[]{R.id.biaobu_name, R.id.biaobu_num, R.id.yonghushu, R.id.yichaoshu, R.id.weichaoshu, R.id.chaojianshuiliang});
            list = (ListView) findViewById(R.id.first_page_list);
            list.setAdapter(simleAdapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //取得点击的行
                    HashMap item = (HashMap) adapterView.getItemAtPosition(i);
                    //取得改行的id
                    String id_str = item.get("biaobu_num").toString();

                    Intent intent = new Intent();
                    intent.setClass(First_Page.this, MainActivity.class);
                    intent.putExtra("id_str", id_str);
                    startActivity(intent);

                }
            });
        }else {
            Toast.makeText(this, "数据请求失败，重新开启APP", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {

            System.exit(0);
        }
    }

    /*
 * 将时间戳转换为时间
 */
    public static String stampToDate(String s) {

        String sDate = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        try {
            Date date = sdf1.parse(s);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sDate = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sDate;
    }
}
