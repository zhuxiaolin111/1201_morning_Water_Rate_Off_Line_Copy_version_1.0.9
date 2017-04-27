package com.northsoft.order;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chensiqi on 2016/11/7.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "/sdcard/chaobiao.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //第一次创建数据库时会调用这个方法
    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists chaobiao (_id integer primary key autoincrement,"
                + "fangjianbianhao varchar(50),"
                + "userid varchar(50),"
                + "MeterID_1 varchar(50),"
                + "MeterID_2 varchar(50),"
                + "MeterID_3 varchar(50),"
                + "NumEnd_1 varchar(50),"
                + "NumEnd_2 varchar(50),"
                + "NumEnd_3 varchar(50),"
                + "shifoushangchuan varchar(50))");
        db.execSQL("create table if not exists userinfo (_id integer primary key autoincrement,"
                + "yonghubianhao varchar(50),"
                + "phone varchar(50),"
                + "Census varchar(50),"
                + "userid varchar(50),"
                + "shifoushangchuan varchar(50))");
    }


    //更新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // db.execSQL("ALTER TABLE jicha ADD COLUMN other STRING");
    }
}
