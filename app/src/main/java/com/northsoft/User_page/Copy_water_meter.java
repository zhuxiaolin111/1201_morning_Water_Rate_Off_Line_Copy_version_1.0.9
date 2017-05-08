package com.northsoft.User_page;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.northsoft.Share_MyApplication.MyApplication;
import com.northsoft.model.jiekou2_model;
import com.northsoft.model.tijiao_model;
import com.northsoft.order.DBManager;
import com.northsoft.water_rate_off_line_copy.First_Page;
import com.northsoft.water_rate_off_line_copy.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chensiqi on 2016/11/28.
 */

public class Copy_water_meter extends AppCompatActivity implements View.OnClickListener {

    private MyApplication application;
    jiekou2_model mJiekou2_model;
    private String yonghubianhao;
    TextView baihao, weizhi, qishu, shuiliang, zhongshuiliang, riqi, jine, kegou;
    TextView baihao1, weizhi1, qishu1, shuiliang1;
    TextView baihao2, weizhi2, qishu2, shuiliang2;
    EditText zhishu, zhishu1, zhishu2;
    Button baocun;
    // 获得文本框中的用户
    String str_zhishu = null;
    String str_zhishu1 = null;
    String str_zhishu2 = null;
    String MeterID_1 = null;
    String MeterID_2 = null;
    String MeterID_3 = null;
    int size = 0;

    int i;

    ArrayList<jiekou2_model> mArrayList = new ArrayList<>();
    //SQLite数据库管理类的实体化
    private DBManager mgr;
    private Cursor cursor;

