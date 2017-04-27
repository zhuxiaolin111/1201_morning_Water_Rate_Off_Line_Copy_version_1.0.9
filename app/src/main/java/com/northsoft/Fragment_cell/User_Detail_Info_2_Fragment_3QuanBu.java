package com.northsoft.Fragment_cell;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.model.jiekou2_model;
import com.northsoft.water_rate_off_line_copy.First_Page;
import com.northsoft.water_rate_off_line_copy.R;
import com.northsoft.water_rate_off_line_copy.User_info_menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chensiqi on 2016/11/23.
 */

public class User_Detail_Info_2_Fragment_3QuanBu extends Fragment {
    //本页面中接收的jiekou2的model
    private jiekou2_model benye_jiekou2_model;

    //接口2中总数据的数量
    private int s;
    //接口2中model的数量
    private int ss;
    //first_page_id_list内的对象的数量
    private int sss;

    //定义一个ListView
    private List<Map<String, String>> listItem;

    private ListView test_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quanbu_list_page, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        benye_jiekou2_model = new jiekou2_model();
        //Myapplication必须初始化
        MyApplication application = (MyApplication) getActivity().getApplication();

        //first_page_id_list内的对象的数量
        sss = application.getJiekou2_id_str_list().size();
        //接口1中传过来的楼名的字符串的字符数
        Intent intent = getActivity().getIntent();
        String jiekou1_chuanguolaide_id = intent.getStringExtra("id_str");


     /*   for (int i = 0; i < sss; i++) {

           if (jiekou1_chuanguolaide_id.equals(application.getJiekou2_id_str_list().get(i))){
               benye_jiekou2_model = application.getJiekou2_Model_list().get(i);
              //  Toast.makeText(application, , Toast.LENGTH_SHORT).show();
            }
        }*/


        listItem = new ArrayList<>();
        benye_jiekou2_model = application.getJiekou2_Model_list().get(0);
        int s = benye_jiekou2_model.getData().size();

        for (int i = 0; i < s; i++) {
            Map<String, String> item = new HashMap<>();

            item.put("yonghubianhao", benye_jiekou2_model.getData().get(i).getUserid());
            item.put("chaobiaocixu", String.valueOf(benye_jiekou2_model.getData().get(i).getBookseq()));
            item.put("yonghumingcheng", benye_jiekou2_model.getData().get(i).getOwnername());
            item.put("yonghudizhi", benye_jiekou2_model.getData().get(i).getAddress());
            if (benye_jiekou2_model.getData().get(i).getIsread() == 1){
                item.put("shifouchaobiao","是");
            }else {
                item.put("shifouchaobiao","否");
            }

            item.put("shuibiaoshu", String.valueOf(benye_jiekou2_model.getData().get(i).getMetercount()));
            item.put("benyueshuiliang", String.valueOf(benye_jiekou2_model.getData().get(i).getWaterused()));
            item.put("yucunjine", String.valueOf(benye_jiekou2_model.getData().get(i).getLastfee()));
            item.put("benyueshuifei", String.valueOf(benye_jiekou2_model.getData().get(i).getTotalfee()));
            item.put("xiaozhangjine", benye_jiekou2_model.getData().get(i).getJiaofee());
            item.put("qianfeijine", String.valueOf(benye_jiekou2_model.getData().get(i).getCensus()));

            item.put("shangcichaobiaoriqi", First_Page.stampToDate(String.valueOf(benye_jiekou2_model.getData().get(i).getLastcheckmeterdate())));

            listItem.add(item);
        }
        SimpleAdapter simleAdapter = new SimpleAdapter(getActivity(), listItem,
                R.layout.user_info_detail_page_cell1,
                new String[]{"yonghubianhao", "chaobiaocixu", "yonghumingcheng", "yonghudizhi", "shifouchaobiao",
                        "shuibiaoshu", "benyueshuiliang", "yucunjine", "benyueshuifei", "xiaozhangjine", "qianfeijine", "shangcichaobiaoriqi"},
                new int[]{R.id.yonghubianhao, R.id.chaobiaocixu, R.id.yonghumingcheng, R.id.yonghudizhi,
                        R.id.shifouchaobiao, R.id.shuibiaoshu, R.id.benyueshuiliang, R.id.yucunjine, R.id.benyueshuifei,
                        R.id.xiaozhangjine, R.id.qianfeijine, R.id.shangcichaobiaoriqi});
        test_list = (ListView) getActivity().findViewById(R.id.quanbu_list_page);
        test_list.setAdapter(simleAdapter);

        test_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap item = (HashMap) adapterView.getItemAtPosition(i);
                Intent intent = new Intent();
                intent.setClass(getActivity(), User_info_menu.class);
                String str = item.get("yonghubianhao").toString();
                intent.putExtra("yonghubianhao", str);
                getActivity().startActivity(intent);
            }
        });

    }


}
