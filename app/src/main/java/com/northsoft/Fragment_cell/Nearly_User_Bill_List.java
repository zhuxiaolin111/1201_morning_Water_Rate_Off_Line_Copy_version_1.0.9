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
import com.northsoft.User_page.User_Bill_Detail_Page;
import com.northsoft.model.jiekou4_model;
import com.northsoft.water_rate_off_line_copy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chensiqi on 2016/11/29.
 */

public class Nearly_User_Bill_List extends Fragment {

    private MyApplication application;

    private jiekou4_model benye_model;

    private List<Map<String, String>> listItem;

    private ListView list;
    int ss;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nearly_user_bill_list_page, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化
        application = (MyApplication) getActivity().getApplication();
        listItem = new ArrayList<>();
        benye_model = new jiekou4_model();

        Intent intent = getActivity().getIntent();
        String str = intent.getStringExtra("yonghubianhao");

        int s = application.getJiekou3_id_str_list().size();

        for (int i = 0; i < s; i++) {
            if (str.equals(application.getJiekou3_id_str_list().get(i))) {
                if (!String.valueOf(application.getJiekou4Model().get(i).getData()).equals("null")) {
                    benye_model = application.getJiekou4Model().get(i);
                } else {
                    return;
                }
            }
        }

        ss = benye_model.getData().size();
        for (int i = 0; i < ss; i++) {
            //设置list
            Map<String, String> item = new HashMap<>();
            if (benye_model.getData() != null) {
                item.put("zhangdannianyue", benye_model.getData().get(i).getBillcycle());
                item.put("chaobiaoriqi", benye_model.getData().get(i).getReaddate());
                item.put("shuiliang", String.valueOf(benye_model.getData().get(i).getWaterused()));
                item.put("shuifei", String.valueOf(benye_model.getData().get(i).getWaterfee()));
                item.put("paiwufei", String.valueOf(benye_model.getData().get(i).getDrainfee()));
                item.put("zongjine", String.valueOf(benye_model.getData().get(i).getTotalfee()));
                item.put("billID", benye_model.getData().get(i).getBillid());

                listItem.add(item);
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItem, R.layout.zuijinyonghu_zhangdanliebiao_cell,
                new String[]{"zhangdannianyue", "chaobiaoriqi", "shuiliang", "shuifei", "paiwufei", "zongjine", "billID"},
                new int[]{R.id.zhangdannianyue, R.id.chaobiaoriqi, R.id.shuiliang, R.id.shuifei, R.id.paiwufei, R.id.zongjine,
                        R.id.billID});
        list = (ListView) getActivity().findViewById(R.id.nearly_user_bill_list);
        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //第一步——取得每行的BILLID
                HashMap item = (HashMap) adapterView.getItemAtPosition(i);
                String billid_str = item.get("billID").toString();

                Intent intent = new Intent();
                intent.setClass(getActivity(), User_Bill_Detail_Page.class);
                intent.putExtra("billID", billid_str);
                startActivity(intent);
            }
        });

    }

}

