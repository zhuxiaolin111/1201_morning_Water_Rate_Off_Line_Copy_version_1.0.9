package com.northsoft.water_rate_off_line_copy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.model.jiekou1_model;
import com.northsoft.model.loginUserID_model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 登录界面Demo
 *
 * @author ZHY
 */
public class loginActivity extends AppCompatActivity implements OnClickListener {

    private EditText username, password;
    private Button bt_username_clear;
    private Button bt_pwd_clear;
    private Button forgive_pwd;
    private Button bt_pwd_eye;
    private Button login;
    private Button register;
    private boolean isOpen = false;

    private ProgressDialog myDialog;
    //存储接口1数据的list
    private List<jiekou1_model> list;
    private List<loginUserID_model> mLoginUserID_modelList;
    private String userid_str;
    private String jiekou1_str = null;
    //定义时间戳
    private String time = getTime();

    private MyApplication appli;
    //获取IMEI
    TelephonyManager tm;
    String imei;
    // 获得文本框中的用户
    String tel;
    //装在这首页所有表簿编号的list
    private ArrayList<String> first_page_id_list;
    private ArrayList<String> loginUserID_list;
    Context context;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                download_jiekou1_data();
            } else if (msg.what == 2) {
                myDialog.dismiss();
                Toast.makeText(loginActivity.this, "请使用抄表员手机进行登录或者公司没有初始化数据", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3) {
                int i = appli.getLogin_id_str_list().size();
                if (!appli.getLogin_id_str_list().get(i - 1).equals("") && !appli.getJiekou1_model_list().equals("")) {
                    myDialog.dismiss();
                    Intent intent = new Intent(loginActivity.this, First_Page.class);
                    startActivity(intent);
                    loginActivity.this.finish();
                } else {
                    myDialog.dismiss();
                    Toast.makeText(loginActivity.this, "无此用户，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            } else if (msg.what == 4) {
                myDialog.dismiss();
                Toast.makeText(loginActivity.this, "无此用户，请重新输入！", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.login_layout);
        //  int i=getVersionCode(context);
        //  login1();
        initView();
        context = this.getApplication();
        UpdateManager updateManager = new UpdateManager(context);
        updateManager.checkUpdate();
        tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
          /*
      * 唯一的设备ID：
      * GSM手机的 IMEI 和 CDMA手机的 MEID.
      * Return null if device ID is not available.
      */
        imei = tm.getDeviceId();//String
        //初始化接收接口1里id的list
        first_page_id_list = new ArrayList<>();
        //初始化Myapplication对象
        loginUserID_list = new ArrayList<>();
        appli = (MyApplication) getApplication();
        tel = new String();
        jiekou1_str = new String();

    }

    private void initView() {

        username = (EditText) findViewById(R.id.username);

        // 监听文本框内容变化
        username.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 获得文本框中的用户
                tel = username.getText().toString().trim();
                //  Toast.makeText(loginActivity.this, tel, Toast.LENGTH_SHORT).show();
                if ("".equals(tel)) {
                    // 用户名为空,设置按钮不可见
                    bt_username_clear.setVisibility(View.INVISIBLE);
                } else {
                    // 用户名不为空，设置按钮可见
                    bt_username_clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 获得文本框中的用户
                tel = username.getText().toString().trim();
                //  Toast.makeText(loginActivity.this, tel, Toast.LENGTH_SHORT).show();
                if ("".equals(tel)) {
                    // 用户名为空,设置按钮不可见
                    bt_username_clear.setVisibility(View.INVISIBLE);
                } else {
                    // 用户名不为空，设置按钮可见
                    bt_username_clear.setVisibility(View.VISIBLE);
                }
            }
        });
        bt_username_clear = (Button) findViewById(R.id.bt_username_clear);
        bt_username_clear.setOnClickListener(this);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_username_clear:
                // 清除登录名
                username.setText("");
                break;

            case R.id.login:
                // TODO 登录按钮
                if (!tel.equals("")) {
                    circle();
                    login();
                } else {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
                  /*  Intent intent = new Intent(loginActivity.this, First_Page.class);
                    startActivity(intent);*/
                break;


            default:
                break;
        }
    }


    //下载接口1里的数据
    private void download_jiekou1_data() {
        int i = loginUserID_list.size() - 1;
        final String userid = loginUserID_list.get(i);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //    测试URL
                // Request.Builder requestBuilder = new Request.Builder().url("http://sylm.eheat.com.cn/waterservice/service.ashx?method=BookList&OperatorNo=0104&UserID=zxl&time=" + time);
                //  正式
                Request.Builder requestBuilder = new Request.Builder().url("http://tltx.eheat.com.cn/waterservice/service.ashx?method=BookList&UserID=" + userid + "&time=" + time);
                requestBuilder.method("GET", null);
                Request request = requestBuilder.build();
                Call call = mOkHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        jiekou1_str = response.body().string();
                        if (!jiekou1_str.equals("null")) {
                            list = new ArrayList<>(JSON.parseArray(jiekou1_str, jiekou1_model.class));

                            int x = list.size();
                            for (int i = 0; i < x; i++) {
                                String str = list.get(i).getId();
                                first_page_id_list.add(str);//有了接口1里所有的id才能请求接口2
                            }
                            appli.setJiekou1_model_list(list);
                            appli.setJiekou2_id_str_list(first_page_id_list);
                            Message message = new Message();
                            message.what = 3;
                            handler.sendEmptyMessage(3);
                        } else {
                            Message message = new Message();
                            message.what = 2;
                            handler.sendEmptyMessage(2);
                        }
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

    public void login() {

        final OkHttpClient mOkHttpClient = new OkHttpClient();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formbody = new FormBody.Builder()
                        .add("method", "getuserid")
                        .add("isweixin", "1")
                        .add("mobile", tel)
                        .add("imei", imei)
                        .build();
                Request request = new Request.Builder()
                        //.url("http://syhf.eheat.com.cn/weixinservice.ashx")
                        //正式
                        .url("http://tltx.eheat.com.cn/weixinservice.ashx")
                        .post(formbody)
                        .build();
                okhttp3.Call call = mOkHttpClient.newCall(request);
                try {
                    Response response = call.execute();

                    userid_str = response.body().string();

                    try {
                        JSONObject jsonObject2 = new JSONObject(userid_str);
                        loginUserID_list.add(jsonObject2.getString("userid"));
                        appli.setLogin_id_str_list(loginUserID_list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Message message = new Message();
                message.what = 1;
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    /**
     * 圆形进度条测试..
     */
    public void circle() {
        myDialog = new ProgressDialog(loginActivity.this); // 获取对象
        myDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // 设置样式为圆形样式
        myDialog.setTitle("友情提示"); // 设置进度条的标题信息
        myDialog.setMessage("查找用户，请稍后..."); // 设置进度条的提示信息
        myDialog.setIcon(android.R.drawable.ic_dialog_info); // 设置进度条的图标
        myDialog.setIndeterminate(false); // 设置进度条是否为不明确
        myDialog.setCancelable(true); // 设置进度条是否按返回键取消
        myDialog.show(); // 显示进度条
    }

    public void login1() {

        final OkHttpClient mOkHttpClient = new OkHttpClient();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formbody = new FormBody.Builder()
                        .add("method", "getappversion")
                        .build();
                Request request = new Request.Builder()
                        //.url("http://syhf.eheat.com.cn/weixinservice.ashx")
                        //正式
                        .url("http://tltx.eheat.com.cn/handler/tltxwaterservice.ashx")
                        .post(formbody)
                        .build();
                okhttp3.Call call = mOkHttpClient.newCall(request);
                try {
                    Response response = call.execute();

                    userid_str = response.body().string();

                    try {
                        JSONObject jsonObject2 = new JSONObject(userid_str);
                        loginUserID_list.add(jsonObject2.getString("userid"));
                        appli.setLogin_id_str_list(loginUserID_list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Message message = new Message();
                message.what = 1;
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        PackageManager packageManager;
        PackageInfo info = null;
        packageManager = this.getPackageManager();
        try {
            info = packageManager.getPackageInfo("com.northsoft.water_rate_off_line_copy", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = info.versionCode;
    /*    try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().getPackageInfo("com.northsoft.water_rate_off_line_copy", 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }*/
        return versionCode;
    }
}
