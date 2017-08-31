package com.northsoft.Fragment_cell;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.model.jiekou5_model;
import com.northsoft.water_rate_off_line_copy.First_Page;
import com.northsoft.water_rate_off_line_copy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiaolin on 2017/04/16.
 */

public class Near_User_JiaoFei_list extends Fragment {

    private MyApplication application;
    private jiekou5_model mJiekou5_model;

    private List<Map<String, String>> listItem;

    private ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nearly_user_jiaofei_list_page, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        application = (MyApplication) getActivity().getApplication();
        int s = application.getJiekou5Model().size();
        Intent intent = getActivity().getIntent();
        String str = intent.getStringExtra("yonghubianhao");

        for (int i = 0; i < s; i++) {

            if (str.equals(application.getJiekou3_id_str_list().get(i))) {
                if (!String.valueOf(application.getJiekou5Model().get(i).getData()).equals("null")) {
                    mJiekou5_model = application.getJiekou5Model().get(i);
                } else {

                    return;
                }

            }
        }
        int ss = mJiekou5_model.getData().size();
        listItem = new ArrayList<>();

        for (int i = 0; i < ss; i++) {
            //设置list
            Map<String, String> item = new HashMap<>();
            if (mJiekou5_model.getData() != null) {
                item.put("paydate", First_Page.stampToDate(String.valueOf(mJiekou5_model.getData().get(i).getPayDate())));
                item.put("payment", String.valueOf(mJiekou5_model.getData().get(i).getPayment()));
                listItem.add(item);
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItem, R.layout.jianfei_listview,
                new String[]{"paydate", "payment"},
                new int[]{R.id.id_paydate, R.id.id_payment});
        list = (ListView) getActivity().findViewById(R.id.nearly_user_jiaofei_list);
        list.setAdapter(simpleAdapter);
    }
}

