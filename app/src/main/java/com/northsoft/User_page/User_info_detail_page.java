package com.northsoft.User_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.water_rate_off_line_copy.R;

/**
 * Created by chensiqi on 2016/11/28.
 */

public class User_info_detail_page extends AppCompatActivity {

    private MyApplication application;
    private String yonghubianhao;


    private TextView bianhao, xingming, dianhua, dizhi, yonghuleibie, biaobuleibie, jiliangfangshi, renkoushu,
            yonghuzhuangtai, chaobiaoyuan, kahao, yingyesuo, ICyonghu, yucunjine, benyueshuifei, xiaozhangjine, qianfeijine,
            biao1, biao2, biao3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_detail_page);

        //初始化所有Textview
        bianhao = (TextView) findViewById(R.id.bianhao);
        xingming = (TextView) findViewById(R.id.xingming);
        dianhua = (TextView) findViewById(R.id.dianhua);
        dizhi = (TextView) findViewById(R.id.dizhi);
        yonghuleibie = (TextView) findViewById(R.id.yonghuleibie);
        biaobuleibie = (TextView) findViewById(R.id.biaobuleibie);
        jiliangfangshi = (TextView) findViewById(R.id.jiliangfangshi);
        renkoushu = (TextView) findViewById(R.id.renkoushu);
        yonghuzhuangtai = (TextView) findViewById(R.id.yonghuzhuangtai);
        chaobiaoyuan = (TextView) findViewById(R.id.chaobiaoyuan);
        kahao = (TextView) findViewById(R.id.kahao);
        yingyesuo = (TextView) findViewById(R.id.yingyesuo);
        ICyonghu = (TextView) findViewById(R.id.ic_yonghu);
        yucunjine = (TextView) findViewById(R.id.yucunjine);
        benyueshuifei = (TextView) findViewById(R.id.benyueshuifei);
        xiaozhangjine = (TextView) findViewById(R.id.xiaozhangjine);
        qianfeijine = (TextView) findViewById(R.id.qianfeijine);
        biao1 = (TextView) findViewById(R.id.biao1);
        biao2 = (TextView) findViewById(R.id.biao2);
        biao3 = (TextView) findViewById(R.id.biao3);

        yonghubianhao = new String();

        application = (MyApplication) getApplication();

        Intent intent = getIntent();
        yonghubianhao = intent.getStringExtra("yonghubianhao");

        int s = application.getJiekou3_id_str_list().size();

        for (int i = 0; i < s; i++) {
            if (yonghubianhao.equals(application.getJiekou3_id_str_list().get(i))) {

                bianhao.setText(String.valueOf(application.jiekou3Model.get(i).getData().get(0).getUserid()));
                xingming.setText(application.jiekou3Model.get(i).getData().get(0).getOwnername());
                dianhua.setText(application.jiekou3Model.get(i).getData().get(0).getPhone());
                dizhi.setText(application.jiekou3Model.get(i).getData().get(0).getAddress());
                yonghuleibie.setText(application.jiekou3Model.get(i).getData().get(0).getUsertype());
                biaobuleibie.setText(application.jiekou3Model.get(i).getData().get(0).getBooktype());
                jiliangfangshi.setText(application.jiekou3Model.get(i).getData().get(0).getChargetype());
                renkoushu.setText(String.valueOf(application.jiekou3Model.get(i).getData().get(0).getCensus()));
                yonghuzhuangtai.setText(application.jiekou3Model.get(i).getData().get(0).getState());
                chaobiaoyuan.setText(application.jiekou3Model.get(i).getData().get(0).getCCashiername());
                kahao.setText(application.jiekou3Model.get(i).getData().get(0).getCardnum());
                yingyesuo.setText(application.jiekou3Model.get(i).getData().get(0).getWangname());
                if (application.jiekou3Model.get(i).getData().get(0).getIsic() == 0) {
                    ICyonghu.setText("否");
                } else {
                    ICyonghu.setText("是");
                }
                yucunjine.setText(String.valueOf(application.jiekou3Model.get(i).getData().get(0).getLastfee()));
                benyueshuifei.setText(String.valueOf(application.jiekou3Model.get(i).getData().get(0).getTotalfee()));
                xiaozhangjine.setText(String.valueOf(application.jiekou3Model.get(i).getData().get(0).getJiaofee()));
                qianfeijine.setText(String.valueOf(application.jiekou3Model.get(i).getData().get(0).getOweFee()));

            }

        }

    }




    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
