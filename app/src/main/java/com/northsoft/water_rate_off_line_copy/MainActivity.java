package com.northsoft.water_rate_off_line_copy;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.model.jiekou1_model;
import com.northsoft.model.jiekou2_model;
import com.northsoft.model.jiekou3_model;
import com.northsoft.model.jiekou4_1_model;
import com.northsoft.model.jiekou4_model;
import com.northsoft.model.jiekou5_model;
import com.northsoft.model.jiekou6_1_model;
import com.northsoft.model.jiekou6_2_model;
import com.northsoft.order.DBManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //下载接口2里的数据
                    download_jiekou2_data();
                    break;
                case 2:
                    appli.setJiekou2_Model_list(jiekou2_model_list);
                    //下载接口3里的数据
                    download_jiekou3_data();
                    break;
                case 3:
                    //下载接口4里的数据
                    download_jiekou4_data();
                    //下载接口6.2里的数据
                    download_jiekou6_2_data();

                    //下载接口6.1里的数据
                    //  download_jiekou6_1_data();
                    break;
                case 4:
                    appli.setJiekou4_1Model(jiekou4_1_model_list);
                    //下载接口5里的数据
                    download_jiekou5_data();
                    break;
                case 5:
                    //下载接口4.1里的数据
                    download_jiekou4_1_data();
                    break;
                case 6:
                    show_btn();
                    break;
                case 7:
                 Toast.makeText(MainActivity.this, "服务器连接超时", Toast.LENGTH_SHORT).show();
                 //  MainActivity.this.finish();
                    break;
            }
        }
    };

    private MyApplication appli;

    //装在这首页所有表簿编号的list
    private ArrayList<String> first_page_id_list;

    //装在所有接口2数据的list
    private ArrayList<jiekou2_model> jiekou2_model_list;

    //装有所有接口3数据的list
    private List<jiekou3_model> jiekou3_model_list;

    //装有所有接口4数据的list
    private List<jiekou4_model> jiekou4_model_list;

    //装有所有接口4。1数据的list
    private List<jiekou4_1_model> jiekou4_1_model_list;

    //定义装有所有接口3所需id的list
    private List<String> jiekou3_id_list;

    //装有所有接口5数据的list
    private List<jiekou5_model> jiekou5_model_list;
    //装有所有接口6_1数据的list
    private List<jiekou6_1_model> jiekou6_1_model_list;
    //装有所有接口6_2数据的list
    private List<jiekou6_2_model> jiekou6_2_model_list;
    //定义所有接口4.1需要的BILLID得list
    private List<String> jiekou4_1_billid_model_list;

    //定义页面元素
    private Button dianji_begin, tijiao_btn, xiugai_btn;
    private TextView downloading_data1, downloading_data2, downloading_data3, downloading_data4,
            downloading_data5, downloading_data6, downloading_data7, downloading_data8;

    //定义请求标记（以确定所有请求是否结束）
    private int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, jiekou2_biaoji = 0;


    //定义变量
    private String jiekou1_str, jiekou2_str, jiekou3_str, jiekou4_str,
            jiekou4_1_str, jiekou5_str, jiekou6_1_str, jiekou6_2_str;

    //存储接口1数据的list
    private List<jiekou1_model> list;

    private jiekou1_model jiekou1Model;
    private jiekou2_model jiekou2Model;
    private jiekou3_model jiekou3Model;
    private jiekou4_model jiekou4Model;
    private jiekou4_1_model jiekou4_1Model;
    private jiekou5_model jiekou5Model;
    private jiekou6_1_model jiekou6_1Model;
    private jiekou6_2_model jiekou6_2Model;

    //定义时间戳
    private String time = getTime();
    //SQLite数据库管理类的实体化
    private DBManager mgr;
    private Cursor cursor;

    //表薄号
    String id_str;
    private ProgressDialog myDialog;
    String id_login = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = getIntent();
        id_str = intent.getStringExtra("id_str");
        appli = new MyApplication();

       /* int x = appli.getLogin_id_str_list().size() - 1;
        id_login = appli.getLogin_id_str_list().get(x);  */
        circle();
        // Toast.makeText(MainActivity.this, "正在下载数据，请耐心等候", Toast.LENGTH_LONG).show();
        //初始化按钮
        dianji_begin = (Button) findViewById(R.id.dianji_begin_btn);
        tijiao_btn = (Button) findViewById(R.id.dianji_tijiao_btn);
        // xiugai_btn = (Button) findViewById(R.id.dianji_tijiaoinfo_btn);
        // downloading_data1 = (TextView) findViewById(R.id.downloading1);
        downloading_data2 = (TextView) findViewById(R.id.downloading2);
        downloading_data3 = (TextView) findViewById(R.id.downloading3);
        downloading_data4 = (TextView) findViewById(R.id.downloading4);
        downloading_data5 = (TextView) findViewById(R.id.downloading5);
        downloading_data6 = (TextView) findViewById(R.id.downloading6);
        //    downloading_data7 = (TextView) findViewById(R.id.downloading7);
        downloading_data8 = (TextView) findViewById(R.id.downloading8);
        //隐藏'开始抄表按钮'，在全部请求完毕后显示
        dianji_begin.setVisibility(View.INVISIBLE);
        tijiao_btn.setVisibility(View.INVISIBLE);
        //  xiugai_btn.setVisibility(View.INVISIBLE);
        //定义接收所有接口2数据的list
        jiekou2_model_list = new ArrayList<>();

        //初始化接收所有接口3数据的list
        jiekou3_model_list = new ArrayList<>();

        //初始化接收接口4数据的list
        jiekou4_model_list = new ArrayList<>();

        //初始化接收接口4.1数据的list
        jiekou4_1_model_list = new ArrayList<>();

        //初始化接收接口5数据的list
        jiekou5_model_list = new ArrayList<>();

        jiekou6_1_model_list = new ArrayList<>();
        jiekou6_2_model_list = new ArrayList<>();

        //初始化接收接口4.1数据的list
        jiekou4_1_billid_model_list = new ArrayList<>();

        //开始抄表按钮点击事件
        dianji_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, User_Detail_info_1_Page.class);
                intent.putExtra("id_str", id_str);
                startActivity(intent);
            }
        });
        tijiao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, TijiaoActivity.class);
                startActivity(intent1);
            }
        });
      /*  xiugai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, TijiaoInfoActivity.class);
                startActivity(intent1);
            }
        });*/

        //初始化接收接口1里id的list
        first_page_id_list = new ArrayList<>();

        //初始化Myapplication对象
        appli = (MyApplication) getApplication();
        //下载接口1里的数据
        download_jiekou2_data();
    }


    public void show_btn() {
        //所有数据下载完毕后才能点击开始按钮的方法（开启新线程以不影响主线程UI的绘制）
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (b + c + d + e + f + g + h == 6) {
                    myDialog.dismiss();
                    int i1 = appli.getJiekou3_id_str_list().size();
                    int i2 = appli.getJiekou4_1_BILLID_str_list().size();
                    int i3 = appli.getJiekou4Model().size();
                    int i4 = appli.getJiekou5Model().size();
                    int i5 = appli.getJiekou6_2modelList().size();
                    //    if (i1 == i2 && i1 == i3 && i2 == i5 && i3 == i5 && i4 == i5) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dianji_begin.setVisibility(View.VISIBLE);
                            tijiao_btn.setVisibility(View.VISIBLE);
                            //     xiugai_btn.setVisibility(View.VISIBLE);
                        }
                    });
                    break;
                }
            }
        }).start();
    }


    //下载接口2里的数据
    public void download_jiekou2_data() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //  int x = appli.getJiekou2_id_str_list().size();

                //    for (int i = 0; i < x; i++) {
                //    String id_str = appli.getJiekou2_id_str_list().get(i);
                // String id_str = "040217";
                String url = "http://tltx.eheat.com.cn/waterservice/service.ashx?" +
                        "method=UserList&id=" + id_str +
                        "&time=" + time;
                //测试
