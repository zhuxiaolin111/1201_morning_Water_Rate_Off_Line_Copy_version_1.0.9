package com.northsoft.model;

/**
 * Created by zhuxiaolin on 2017/4/10 9:38.
 */

public class user_change_info {


    public int id;
    public String yonghubianhao;
    //电话号
    public String phone;
    //人口数
    public String Census;
    public String userid;
    public String shifushangchuan;

    public user_change_info() {
    }

    public user_change_info(String yonghubianhao, String phone, String census, String userid, String shifushangchuan) {

        this.yonghubianhao = yonghubianhao;
        this.phone = phone;
        Census = census;
        this.userid = userid;
        this.shifushangchuan = shifushangchuan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYonghubianhao() {
        return yonghubianhao;
    }

    public void setYonghubianhao(String yonghubianhao) {
        this.yonghubianhao = yonghubianhao;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCensus() {
        return Census;
    }

    public void setCensus(String census) {
        Census = census;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getShifushangchuan() {
        return shifushangchuan;
    }

    public void setShifushangchuan(String shifushangchuan) {
        this.shifushangchuan = shifushangchuan;
    }
}
