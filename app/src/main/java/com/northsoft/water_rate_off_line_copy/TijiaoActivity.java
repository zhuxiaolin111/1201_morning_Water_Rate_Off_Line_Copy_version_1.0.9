package com.northsoft.water_rate_off_line_copy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhuxiaolin on 2017/4/10 9:11.
 */

public class TijiaoActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_tijiao;
    //SQLite数据库管理类的实体化
    private DBManager mgr;
    private Cursor cursor;

    private ProgressDialog myDialog;
    //存放从数据库中取出数据的ArrayList
    private ArrayList<String> useridArrayList = new ArrayList<>();
    private ArrayList<String> bianhaoArrayList = new ArrayList<>();
    private ArrayList<String> MeterID_1ArrayList = new ArrayList<>();
    private ArrayList<String> MeterID_2ArrayList = new ArrayList<>();
    private ArrayList<String> MeterID_3ArrayList = new ArrayList<>();
    private ArrayList<String> NumEnd_1ArrayList = new ArrayList<>();
    private ArrayList<String> NumEnd_2ArrayList = new ArrayList<>();
    private ArrayList<String> NumEnd_3ArrayList = new ArrayList<>();
    //返回结果
    private ArrayList<String> result_list = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                int x = result_list.size();

                for (int i = 0; i < x; i++) {
                    if (result_list.get(i).equals("0")) {
                        myDialog.dismiss();
                        mgr.updatechobiao(bianhaoArrayList.get(i));
                        Toast.makeText(TijiaoActivity.this, "第" + (i + 1) + "条上传成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TijiaoActivity.this, "第" + (i + 1) + "条上传失败", Toast.LENGTH_SHORT).show();
                    }

                }
                //清空所有list数据
                listclear();
                TijiaoActivity.this.finish();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tijiao_layout);
        mgr = new DBManager(this);
        btn_tijiao = (Button) findViewById(R.id.tijiaoquanbushuju_btn);
        btn_tijiao.setOnClickListener(this);
        //取得每行的集合cursor
        //cursor为所有"shifoujicha"的值为"no"的行
        cursor = mgr.search("no", "shifoushangchuan");
        put_data_into_listview();
    }


    //设置本页的ListView
    private void put_data_into_listview() {
        List<Map<String, String>> listItems = new ArrayList<>();
        //光标逐个下移,遍历Cursor,并将数据存入ListView
        while (cursor.moveToNext()) {
            Map<String, String> item = new HashMap<>();

            item.put("xingming", cursor.getString(cursor.getColumnIndex("userid")));
            item.put("bianhao", cursor.getString(cursor.getColumnIndex("fangjianbianhao")));
            item.put("MeterID_1", cursor.getString(cursor.getColumnIndex("NumEnd_1")));
            if (!cursor.getString(cursor.getColumnIndex("NumEnd_2")).equals("null")) {
                item.put("MeterID_2", cursor.getString(cursor.getColumnIndex("NumEnd_2")));
            } else {
                item.put("MeterID_2", "");
            }
            if (!cursor.getString(cursor.getColumnIndex("NumEnd_3")).equals("null")) {
                item.put("MeterID_3", cursor.getString(cursor.getColumnIndex("NumEnd_3")));
            } else {
                item.put("MeterID_3", "");
            }
            listItems.add(item);
        }
        SimpleAdapter simpleadapter = new SimpleAdapter(this, listItems, R.layout.show_data,
                new String[]{"xingming", "bianhao", "MeterID_1", "MeterID_2", "MeterID_3"},
                new int[]{R.id.xingming_data, R.id.bianhao_data, R.id.MeterID_1, R.id.MeterID_2, R.id.MeterID_3});
        final ListView list = (ListView) findViewById(R.id.zhanshitijiaoshuju_list);
        list.setAdapter(simpleadapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tijiaoquanbushuju_btn:
                if (isNetworkAvailable(this)) {
                    //获取当前经纬度坐标
                    //   get_jingdu_And_Weidu();
                    //提交数据
                    shangchuanshuju();
                    circle();
                } else {
                    new AlertDialog.Builder(TijiaoActivity.this)
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

    //通过接口上传数据
    public void shangchuanshuju() {
        //搜索所有"shifoujicha"值是"no"的
        cursor = mgr.search("no", "shifoushangchuan");
        while (cursor.moveToNext()) {
            useridArrayList.add(cursor.getString(cursor.getColumnIndex("userid")));
            bianhaoArrayList.add(cursor.getString(cursor.getColumnIndex("fangjianbianhao")));
            MeterID_1ArrayList.add(cursor.getString(cursor.getColumnIndex("MeterID_1")));
            MeterID_2ArrayList.add(cursor.getString(cursor.getColumnIndex("MeterID_2")));
            MeterID_3ArrayList.add(cursor.getString(cursor.getColumnIndex("MeterID_3")));
            NumEnd_1ArrayList.add(cursor.getString(cursor.getColumnIndex("NumEnd_1")));
            NumEnd_2ArrayList.add(cursor.getString(cursor.getColumnIndex("NumEnd_2")));
            NumEnd_3ArrayList.add(cursor.getString(cursor.getColumnIndex("NumEnd_3")));
        }


        //发送上传请求
        if (cursor.getCount() != 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        OkHttpClient okhttpclient = new OkHttpClient();
                        String meterid1 = MeterID_1ArrayList.get(i);
                        String meterid2 = MeterID_2ArrayList.get(i);
                        String meterid3 = MeterID_3ArrayList.get(i);
                        RequestBody formbody = null;
                        if (meterid1.equals("1") && meterid2.equals("null") && meterid3.equals("null")) {
                            formbody = new FormBody.Builder()
                                    .add("method", "MeterReadInput")
                                    .add("ID", bianhaoArrayList.get(i))
                                    .add("userid", useridArrayList.get(i))
                                    .add("MeterID", MeterID_1ArrayList.get(i))
                                    .add("NumEnd", NumEnd_1ArrayList.get(i))
                                    .build();
                        } else if (meterid1.equals("1") && meterid2.equals("2") && meterid3.equals("null")) {
                            formbody = new FormBody.Builder()
                                    .add("method", "MeterReadInput")
                                    .add("ID", bianhaoArrayList.get(i))
                                    .add("userid", useridArrayList.get(i))
                                    .add("MeterID", MeterID_1ArrayList.get(i))
                                    .add("MeterID", MeterID_2ArrayList.get(i))
                                    .add("NumEnd", NumEnd_1ArrayList.get(i))
                                    .add("NumEnd", NumEnd_2ArrayList.get(i))
                                    .build();
                        } else if (meterid1.equals("1") && meterid2.equals("2") && meterid3.equals("3")) {
                            formbody = new FormBody.Builder()
                                    .add("method", "MeterReadInput")
                                    .add("ID", bianhaoArrayList.get(i))
                                    .add("userid", useridArrayList.get(i))
                                    .add("MeterID", MeterID_1ArrayList.get(i))
                                    .add("MeterID", MeterID_2ArrayList.get(i))
                                    .add("MeterID", MeterID_3ArrayList.get(i))
                                    .add("NumEnd", NumEnd_1ArrayList.get(i))
                                    .add("NumEnd", NumEnd_2ArrayList.get(i))
                                    .add("NumEnd", NumEnd_3ArrayList.get(i))
                                    .build();
                        }
                        Request request = new Request.Builder()
                                .url("http://tltx.eheat.com.cn/waterservice/service.ashx")
                                .post(formbody)
                                .build();
                        Call call = okhttpclient.newCall(request);
                        try {
                            Response response = call.execute();
                            String result = response.body().string();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(result);
                                result_list.add(jsonObject.getString("Result"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    Message message = new Message();
                    message.what = 1;
                    handler.sendEmptyMessage(1);
                }
            }).start();
        } else {
            new AlertDialog.Builder(TijiaoActivity.this)
                    .setTitle("温馨提示")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage("无数据提交！")
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
        ConnectivityManager connectivity = (ConnectivityManager) TijiaoActivity.this
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
        result_list.removeAll(result_list);
        useridArrayList.removeAll(useridArrayList);
        bianhaoArrayList.removeAll(bianhaoArrayList);
        MeterID_3ArrayList.removeAll(MeterID_3ArrayList);
        MeterID_2ArrayList.removeAll(MeterID_2ArrayList);
        MeterID_1ArrayList.removeAll(MeterID_1ArrayList);
        NumEnd_1ArrayList.removeAll(NumEnd_1ArrayList);
        NumEnd_2ArrayList.removeAll(NumEnd_2ArrayList);
        NumEnd_3ArrayList.removeAll(NumEnd_3ArrayList);
      /*  result_list.clear();
        useridArrayList.clear();
        bianhaoArrayList.clear();
        MeterID_1ArrayList.clear();
        MeterID_2ArrayList.clear();
        MeterID_3ArrayList.clear();
        NumEnd_1ArrayList.clear();
        NumEnd_3ArrayList.clear();
        NumEnd_2ArrayList.clear();*/
    }

    /**
     * 圆形进度条测试..
     */
    public void circle() {
        myDialog = new ProgressDialog(TijiaoActivity.this); // 获取对象
        myDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // 设置样式为圆形样式
        myDialog.setTitle("友情提示"); // 设置进度条的标题信息
        myDialog.setMessage("上传数据，请稍后..."); // 设置进度条的提示信息
        myDialog.setIcon(android.R.drawable.ic_dialog_info); // 设置进度条的图标
        myDialog.setIndeterminate(false); // 设置进度条是否为不明确
        myDialog.setCancelable(true); // 设置进度条是否按返回键取消

        // 为进度条添加确定按钮 ， 并添加单机事件
        myDialog.setButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                myDialog.cancel(); // 撤销进度条
            }
        });

        myDialog.show(); // 显示进度条
    }
}
