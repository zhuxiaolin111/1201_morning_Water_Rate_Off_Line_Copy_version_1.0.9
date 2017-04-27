package com.northsoft.order;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.northsoft.model.tijiao_model;
import com.northsoft.model.user_change_info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensiqi on 2016/11/7.
 * 通过这个方法来封装所有的数据库操作方法
 */

public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //getWritableDatabase内部调用了openorcreateDatabase()方法
        db = helper.getWritableDatabase();
    }

    public void add(List<tijiao_model> tijiao_modelList) {
        //开始事务
        db.beginTransaction();
        try {
            for (tijiao_model tijiaoModel : tijiao_modelList) {
                db.execSQL("insert into chaobiao values(null , ? ,? ,? ,? ,? ,?, ?, ?, ?)",
                        new String[]{
                                String.valueOf(tijiaoModel.fangjianbianhao),
                                String.valueOf(tijiaoModel.userid),
                                String.valueOf(tijiaoModel.MeterID_1),
                                String.valueOf(tijiaoModel.MeterID_2),
                                String.valueOf(tijiaoModel.MeterID_3),
                                String.valueOf(tijiaoModel.NumEnd_1),
                                String.valueOf(tijiaoModel.NumEnd_2),
                                String.valueOf(tijiaoModel.NumEnd_3),
                                String.valueOf(tijiaoModel.shifushangchuang)
                        });
                //设置事务成功完成
                db.setTransactionSuccessful();
            }
        } catch (SQLiteException se) {
            se.printStackTrace();
        } finally {
            //结束事物
            db.endTransaction();
        }
    }

    public void add_userChangeInfo(List<user_change_info> user_change_infoList) {
        //开始事务
        db.beginTransaction();
        try {
            for (user_change_info userChangeInfo : user_change_infoList) {
                db.execSQL("insert into userinfo values(null , ? ,? ,? ,? ,? )",
                        new String[]{
                                String.valueOf(userChangeInfo.yonghubianhao),
                                String.valueOf(userChangeInfo.phone),
                                String.valueOf(userChangeInfo.Census),
                                String.valueOf(userChangeInfo.userid),
                                String.valueOf(userChangeInfo.shifushangchuan)
                        });
                //设置事务成功完成
                db.setTransactionSuccessful();
            }
        } catch (SQLiteException se) {
            se.printStackTrace();
        } finally {
            //结束事物
            db.endTransaction();
        }
    }

    //将数据库里的所有数据放入一个Curse中,以便后续的数据使用
    public List<tijiao_model> query() {
        ArrayList<tijiao_model> chaobiao_models = new ArrayList<>();
        Cursor c = queryTheCursor();

        while (c.moveToNext()) {
            tijiao_model tijiaoModel = new tijiao_model();

            tijiaoModel.id = c.getInt(c.getColumnIndex("_id"));
            tijiaoModel.fangjianbianhao = c.getString(c.getColumnIndex("fangjianbianhao"));
            tijiaoModel.userid = c.getString(c.getColumnIndex("userid"));
            tijiaoModel.MeterID_1 = c.getString(c.getColumnIndex("MeterID_1"));
            tijiaoModel.MeterID_2 = c.getString(c.getColumnIndex("MeterID_2"));
            tijiaoModel.MeterID_3 = c.getString(c.getColumnIndex("MeterID_3"));
            tijiaoModel.NumEnd_1 = c.getString(c.getColumnIndex("NumEnd_1"));
            tijiaoModel.NumEnd_2 = c.getString(c.getColumnIndex("NumEnd_2"));
            tijiaoModel.NumEnd_3 = c.getString(c.getColumnIndex("NumEnd_3"));
            chaobiao_models.add(tijiaoModel);
        }
        c.close();
        return chaobiao_models;
    }

    //将数据库里的所有数据放入一个Curse中,以便后续的数据使用
    public List<user_change_info> query1() {
        ArrayList<user_change_info> userChangeInfoArrayList = new ArrayList<>();
        Cursor c = queryTheCursor1();

        while (c.moveToNext()) {
            user_change_info userChangeInfo = new user_change_info();

            userChangeInfo.id = c.getInt(c.getColumnIndex("_id"));
            userChangeInfo.yonghubianhao = c.getString(c.getColumnIndex("yonghubianhao"));
            userChangeInfo.phone = c.getString(c.getColumnIndex("phone"));
            userChangeInfo.Census = c.getString(c.getColumnIndex("Census"));
            userChangeInfo.userid = c.getString(c.getColumnIndex("userid"));
            userChangeInfo.shifushangchuan = c.getString(c.getColumnIndex("shifoushangchuan"));
            userChangeInfoArrayList.add(userChangeInfo);
        }
        c.close();
        return userChangeInfoArrayList;
    }

    //返回一个Curse对象
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("select * from chaobiao", null);
        return c;
    }

    //返回一个Curse对象
    public Cursor queryTheCursor1() {
        Cursor c = db.rawQuery("select * from userinfo", null);
        return c;
    }

    //搜索数据库方法
    public Cursor search(String string, String lieming) {
        String sql = "select * from chaobiao where " + lieming + " = ?";
        Cursor c = db.rawQuery(sql, new String[]{string});
        return c;
    }

    //搜索数据库方法
    public Cursor search1(String string, String lieming) {
        String sql = "select * from userinfo where " + lieming + " = ?";
        Cursor c = db.rawQuery(sql, new String[]{string});
        return c;
    }

    //删除行
    public void deleteOldPerson(tijiao_model tijiaoModel) {
        db.delete("chaobiao", "fangjianbianhao = ?", new String[]{String.valueOf(tijiaoModel.fangjianbianhao)});
    }

    //删除行
    public void deleteOldPerson1(user_change_info userChangeInfo) {
        db.delete("userinfo", "yonghubianhao = ?", new String[]{String.valueOf(userChangeInfo.yonghubianhao)});
    }

    //更改列数据
    public void update() {
        db.execSQL("update chaobiao set shifushangchuan = 'yes' where shifoujicha = 'isflag'");
    }


    //修改成功上传数据；
    public boolean updateinfo(String bianhao) {
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put("shifoushangchuan", "yes");
            String userinfo = "userinfo";
            db.update(userinfo,
                    cv,
                    "yonghubianhao = ?",
                    new String[]{bianhao});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.d("", String.valueOf(e));
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }
//修改成功上传数据；
    public boolean updatechobiao(String bianhao) {
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put("shifoushangchuan", "yes");
            String userinfo = "chaobiao";
            db.update(userinfo,
                    cv,
                    "fangjianbianhao = ?",
                    new String[]{bianhao});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.d("", String.valueOf(e));
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }
    //更改列数据
    public void update1() {
        db.execSQL("update userinfo set shifoushangchuan = 'yes' where shifoujicha = 'isflag'");
    }

    //更改成上一次稽查记录
    public void update_isflag() {
        db.execSQL("update chaobiao set shifoushangchuan = 'isflag' where shifoujicha = 'no'");
    }

    //更改成上一次稽查记录
    public void update_isflag1() {
        db.execSQL("update userinfo set shifushangchuan = 'isflag' where shifoujicha = 'no'");
    }

    //更改成上一次稽查记录
    public void delete() {
        db.execSQL("delete from chaobiao ");
    }

    //更改成上一次稽查记录
    public void delete1() {
        db.execSQL("delete from userinfo ");
    }

    //关闭数据库
    public void closeDB() {
        db.close();
    }
}