    //数据库止数显示；
    private String[] zhishuSQLite;
    //
    Integer zhishu_double;
    Integer qishu_double;
    Integer zhishu_double1;
    Integer qishu_double1;
    Integer zhishu_double2;
    Integer qishu_double2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copy_water_mater_page);
        mgr = new DBManager(this);
        initView();
        application = (MyApplication) getApplication();
        mJiekou2_model = new jiekou2_model();
        Intent intent = getIntent();
        yonghubianhao = intent.getStringExtra("yonghubianhao");
        zhishuSQLite = new String[3];
        zhishuSQLite = searthSQLite();

        int x = zhishuSQLite.length;
        int s = application.getJiekou3_id_str_list().size();
        int ss = application.getJiekou6_2modelList().size();
        if (s == ss) {
            for (i = 0; i < s; i++) {
                if (yonghubianhao.equals(application.getJiekou3_id_str_list().get(i))) {

                    size = application.getJiekou6_2modelList().get(i).getData().size();
                    break;
                }
            }

            if (!application.getJiekou6_2modelList().get(i).getData().equals("")) {
                //三个水表
                if (size == 3) {

                    baihao.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getMeterID() + "");

                    String str_weizhi = (String) application.getJiekou6_2modelList().get(i).getData().get(0).getLocation();
                    if (str_weizhi == null) {
                        weizhi.setText("");
                        weizhi1.setText("");
                        weizhi2.setText("");
                    } else {
                        weizhi.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getLocation() + "");
                        weizhi1.setText(application.getJiekou6_2modelList().get(i).getData().get(1).getLocation() + "");
                        weizhi2.setText(application.getJiekou6_2modelList().get(i).getData().get(2).getLocation() + "");
                    }
                    qishu.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart() + "");


                    String Iszhishu = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd());
                    //判断app 水表止数是否有数据
                    if (!Iszhishu.equals("null")) {
                        zhishu.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd() + "");
                        zhishu1.setText(application.getJiekou6_2modelList().get(i).getData().get(1).getNumEnd() + "");
                        zhishu2.setText(application.getJiekou6_2modelList().get(i).getData().get(2).getNumEnd() + "");
                        zhishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd()));
                        qishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart()));
                        zhishu_double1 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumEnd()));
                        qishu_double1 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart()));
                        zhishu_double2 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(2).getNumEnd()));
                        qishu_double2 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(2).getNumStart()));
                        shuiliang.setText(sub(zhishu_double, qishu_double) + "");
                        shuiliang1.setText(sub(zhishu_double1, qishu_double1) + "");
                        shuiliang2.setText(sub(zhishu_double2, qishu_double2) + "");
                        zhongshuiliang.setText(add(sub(zhishu_double2, qishu_double2), add(sub(zhishu_double, qishu_double), sub(zhishu_double1, qishu_double1))) + "");
                        //判断数据保存记录但没有上传时，是否有记录；
                    } else if (!zhishuSQLite.equals(null) || !zhishuSQLite[0].equals("null")) {
                        Integer d0 = Integer.valueOf(zhishuSQLite[0]);
                        Integer d1 = Integer.valueOf(zhishuSQLite[1]);
                        Integer d2 = Integer.valueOf(zhishuSQLite[2]);
                        qishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart()));
                        qishu_double1 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart()));
                        qishu_double2 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(2).getNumStart()));
                        zhishu.setText(zhishuSQLite[0]);
                        zhishu1.setText(zhishuSQLite[1]);
                        zhishu2.setText(zhishuSQLite[2]);
                        shuiliang.setText(sub(d0, qishu_double)+ "");
                        shuiliang1.setText(sub(d1, qishu_double1) + "");
                        shuiliang2.setText(sub(d2, qishu_double2) + "");
                        zhongshuiliang.setText(add(sub(d2, qishu_double2), add(sub(d0, qishu_double), sub(d1, qishu_double1)))+ "");
                    } else {

                        shuiliang.setText("0");
                        shuiliang1.setText("0");
                        shuiliang2.setText("0");
                        zhongshuiliang.setText("0");
                    }
                    baihao1.setText(application.getJiekou6_2modelList().get(i).getData().get(1).getMeterID() + "");
                    qishu1.setText(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart() + "");

                    baihao2.setText(application.getJiekou6_2modelList().get(i).getData().get(2).getMeterID() + "");
                    qishu2.setText(application.getJiekou6_2modelList().get(i).getData().get(2).getNumStart() + "");

                    riqi.setText(First_Page.stampToDate(application.getJiekou2_Model_list().get(0).getData().get(i).getLastcheckmeterdate() + ""));

                    Double d1 = application.getJiekou3Model().get(i).getData().get(0).getLastfee();
                    Double d2 = application.getJiekou6_2modelList().get(i).getData().get(0).getPrice_total();
                    jine.setText(application.getJiekou3Model().get(i).getData().get(0).getLastfee() + "");
                    kegou.setText(div(d1, d2, 1) + "");
                }


                //两个水表
                else if (size == 2) {
                    String str_weizhi = (String) application.getJiekou6_2modelList().get(i).getData().get(0).getLocation();

                    if (str_weizhi == null) {
                        weizhi.setText("");
                        weizhi1.setText("");

                    } else {
                        weizhi.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getLocation() + "");
                        weizhi1.setText(application.getJiekou6_2modelList().get(i).getData().get(1).getLocation() + "");
                    }
                    baihao.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getMeterID() + "");
                    qishu.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart() + "");

                    String str = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd());
                    if (!str.equals("null")) {
                        zhishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd()));
                        qishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart()));
                        zhishu_double1 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumEnd()));
                        qishu_double1 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart()));
                        zhishu.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd() + "");
                        zhishu1.setText(application.getJiekou6_2modelList().get(i).getData().get(1).getNumEnd() + "");
                        shuiliang.setText(sub(zhishu_double, qishu_double)+"");
                        shuiliang1.setText(sub(zhishu_double1, qishu_double1) + "");
                        zhongshuiliang.setText(add(sub(zhishu_double, qishu_double), sub(zhishu_double1, qishu_double1)) + "");
                    } else if (!zhishuSQLite[0].equals("null")) {
                        Integer d1 = Integer.valueOf(zhishuSQLite[0]);
                        Integer d2 = Integer.valueOf(zhishuSQLite[1]);
                        zhishu.setText(zhishuSQLite[0]);
                        zhishu1.setText(zhishuSQLite[1]);

                        qishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart()));

                        qishu_double1 = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart()));
                        shuiliang.setText(sub(d1, qishu_double) + "");
                        shuiliang1.setText(sub(d2, qishu_double1) + "");
                        zhongshuiliang.setText(add(sub(d1, qishu_double), sub(d2, qishu_double1)) + "");
                    } else {
                        shuiliang.setText("0");
                        shuiliang1.setText("0");
                        zhongshuiliang.setText("0");
                    }
                    baihao1.setText(application.getJiekou6_2modelList().get(i).getData().get(1).getMeterID() + "");

                    qishu1.setText(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart() + "");
                    riqi.setText(First_Page.stampToDate(application.getJiekou2_Model_list().get(0).getData().get(i).getLastcheckmeterdate() + ""));
                    Double d1 = application.getJiekou3Model().get(i).getData().get(0).getLastfee();
                    Double d2 = application.getJiekou6_2modelList().get(i).getData().get(0).getPrice_total();
                    jine.setText(application.getJiekou3Model().get(i).getData().get(0).getLastfee() + "");
                    kegou.setText(div(d1, d2, 1) + "");
                }


                //一个水表
                else if (size == 1) {

                    String str_weizhi = (String) application.getJiekou6_2modelList().get(i).getData().get(0).getLocation();
                    if (str_weizhi == null) {
                        weizhi.setText("");
                    } else {
                        weizhi.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getLocation() + "");
                    }
                    baihao.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getMeterID() + "");

                    qishu.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart() + "");

                    String str = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd());
                    //判断APP 水表有没止数
                    if (!"null".equals(str)) {
                        Integer zhishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd()));
                        Integer qishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart()));
                        zhishu.setText(application.getJiekou6_2modelList().get(i).getData().get(0).getNumEnd() + "");
                        shuiliang.setText(zhishu_double-qishu_double+ "");
                        zhongshuiliang.setText(zhishu_double-qishu_double + "");
                        //判断数据库里有没有止数；

                    } else if (!zhishuSQLite[0].equals("null")) {
                        Integer d1 = Integer.valueOf(zhishuSQLite[0]);
                        zhishu.setText(zhishuSQLite[0]);
                        Integer qishu_double = Integer.valueOf(String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart()));
                        shuiliang.setText(sub(d1, qishu_double) + "");
                        zhongshuiliang.setText(sub(d1, qishu_double) + "");

                    } else {
                        shuiliang.setText("0");
                        zhongshuiliang.setText("0");
                    }
                    riqi.setText(First_Page.stampToDate(application.getJiekou2_Model_list().get(0).getData().get(i).getLastcheckmeterdate() + ""));
                    Double d1 = application.getJiekou3Model().get(i).getData().get(0).getLastfee();
                    Double d2 = application.getJiekou6_2modelList().get(i).getData().get(0).getPrice_total();
                    jine.setText(application.getJiekou3Model().get(i).getData().get(0).getLastfee() + "");
                    kegou.setText(div(d1, d2, 1) + "");
                }
                //不太可能发生
                else {
                    baihao.setText("无数据");
                    weizhi.setText("");
                    qishu.setText("无数据");
                    shuiliang.setText("无数据");
                    baihao1.setText("无数据");
                    weizhi1.setText("无数据");
                    qishu1.setText("无数据");
                    shuiliang1.setText("无数据");
                    zhongshuiliang.setText("无数据");
                    baihao2.setText("无数据");
                    weizhi2.setText("");
                    qishu2.setText("无数据");
                    shuiliang2.setText("无数据");
                    zhongshuiliang.setText("无数据");
                    riqi.setText("无数据");
                    jine.setText("无数据");
                    kegou.setText("无数据");
                }

            }
        } else {
            Toast.makeText(application, "数据下载错误，请重新下载", Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {
        baocun = (Button) findViewById(R.id.id_baocun1);
        baocun.setOnClickListener(this);
        baihao = (TextView) findViewById(R.id.id_biaohao);
        weizhi = (TextView) findViewById(R.id.id_weizhi);
        qishu = (TextView) findViewById(R.id.id_qishu);
        shuiliang = (TextView) findViewById(R.id.id_shuliang);
        baihao1 = (TextView) findViewById(R.id.id_biaohao1);
        weizhi1 = (TextView) findViewById(R.id.id_weizhi1);
        qishu1 = (TextView) findViewById(R.id.id_qishu1);
        shuiliang1 = (TextView) findViewById(R.id.id_shuliang1);
        baihao2 = (TextView) findViewById(R.id.id_biaohao2);
        weizhi2 = (TextView) findViewById(R.id.id_weizhi2);
        qishu2 = (TextView) findViewById(R.id.id_qishu2);
        shuiliang2 = (TextView) findViewById(R.id.id_shuliang2);
        zhongshuiliang = (TextView) findViewById(R.id.id_zhongshuiliang);
        riqi = (TextView) findViewById(R.id.id_riqi);
        jine = (TextView) findViewById(R.id.id_jine);
        kegou = (TextView) findViewById(R.id.id_kegou);
        zhishu = (EditText) findViewById(R.id.id_zhishu);
        zhishu1 = (EditText) findViewById(R.id.id_zhishu1);
        zhishu2 = (EditText) findViewById(R.id.id_zhishu2);

        // 监听文本框内容变化
        zhishu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 获得文本框中的用户
                str_zhishu = zhishu.getText().toString().trim();
                int size = application.getJiekou6_2modelList().get(i).getData().size();
                if (size == 1) {

                    new Thread() {

                        public void run() {
                            //这儿是耗时操作，完成之后更新UI；
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    //更新UI
                                    if (!str_zhishu.equals("")) {
                                        Integer d1 = Integer.valueOf(str_zhishu);
                                        Integer d2 = Integer.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart());


                                        if (d1.compareTo(d2) > 0) {
                                            zhongshuiliang.setText(sub(d1, d2) + "");
                                            shuiliang.setText(sub(d1, d2) + "");
                                        } else {
                                            zhongshuiliang.setText("0");

                                        }
                                    } else {
                                        zhongshuiliang.setText("0");
                                    }
                                }

                            });
                        }
                    }.start();
                }

            }


            @Override
            public void afterTextChanged(Editable editable) {
            }

        });
        // 监听文本框内容变化
        zhishu1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 获得文本框中的用户
                str_zhishu1 = zhishu1.getText().toString().trim();
                str_zhishu = zhishu.getText().toString().trim();
                int size = application.getJiekou6_2modelList().get(i).getData().size();
                if (size == 2) {
                    new Thread() {
                        public void run() {
                            //这儿是耗时操作，完成之后更新UI；
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    //更新UI
                                    if (!str_zhishu.equals("") && !str_zhishu1.equals("")) {
                                        Integer d1 = Integer.valueOf(str_zhishu);
                                        Integer d2 = Integer.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart());
                                        Integer d3 = Integer.valueOf(str_zhishu1);
                                        Integer d4 = Integer.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart());
                                        Integer add_d1_d3 = add(d1, d3);
                                        Integer add_d2_d4 = add(d2, d4);
                                        if (add_d1_d3.compareTo(add_d2_d4) > 0) {
                                            shuiliang.setText(sub(d1, d2)+ "");
                                            shuiliang1.setText(sub(d3, d4)+ "");
                                            zhongshuiliang.setText(sub(add_d1_d3, add_d2_d4) + "");
                                        } else {
                                            zhongshuiliang.setText("0");
                                        }
                                    } else {
                                        zhongshuiliang.setText("0");
                                    }
                                }

                            });
                        }
                    }.start();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        });
        // 监听文本框内容变化
        zhishu2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 获得文本框中的用户
                str_zhishu = zhishu.getText().toString().trim();
                // 获得文本框中的用户
                str_zhishu1 = zhishu1.getText().toString().trim();
                // 获得文本框中的用户
                str_zhishu2 = zhishu2.getText().toString().trim();
                int size = application.getJiekou6_2modelList().get(i).getData().size();
                if (size == 3) {
                    new Thread() {
                        public void run() {
                            //这儿是耗时操作，完成之后更新UI；
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    //更新UI
                                    if (!str_zhishu.equals("") && !str_zhishu1.equals("") && !str_zhishu2.equals("")) {

                                        Integer d1 = Integer.valueOf(str_zhishu);
                                        Integer d2 = Integer.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart());
                                        Integer d3 = Integer.valueOf(str_zhishu1);
                                        Integer d4 = Integer.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart());
                                        Integer d5 = Integer.valueOf(str_zhishu2);
                                        Integer d6 = Integer.valueOf(application.getJiekou6_2modelList().get(i).getData().get(2).getNumStart());
                                        Integer add_d1_d3 = add(d1, d3);
                                        Integer add_d1_d3_d5 = add(add_d1_d3, d5);
                                        Integer add_d2_d4 = add(d2, d4);
                                        Integer add_d2_d4_d6 = add(add_d2_d4, d6);

                                        if (add_d1_d3_d5.compareTo(add_d2_d4_d6) > 0) {
                                            shuiliang.setText(sub(d1, d2) + "");
                                            shuiliang1.setText(sub(d3, d4)+ "");
                                            shuiliang2.setText(sub(d5, d6) + "");
                                            zhongshuiliang.setText(sub(add_d1_d3_d5, add_d2_d4_d6) + "");
                                        } else {
                                            zhongshuiliang.setText("0");
                                        }
                                    } else {
                                        zhongshuiliang.setText("0");
                                    }
                                }

                            });
                        }
                    }.start();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        });
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static int add(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return v1+v2;
        //return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static int sub(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return v1-v2;
      //  return b1.subtract(b2).doubleValue();
    }

  /*  public static double round(double d,
                               int len) {     // 进行四舍五入
        //  操作
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，
        //  表示进行四舍五入的操作
        return b1.divide(b2, len, BigDecimal.
                ROUND_HALF_UP).doubleValue();
    }
*/
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_baocun1:
                switch (size) {
                    case 1:
                        if (!str_zhishu.equals("")) {
                            //保存按钮
                            if (isFlagzhishu()) {
                                initSQLite();
                                new AlertDialog.Builder(Copy_water_meter.this)
                                        .setTitle("温馨提示")
                                        .setIcon(android.R.drawable.ic_dialog_info)
                                        .setMessage("数据保存成功，请尽快提交")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //销毁页面
                                                Copy_water_meter.this.finish();
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(application, "水表止数必须大于水表起数", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Copy_water_meter.this, "止数不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (!str_zhishu.equals("") && !str_zhishu1.equals("")) {
                            //保存按钮
                            if (isFlagzhishu()) {
                                initSQLite();
                                new AlertDialog.Builder(Copy_water_meter.this)
                                        .setTitle("温馨提示")
                                        .setIcon(android.R.drawable.ic_dialog_info)
                                        .setMessage("数据保存成功，请尽快提交")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //销毁页面
                                                Copy_water_meter.this.finish();
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(application, "水表止数必须大于水表起数", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Copy_water_meter.this, "水表止数不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        if (!str_zhishu.equals("") && !str_zhishu1.equals("") && !str_zhishu2.equals("")) {
                            //保存按钮
                            if (isFlagzhishu()) {
                                initSQLite();
                                new AlertDialog.Builder(Copy_water_meter.this)
                                        .setTitle("温馨提示")
                                        .setIcon(android.R.drawable.ic_dialog_info)
                                        .setMessage("数据保存成功，请尽快提交")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //销毁页面
                                                Copy_water_meter.this.finish();
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(application, "水表止数必须大于水表起数", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Copy_water_meter.this, "水表止数不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }


                break;
        }
    }

    //查询数据库里已抄表记录；
    public String[] searthSQLite() {
        String[] strings = new String[]{"null", " null", "null"};
        Cursor c = mgr.search(yonghubianhao, "fangjianbianhao");
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                strings[0] = c.getString(c.getColumnIndex("NumEnd_1"));
                strings[1] = c.getString(c.getColumnIndex("NumEnd_2"));
                strings[2] = c.getString(c.getColumnIndex("NumEnd_3"));
            }
            return strings;
        } else {
            return strings;
        }

    }

    //这个方法是点击保存按钮的时候调用的
    public void initSQLite() {//首先查询一下这个数据有没有存在数据库
        //根据RoomID查询
        Cursor c = mgr.search(yonghubianhao, "fangjianbianhao");
        while (c.moveToNext()) {
            if (!"".equals(c.getString(c.getColumnIndex("fangjianbianhao")))) {
                //删除这行
                tijiao_model tijiaoModel = new tijiao_model();
                tijiaoModel.fangjianbianhao = yonghubianhao;
                mgr.deleteOldPerson(tijiaoModel);
            }
        }


        //将数据写入数据库
        if (size == 1) {
            MeterID_1 = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getMeterID());
        } else if (size == 2) {
            MeterID_1 = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getMeterID());
            MeterID_2 = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getMeterID());
        } else if (size == 3) {
            MeterID_1 = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getMeterID());
            MeterID_2 = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getMeterID());
            MeterID_3 = String.valueOf(application.getJiekou6_2modelList().get(i).getData().get(2).getMeterID());
        }

        // String userid=null;
        String userid = application.getLogin_id_str_list().get(application.getLogin_id_str_list().size() - 1);
        ArrayList<tijiao_model> tijiaoModelArrayList = new ArrayList<>();
        tijiao_model tijiaoModel = new tijiao_model(
                yonghubianhao,
                userid,
                MeterID_1,
                MeterID_2,
                MeterID_3,
                str_zhishu,
                str_zhishu1,
                str_zhishu2,
                "no");
        tijiaoModelArrayList.add(tijiaoModel);
        mgr.add(tijiaoModelArrayList);

        List<tijiao_model> list = mgr.query();
        mJiekou2_model = application.getJiekou2_Model_list().get(0);
        mJiekou2_model.getData().get(i).setIsread(1);
        mArrayList.add(mJiekou2_model);
        application.setJiekou2_Model_list(mArrayList);
