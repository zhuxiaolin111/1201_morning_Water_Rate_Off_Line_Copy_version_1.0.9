package com.northsoft.Fragment_cell;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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

public class User_Detail_Info_2_Fragment_2YiChao extends Fragment {
    //本页面中接收的jiekou2的model
    private jiekou2_model benye_jiekou2_model ;

    //接口2中总数据的数量
    private int s ;
    //接口2中model的数量
    private int ss ;
    //first_page_id_list内的对象的数量
    private int sss;

    //定义一个ListView
    private List<Map<String,String>> listItem;

    private ListView test_list;
    private View view;// 需要返回的布局


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yichao_list_page,null);
    }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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

    /*    for (int i = 0; i < sss; i++) {
            if (jiekou1_chuanguolaide_id.equals(application.getJiekou2_id_str_list().get(i))){
                benye_jiekou2_model = application.getJiekou2_Model_list().get(i);
            }
        }*/
       benye_jiekou2_model = application.getJiekou2_Model_list().get(0);
        int s = benye_jiekou2_model.getData().size();

        listItem = new ArrayList<>();

        for (int i1 = 0; i1 < s; i1++) {
            if (benye_jiekou2_model.getData().get(i1).getIsread() == 1){
                Map<String,String> item = new HashMap<>();

                item.put("yonghubianhao",benye_jiekou2_model.getData().get(i1).getUserid());
                item.put("chaobiaocixu",String.valueOf(benye_jiekou2_model.getData().get(i1).getBookseq()));
                item.put("yonghumingcheng",benye_jiekou2_model.getData().get(i1).getOwnername());
                item.put("yonghudizhi",benye_jiekou2_model.getData().get(i1).getAddress());

                if (benye_jiekou2_model.getData().get(i1).getIsread() == 1){
                    item.put("shifouchaobiao","是");
                }else {
                    item.put("shifouchaobiao","否");
                }
                item.put("shuibiaoshu",String.valueOf(benye_jiekou2_model.getData().get(i1).getMetercount()));
                item.put("benyueshuiliang", String.valueOf(benye_jiekou2_model.getData().get(i1).getWaterused()));
                item.put("yucunjine",String.valueOf(benye_jiekou2_model.getData().get(i1).getLastfee()));
                item.put("benyueshuifei",String.valueOf(benye_jiekou2_model.getData().get(i1).getTotalfee()));
                item.put("xiaozhangjine",benye_jiekou2_model.getData().get(i1).getJiaofee());
                item.put("qianfeijine", String.valueOf(benye_jiekou2_model.getData().get(i1).getCensus()));
                item.put("shangcichaobiaoriqi", First_Page.stampToDate(String.valueOf(benye_jiekou2_model.getData().get(i1).getLastcheckmeterdate())));

                listItem.add(item);
            }
        }

        SimpleAdapter simleAdapter = new SimpleAdapter(getActivity(),listItem,
                R.layout.user_info_detail_page_cell1,
                new String[]{"yonghubianhao","chaobiaocixu","yonghumingcheng","yonghudizhi","shifouchaobiao",
                        "shuibiaoshu","benyueshuiliang","yucunjine","benyueshuifei","xiaozhangjine","qianfeijine","shangcichaobiaoriqi"},
                new int[]{R.id.yonghubianhao,R.id.chaobiaocixu,R.id.yonghumingcheng,R.id.yonghudizhi,
                        R.id.shifouchaobiao,R.id.shuibiaoshu,R.id.benyueshuiliang,R.id.yucunjine,R.id.benyueshuifei,
                        R.id.xiaozhangjine, R.id.qianfeijine,R.id.shangcichaobiaoriqi});
        test_list = (ListView) getActivity().findViewById(R.id.yichao_list_page);
        test_list.setAdapter(simleAdapter);
        Resources resources = getResources();
        Drawable drawable = resources.getDrawable(R.drawable.cast_abc_scrubber_control_to_pressed_mtrl_005);
        test_list.setBackground(drawable);

        test_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap item = (HashMap) adapterView.getItemAtPosition(i);
                Intent intent = new Intent();
                intent.setClass(getActivity(), User_info_menu.class);
                String str = item.get("yonghubianhao").toString();
                intent.putExtra("yonghubianhao",str);
                getActivity().startActivity(intent);

            }
        });
    }
}
