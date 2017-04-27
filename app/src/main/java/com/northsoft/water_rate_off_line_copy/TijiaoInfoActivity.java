package com.northsoft.water_rate_off_line_copy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.northsoft.order.DBManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhuxiaolin on 2017/4/13 12:03.
 */

public class TijiaoInfoActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_tijiao;
    //SQLite数据库管理类的实体化
    private DBManager mgr;
    private Cursor cursor;
    //存放从数据库中取出数据的ArrayList
    private ArrayList<String> useridArrayList = new ArrayList<>();
    private ArrayList<String> bianhaoArrayList = new ArrayList<>();
    private ArrayList<String> CensusArrayList = new ArrayList<>();
    private ArrayList<String> phoneArrayList = new ArrayList<>();
    //返回结果
    private ArrayList<String> result_list = new ArrayList<>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                int x = result_list.size();
                for (int i = 0; i < x; i++) {



                    if (result_list.get(i).equals("0")) {
                        String strings=bianhaoArrayList.get(i);
                        Toast.makeText(TijiaoInfoActivity.this, "第" + (i + 1) + "条上传成功", Toast.LENGTH_SHORT).show();
                        mgr.updateinfo(strings);
                    } else {
                        Toast.makeText(TijiaoInfoActivity.this, "第" + (i + 1) + "条上传失败", Toast.LENGTH_SHORT).show();
                    }


                }

                //清空所有list数据
                listclear();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tijiao_info_layout);
        mgr = new DBManager(this);
        btn_tijiao = (Button) findViewById(R.id.tijiao_btn);
        btn_tijiao.setOnClickListener(this);
        //取得每行的集合cursor
        //cursor为所有"shifoujicha"的值为"no"的行
        cursor = mgr.search1("no", "shifoushangchuan");
        put_data_into_listview();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tijiao_btn:
                if (isNetworkAvailable(this)) {

                    //提交数据
                    shangchuanshuju();
                } else {
                    new AlertDialog.Builder(TijiaoInfoActivity.this)
                            .setTitle("注意")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setMessage("请到网络信号良好的区域提交数据！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //销毁页面
                                    finish();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                }
                break;
        }
    }

    //设置本页的ListView
    private void put_data_into_listview() {
        List<Map<String, String>> listItems = new ArrayList<>();
        //光标逐个下移,遍历Cursor,并将数据存入ListView
        while (cursor.moveToNext()) {
            Map<String, String> item = new HashMap<>();

            item.put("xingming", cursor.getString(cursor.getColumnIndex("userid")));
            item.put("bianhao", cursor.getString(cursor.getColumnIndex("yonghubianhao")));
            item.put("phone", cursor.getString(cursor.getColumnIndex("phone")));
            item.put("people", cursor.getString(cursor.getColumnIndex("Census")));

            listItems.add(item);
        }
        SimpleAdapter simpleadapter = new SimpleAdapter(this, listItems, R.layout.show_info_data,
                new String[]{"xingming", "bianhao", "phone", "people"},
                new int[]{R.id.xingming_data, R.id.bianhao_data, R.id.phone, R.id.people});
        final ListView list = (ListView) findViewById(R.id.lvInfo);
        list.setAdapter(simpleadapter);
    }

    //通过接口上传数据
    public void shangchuanshuju() {
        //搜索所有"shifoujicha"值是"no"的
        cursor = mgr.search1("no", "shifoushangchuan");
        while (cursor.moveToNext()) {
            useridArrayList.add(cursor.getString(cursor.getColumnIndex("userid")));
            bianhaoArrayList.add(cursor.getString(cursor.getColumnIndex("yonghubianhao")));
            phoneArrayList.add(cursor.getString(cursor.getColumnIndex("phone")));
            CensusArrayList.add(cursor.getString(cursor.getColumnIndex("Census")));
        }

        //发送上传请求
        if (cursor.getCount() != 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        OkHttpClient okhttpclient = new OkHttpClient();

                        RequestBody formbody = new FormBody.Builder()
                                .add("method", "Modify")
                                .add("ID", bianhaoArrayList.get(i))
                                .add("Phone", phoneArrayList.get(i))
                                .add("Census", CensusArrayList.get(i))
                                .add("userid", useridArrayList.get(i))
                                .build();
                        Request request = new Request.Builder()
                                .url("http://tltx.eheat.com.cn/waterservice/service.ashx")
                                .post(formbody)
                                .build();
                        Call call = okhttpclient.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String result = response.body().string();

                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    result_list.add(jsonObject.getString("Result"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    message.what = 1;
                    handler.sendEmptyMessage(1);
                }
            }).start();

          /*  new AlertDialog.Builder(TijiaoActivity.this)
                    .setTitle("温馨提示")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage("数据提交成功！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //销毁页面
                            //将数据库中的已提交的数据的"shifoujicha"项的"no"值改为"yes"
                            // mgr.update_isflag();
                         //   finish();
                        }
                    })
                    .show();*/
        } else {
            new AlertDialog.Builder(TijiaoInfoActivity.this)
                    .setTitle("温馨提示")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage("无稽查数据提交！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //销毁页面
                            finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();

        }
    }

    //判断是否有网络连接
    public boolean isNetworkAvailable(View.OnClickListener context) {
        ConnectivityManager connectivity = (ConnectivityManager) TijiaoInfoActivity.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    public void listclear() {
        result_list.clear();
        useridArrayList.clear();
        bianhaoArrayList.clear();
        phoneArrayList.clear();
        CensusArrayList.clear();
    }
}