//        Toast.makeText(User_Info.this,list.get(0).Type,Toast.LENGTH_LONG).show();

    }

    public boolean isFlagzhishu() {
        Double d1;
        Double d2;
        Double d3;
        Double d4;
        Double d5;
        Double d6;
        int size = application.getJiekou6_2modelList().get(i).getData().size();
        switch (size) {
            case 1:
                d1 = Double.valueOf(str_zhishu);
                d2 = Double.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart());
                if (d1.compareTo(d2) > 0) {
                    return true;

                } else {
                    return false;
                }

            case 2:
                d1 = Double.valueOf(str_zhishu);
                d2 = Double.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart());
                d3 = Double.valueOf(str_zhishu1);
                d4 = Double.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart());
                if (d1.compareTo(d2) > 0 && d3.compareTo(d4) > 0) {
                    return true;
                } else {
                    return false;
                }

            case 3:
                d1 = Double.valueOf(str_zhishu);
                d2 = Double.valueOf(application.getJiekou6_2modelList().get(i).getData().get(0).getNumStart());
                d3 = Double.valueOf(str_zhishu1);
                d4 = Double.valueOf(application.getJiekou6_2modelList().get(i).getData().get(1).getNumStart());
                d5 = Double.valueOf(str_zhishu2);
                d6 = Double.valueOf(application.getJiekou6_2modelList().get(i).getData().get(2).getNumStart());
                if (d1.compareTo(d2) > 0 && d3.compareTo(d4) > 0 && d5.compareTo(d6) > 0) {
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mgr.closeDB();
    }
}
