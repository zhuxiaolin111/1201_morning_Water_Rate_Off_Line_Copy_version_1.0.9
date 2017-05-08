package com.northsoft.water_rate_off_line_copy;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UpdateManager {
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 保存解析的XML信息 */
    HashMap<String, String> mHashMap;
    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;

    private Context mContext;
    /* 更新进度条 */
    private ProgressBar mProgress;
    private TextView mTextView;
    private Dialog mDownloadDialog;
    InputStream is;
    int versionCode;
    String versionName;
    boolean flag = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mProgress.setProgress(progress);

                    mTextView.setText("当前进度为:"+progress + "%");
                    break;
                case DOWNLOAD_FINISH:
                     Toast.makeText(mContext,"下载完成", Toast.LENGTH_LONG).show();
                    // 安装文件
                    installApk();
                    break;
                case 3:

                    showNoticeDialog();
                    // Toast.makeText(mContext,R.string.soft_update_no, Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    public UpdateManager(Context context) {
        this.mContext = context;
        // 获取当前软件版本
        versionCode = getVersionCode(mContext);
        versionName = getVersionName(mContext);
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate() {
        if (isUpdate()) {
            // 显示提示对话框
            showNoticeDialog();
        } else {

        }
    }

    /**
     * 检查软件是否有更新版本
     *
     * @return
     */

    private boolean isUpdate() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                final OkHttpClient mOkHttpClient = new OkHttpClient();
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


                Response response = null;
                try {
                    response = call.execute();
                    String s = response.body().string();
                    InputStream is = new ByteArrayInputStream(s.getBytes());
                    //InputStream   is   =   new   ByteArrayInputStream(s.getBytes("UTF-8"));

                    ParseXmlService service = new ParseXmlService();
                    mHashMap = service.parseXml(is);
                    // 把version.xml放到网络上，然后获取文件信息
                    //  InputStream inStream = ParseXmlService.class.getClassLoader().getResourceAsStream("version.xml");
                    // 解析XML文件。 由于XML文件比较小，因此使用DOM方式进行解析

                    if (null != mHashMap) {
                        //  int serviceCode = Integer.valueOf(mHashMap.get("version"));
                        // 版本判断
                       Double serviceCode = Double.valueOf(mHashMap.get("version"));
                        String StrVersionCode = String.valueOf(versionCode);
                       Double StrVersionName = Double.valueOf(versionName);
                        if (serviceCode.compareTo(StrVersionName) >= 0) {
                            flag = true;
                            mHandler.sendEmptyMessage(3);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }).start();


        // 把version.xml放到网络上，然后获取文件信息
        //  InputStream inStream = ParseXmlService.class.getClassLoader().getResourceAsStream("version.xml");
        // 解析XML文件。 由于XML文件比较小，因此使用DOM方式进行解析

        return flag;
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
        packageManager = context.getPackageManager();
        try {
            info = packageManager.getPackageInfo("com.northsoft.water_rate_off_line_copy", 0);
        } catch (NameNotFoundException e) {
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

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private String getVersionName(Context context) {
        String versionName = null;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionName = context.getPackageManager().getPackageInfo("com.northsoft.water_rate_off_line_copy", 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog() {
        // 构造对话框

        Builder builder = new Builder(mContext);
        builder.setTitle(R.string.soft_update_title);
        builder.setMessage(R.string.soft_update_info);
        // 更新
        builder.setPositiveButton(R.string.soft_update_updatebtn, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 显示下载对话框
                showDownloadDialog();
            }
        });
        // 稍后更新
        builder.setNegativeButton(R.string.soft_update_later, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();

        noticeDialog.show();
    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog() {

        // 构造软件下载对话框
        Builder builder = new Builder(mContext);
        //builder.setTitle(R.string.soft_updating);
        // 给下载对话框增加进度条
        builder.setTitle("提示");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("正在更新中......");
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        mTextView= (TextView) v.findViewById(R.id.textview);
        builder.setView(v);
        // 取消更新
        builder.setNegativeButton(R.string.soft_update_cancel, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 设置取消状态
                cancelUpdate = true;
            }
        });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        // 现在文件
        downloadApk();
    }



    /**
     * 下载apk文件
     */
    private void downloadApk() {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    /**
     * 下载文件线程
     *
     * @author coolszy
     * @date 2012-4-26
     * @blog http://blog.92coding.com
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "download";
                   URL url = new URL(mHashMap.get("url"));
                 //   URL url = new URL("http://app.xiaoxinxh.com/99danji/20/");
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, mHashMap.get("name"));
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024 * 2];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);

                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    }

    ;

    /**
     * 安装APK文件
     */
    private void installApk() {
        File apkfile = new File(mSavePath, mHashMap.get("name"));
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
