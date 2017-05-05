package com.northsoft.Share_MyApplication;

import android.app.Application;

import com.northsoft.model.jiekou1_model;
import com.northsoft.model.jiekou2_model;
import com.northsoft.model.jiekou3_model;
import com.northsoft.model.jiekou4_1_model;
import com.northsoft.model.jiekou4_model;
import com.northsoft.model.jiekou5_model;
import com.northsoft.model.jiekou6_1_model;
import com.northsoft.model.jiekou6_2_model;
import com.northsoft.model.loginUserID_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensiqi on 2016/11/22.
 */

public class MyApplication extends Application {

    //定义各全局变量
    public List<jiekou1_model> jiekou1_model_list;
    public List<loginUserID_model> mLoginUserID_modelList;


    public ArrayList<jiekou2_model> jiekou2_Model_list;
    public List<jiekou3_model> jiekou3Model;
    public List<jiekou4_model> jiekou4Model;
    public List<jiekou4_1_model> jiekou4_1Model;
    public List<jiekou5_model> jiekou5Model;


    public List<jiekou6_2_model> mJiekou6_2modelList;
    public jiekou6_1_model jiekou6_1Model;
    public jiekou6_2_model jiekou6_2Model;
    //装有所有接口2所需id的list
    public ArrayList<String> jiekou2_id_str_list;
    public ArrayList<String> login_id_str_list;

    @Override
    public void onCreate() {
        super.onCreate();
      /*  CrashHandler.getInstance().init(this); // 一定要先初始化
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance());*/
    }

    public ArrayList<String> getLogin_id_str_list() {
        return login_id_str_list;
    }

    public void setLogin_id_str_list(ArrayList<String> login_id_str_list) {
        this.login_id_str_list = login_id_str_list;
    }

    //装有所有接口3所需id的list
    public List<String> jiekou3_id_str_list;
    //装有所有接口4.1所需BILLID的list
    public List<String> jiekou4_1_BILLID_str_list;

    public List<loginUserID_model> getLoginUserID_modelList() {
        return mLoginUserID_modelList;
    }

    public void setLoginUserID_modelList(List<loginUserID_model> loginUserID_modelList) {
        mLoginUserID_modelList = loginUserID_modelList;
    }

    //装有所有接口2所需id的list
    public ArrayList<String> getJiekou2_id_str_list() {
        return jiekou2_id_str_list;
    }

    public void setJiekou2_id_str_list(ArrayList<String> jiekou2_id_str_list) {
        this.jiekou2_id_str_list = jiekou2_id_str_list;
    }

    //装有所有接口3所需id的list
    public List<String> getJiekou3_id_str_list() {
        return jiekou3_id_str_list;
    }

    public void setJiekou3_id_str_list(List<String> jiekou3_id_str_list) {
        this.jiekou3_id_str_list = jiekou3_id_str_list;
    }

    //装有所有接口4.1所需BILLID的list
    public List<String> getJiekou4_1_BILLID_str_list() {
        return jiekou4_1_BILLID_str_list;
    }

    public void setJiekou4_1_BILLID_str_list(List<String> jiekou4_1_BILLID_str_list) {
        this.jiekou4_1_BILLID_str_list = jiekou4_1_BILLID_str_list;
    }

    //jiekou1_model的get,set方法
    public List<jiekou1_model> getJiekou1_model_list() {
        return jiekou1_model_list;
    }

    public void setJiekou1_model_list(List<jiekou1_model> list) {
        this.jiekou1_model_list = list;
    }


    //jiekou2_model的get,set方法
    public ArrayList<jiekou2_model> getJiekou2_Model_list() {
        return jiekou2_Model_list;
    }

    public void setJiekou2_Model_list(ArrayList<jiekou2_model> jiekou2Model) {
        this.jiekou2_Model_list = jiekou2Model;
    }

    //jiekou3_model的set,get方法
    public List<jiekou3_model> getJiekou3Model() {
        return jiekou3Model;
    }

    public void setJiekou3Model(List<jiekou3_model> jiekou3Model) {
        this.jiekou3Model = jiekou3Model;
    }

    //jiekou4_model的set,get方法
    public List<jiekou4_model> getJiekou4Model() {
        return jiekou4Model;
    }

    public void setJiekou4Model(List<jiekou4_model> jiekou4Model) {
        this.jiekou4Model = jiekou4Model;
    }

    //jiekou4_1_model的set,get方法
    public List<jiekou4_1_model> getJiekou4_1Model() {
        return jiekou4_1Model;
    }

    public void setJiekou4_1Model(List<jiekou4_1_model> jiekou4_1Model) {
        this.jiekou4_1Model = jiekou4_1Model;
    }

    //jiekou5_model的set,get方法
    public List<jiekou5_model> getJiekou5Model() {
        return jiekou5Model;
    }

    public void setJiekou5Model(List<jiekou5_model> jiekou5Model) {
        this.jiekou5Model = jiekou5Model;
    }


    public List<jiekou6_1_model> getJiekou6_1modelList() {
        return mJiekou6_1modelList;
    }

    public void setJiekou6_1modelList(List<jiekou6_1_model> jiekou6_1modelList) {
        mJiekou6_1modelList = jiekou6_1modelList;
    }

    public List<jiekou6_1_model> mJiekou6_1modelList;

    public List<jiekou6_2_model> getJiekou6_2modelList() {
        return mJiekou6_2modelList;
    }

    public void setJiekou6_2modelList(List<jiekou6_2_model> jiekou6_2modelList) {
        mJiekou6_2modelList = jiekou6_2modelList;
    }

    //jiekou6_1_model的set,get方法
    public jiekou6_1_model getJiekou6_1Model() {
        return jiekou6_1Model;
    }

    public void setJiekou6_1Model(jiekou6_1_model jiekou6_1Model) {
        this.jiekou6_1Model = jiekou6_1Model;
    }

    //jiekou6_2_model的set,get方法
    public jiekou6_2_model getJiekou6_2Model() {
        return jiekou6_2Model;
    }

    public void setJiekou6_2Model(jiekou6_2_model jiekou6_2Model) {
        this.jiekou6_2Model = jiekou6_2Model;
    }


}
