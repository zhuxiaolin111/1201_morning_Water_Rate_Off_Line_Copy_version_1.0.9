package com.northsoft.water_rate_off_line_copy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.northsoft.Share_MyApplication.MyApplication;

/**
 * Created by chensiqi on 2016/11/23.
 */

public class TestPage extends Activity {

    private TextView ada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page);

        ada = (TextView) findViewById(R.id.ceshitext);
        MyApplication application = (MyApplication) getApplication();
        String aaa = application.getJiekou6_2Model().getData().get(0).getMeterType();

        ada.setText(aaa);
    }
}
