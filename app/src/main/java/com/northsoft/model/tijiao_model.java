package com.northsoft.model;

/**
 * Created by zhuxiaolin on 2017/4/10 10:42.
 */

public class tijiao_model {

    public int id;
    public String fangjianbianhao;
    public String userid;
    public String MeterID_1;
    public String MeterID_2;
    public String MeterID_3;
    public String NumEnd_1;
    public String NumEnd_2;
    public String NumEnd_3;
    public String shifushangchuang;

    public tijiao_model() {
    }

    public tijiao_model( String fangjianbianhao, String userid, String meterID_1, String meterID_2, String meterID_3, String numEnd_1, String numEnd_2, String numEnd_3, String shifushangchuang) {

        this.fangjianbianhao = fangjianbianhao;
        this.userid = userid;
        MeterID_1 = meterID_1;
        MeterID_2 = meterID_2;
        MeterID_3 = meterID_3;
        NumEnd_1 = numEnd_1;
        NumEnd_2 = numEnd_2;
        NumEnd_3 = numEnd_3;
        this.shifushangchuang = shifushangchuang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFangjianbianhao() {
        return fangjianbianhao;
    }

    public void setFangjianbianhao(String fangjianbianhao) {
        this.fangjianbianhao = fangjianbianhao;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMeterID_1() {
        return MeterID_1;
    }

    public void setMeterID_1(String meterID_1) {
        MeterID_1 = meterID_1;
    }

    public String getMeterID_2() {
        return MeterID_2;
    }

    public void setMeterID_2(String meterID_2) {
        MeterID_2 = meterID_2;
    }

    public String getMeterID_3() {
        return MeterID_3;
    }

    public void setMeterID_3(String meterID_3) {
        MeterID_3 = meterID_3;
    }

    public String getNumEnd_1() {
        return NumEnd_1;
    }

    public void setNumEnd_1(String numEnd_1) {
        NumEnd_1 = numEnd_1;
    }

    public String getNumEnd_2() {
        return NumEnd_2;
    }

    public void setNumEnd_2(String numEnd_2) {
        NumEnd_2 = numEnd_2;
    }

    public String getNumEnd_3() {
        return NumEnd_3;
    }

    public void setNumEnd_3(String numEnd_3) {
        NumEnd_3 = numEnd_3;
    }

    public String getShifushangchuang() {
        return shifushangchuang;
    }

    public void setShifushangchuang(String shifushangchuang) {
        this.shifushangchuang = shifushangchuang;
    }
}