//                String url = "http://sylm.eheat.com.cn/waterservice/service.ashx?" +
//                        "method=UserList&id=" + id_str +
//                        "&time=" + time;
                Request.Builder requestBuilder = new Request.Builder().url(url);
                requestBuilder.method("GET", null);
                Request request = requestBuilder.build();
                final okhttp3.Call call = mOkHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    jiekou2Model = JSON.parseObject(response.body().string(), jiekou2_model.class);
                    jiekou2_model_list.add(jiekou2Model);
                    jiekou2_biaoji += 1;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //  }
                Message message = new Message();
                message.what = 2;
                handler.sendEmptyMessage(2);
                b = 1;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //   downloading_data1.setText("一级数据下载完毕");
                        downloading_data2.setText("下载完毕");
                    }
                });
            }
        }).start();

    }

    //下载接口3里的数据
    public void download_jiekou3_data() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient mOkHttpClient = new OkHttpClient();

                jiekou3_id_list = new ArrayList<>();
                //    int s = appli.getJiekou2_id_str_list().size();

                //   for (int i = 0; i < s; i++) {
                int ss = appli.getJiekou2_Model_list().get(0).getData().size();
                for (int i1 = 0; i1 < ss; i1++) {
                    String str = appli.getJiekou2_Model_list().get(0).getData().get(i1).getUserid();
                    jiekou3_id_list.add(str);
//                        System.out.println(str);
                }
                //    }
                //将装有所有接口3所需id的list存入Myapplication
                appli.setJiekou3_id_str_list(jiekou3_id_list);
                System.out.println(jiekou3_id_list.size());


                int m = appli.jiekou3_id_str_list.size();

                for (int i = 0; i < m; i++) {
                    String str = appli.getJiekou3_id_str_list().get(i);
                    Request.Builder requestBuilder = new Request.Builder().url("http://tltx.eheat.com.cn/waterservice/service.ashx?method=UserInfo&id=" + str + "&time=" + time);
                    requestBuilder.method("GET", null);
                    Request request = requestBuilder.build();
                    okhttp3.Call call = mOkHttpClient.newCall(request);

                    try {
                        Response response = call.execute();
                        jiekou3Model = JSON.parseObject(response.body().string(), jiekou3_model.class);
                        jiekou3_model_list.add(jiekou3Model);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                appli.setJiekou3Model(jiekou3_model_list);
                c = 1;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloading_data3.setText("下载完毕");
                    }
                });
                Message message = new Message();
                message.what = 3;
                handler.sendEmptyMessage(3);

            }
        }).start();
    }

    //下载接口4里的数据
    public void download_jiekou4_data() {
        final int s = appli.getJiekou3_id_str_list().size();
        final OkHttpClient mOkHttpClient = new OkHttpClient();
        int x = appli.getLogin_id_str_list().size() - 1;
        id_login = appli.getLogin_id_str_list().get(x);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < s; i++) {
                    String str = appli.getJiekou3_id_str_list().get(i);
                    RequestBody formbody = new FormBody.Builder()
                            .add("method", "BillList")
                            .add("ID", str)
                            .add("userid", id_login)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://tltx.eheat.com.cn/waterservice/service.ashx")
                            .post(formbody)
                            .build();
                    okhttp3.Call call = mOkHttpClient.newCall(request);
                    try {
                        Response response = call.execute();
                        String string = response.body().string();
                        jiekou4Model = JSON.parseObject(string, jiekou4_model.class);
                        jiekou4_model_list.add(jiekou4Model);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                appli.setJiekou4Model(jiekou4_model_list);
                //      System.out.println("哎哟我去" + appli.getJiekou4Model().get(0).getData().get(0).getBillid());
                //     System.out.println("哎哟我再去" + appli.getJiekou4Model().get(0).getData().get(1).getBillid());
                d = 1;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloading_data4.setText("下载完毕");
                    }
                });
                Message message1 = new Message();
                message1.what = 4;
                handler.sendEmptyMessage(4);
            }
        }).start();

    }

    //    //下载接口4.1里的数据
    public void download_jiekou4_1_data() {
        final OkHttpClient mOkHttpClient = new OkHttpClient();
        final int sss = appli.getJiekou3_id_str_list().size();
        int x = appli.getLogin_id_str_list().size() - 1;
        id_login = appli.getLogin_id_str_list().get(x);
        for (int i = 0; i < sss; i++) {

            //将BILLID存下来
            if (null != appli.getJiekou4Model().get(i).getData()) {
                int ss = appli.getJiekou4Model().get(i).getData().size();
                for (int i1 = 0; i1 < ss; i1++) {
                    String strr = appli.getJiekou4Model().get(i).getData().get(i1).getBillid();
                    jiekou4_1_billid_model_list.add(strr);
                }
            } else {
                jiekou4_1_billid_model_list.add("");
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {


                appli.setJiekou4_1_BILLID_str_list(jiekou4_1_billid_model_list);//所有BILLID都存在这里了
                int s = appli.getJiekou4_1_BILLID_str_list().size();
                for (int i = 0; i < s; i++) {
                    String str = appli.getJiekou4_1_BILLID_str_list().get(i);

                    if (str != null) {
                        RequestBody formbody4_1 = new FormBody.Builder()
                                .add("method", "BillDetail")
                                .add("BillID", str)
                                .add("userid", id_login)
                                .build();
                        Request request4_1 = new Request.Builder()
                                .url("http://tltx.eheat.com.cn/waterservice/service.ashx")
                                .post(formbody4_1)
                                .build();
                        Call call4_1 = mOkHttpClient.newCall(request4_1);
                        try {
                            Response response = call4_1.execute();
                            String string = response.body().string();
                            Log.d("1222222222", string);
                           //     parseJsonMulti(string);
                                jiekou4_1Model = JSON.parseObject(string, jiekou4_1_model.class);
                                jiekou4_1_model_list.add(jiekou4_1Model);


                        } catch (Exception e ) {
                            Message message = new Message();
                            message.what = 7;
                            handler.sendEmptyMessage(7);
                            e.printStackTrace();
                        }
                    } else {
                        jiekou4_1_model_list.add(null);
                    }

                }
            //    appli.setJiekou4_1Model(jiekou4_1_model_list);
                System.out.println(appli.getJiekou4_1Model().size());
                e = 1;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloading_data5.setText("下载完毕");
                    }
                });
                Message message1 = new Message();
                message1.what = 6;
                handler.sendEmptyMessage(6);
            }
        }).start();
    }
    //下载接口5中的数据
    public void download_jiekou5_data() {
        int x = appli.getLogin_id_str_list().size() - 1;
        id_login = appli.getLogin_id_str_list().get(x);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient mOkHttpClient = new OkHttpClient();
                int s = appli.getJiekou3_id_str_list().size();
                for (int i = 0; i < s; i++) {
                    String str = appli.getJiekou3_id_str_list().get(i);
                    RequestBody formbody = new FormBody.Builder()
                            .add("method", "PayList")
                            .add("ID", str)
                            .add("UserId", id_login)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://tltx.eheat.com.cn/waterservice/service.ashx")
                            .post(formbody)
                            .build();
                    Call call = mOkHttpClient.newCall(request);
                    try {
                        Response response = call.execute();
                        String string = response.body().string();
                        jiekou5Model = JSON.parseObject(string, jiekou5_model.class);
                        jiekou5_model_list.add(jiekou5Model);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                appli.setJiekou5Model(jiekou5_model_list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        f = 1;
                        downloading_data6.setText("下载完毕");
                    }
                });
                Message message1 = new Message();
                message1.what = 5;
                handler.sendEmptyMessage(5);
            }
        }).start();

    }



    //下载接口6.2里的数据
    public void download_jiekou6_2_data() {
//        final int s = appli.getJiekou2_id_str_list().size();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int s = appli.getJiekou3_id_str_list().size();


                OkHttpClient mOkHttpClient = new OkHttpClient();
                for (int i = 0; i < s; i++) {
                    String str = appli.getJiekou3_id_str_list().get(i);
                    Request.Builder requestBuilder = new Request.Builder().url("http://tltx.eheat.com.cn/waterservice/service.ashx?method=MeterList&UserId=test&ID=" + str + "&time=" + time);
                    requestBuilder.method("GET", null);
                    Request request = requestBuilder.build();
                    Call call = mOkHttpClient.newCall(request);
                    try {
                        Response response = call.execute();
                        jiekou6_2_str = response.body().string();
                        jiekou6_2Model = JSON.parseObject(jiekou6_2_str, jiekou6_2_model.class);
                        jiekou6_2_model_list.add(jiekou6_2Model);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                appli.setJiekou6_2modelList(jiekou6_2_model_list);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        h = 1;

                        downloading_data8.setText("下载完毕");
                    }
                });

            }
        }).start();
    }


    //获取系统时间戳
    public String getTime() {
        long time = System.currentTimeMillis();
        String str = String.valueOf(time);
        return str;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();


    }


    /**
     * 圆形进度条测试..
     */
    public void circle() {
        myDialog = new ProgressDialog(MainActivity.this); // 获取对象
        myDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // 设置样式为圆形样式
        myDialog.setTitle("友情提示"); // 设置进度条的标题信息
        myDialog.setMessage("数据加载中，请稍后..."); // 设置进度条的提示信息
        myDialog.setIcon(android.R.drawable.ic_dialog_info); // 设置进度条的图标
        myDialog.setIndeterminate(false); // 设置进度条是否为不明确
        myDialog.setCancelable(false); // 设置进度条是否按返回键取消

        // 为进度条添加确定按钮 ， 并添加单机事件
        myDialog.setButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                myDialog.cancel(); // 撤销进度条
            }
        });

        myDialog.show(); // 显示进度条
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDialog != null) {
            myDialog.dismiss();
            myDialog = null;
        }
    }

    //解析多个数据的Json
    private void parseJsonMulti(String strResult) {
        try {
            JSONArray jsonObjs = new JSONObject(strResult).getJSONArray("Data");
            String s = "";
            for (int i = 0; i < jsonObjs.length(); i++) {

                appli.setJiekou4_1Model((List<jiekou4_1_model>) jsonObjs);
            }

        } catch (JSONException e) {
            Message message = new Message();
            message.what = 7;
            handler.sendEmptyMessage(7);
            System.out.println("Jsons parse error !");
            e.printStackTrace();
        }
    }
}
