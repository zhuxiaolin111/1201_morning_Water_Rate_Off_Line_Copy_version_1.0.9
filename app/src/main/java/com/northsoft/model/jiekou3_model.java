package com.northsoft.model;

import java.util.List;

/**
 * Created by chensiqi on 2016/11/18.
 */

public class jiekou3_model {


        private int result;
        private List<Data> data;
        private String errtext;


        public void setResult(int result) {
            this.result = result;
        }
        public int getResult() {
            return result;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }
        public List<Data> getData() {
            return data;
        }

        public void setErrtext(String errtext) {
            this.errtext = errtext;
        }
        public String getErrtext() {
            return errtext;
        }



        public static class Data {

            private String address;
            private String phone;
            private String booktype;
            private String cardnum;
            private String wangname;
            private int oweFee;
            private String paytype;
            private int census;
            private int totalfee;
            private String cCashiername;
            private double lastfee;
            private int isread;
            private int readid;
            private String usertype;
            private int jiaofee;
            private String numend;
            private String chargetype;
            private String ownername;
            private int isic;
            private String userid;
            private String state;
            private int bookseq;
            private String usersts;


            public void setAddress(String address) {
                this.address = address;
            }
            public String getAddress() {
                return address;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
            public String getPhone() {
                return phone;
            }

            public void setBooktype(String booktype) {
                this.booktype = booktype;
            }
            public String getBooktype() {
                return booktype;
            }

            public void setCardnum(String cardnum) {
                this.cardnum = cardnum;
            }
            public String getCardnum() {
                return cardnum;
            }

            public void setWangname(String wangname) {
                this.wangname = wangname;
            }
            public String getWangname() {
                return wangname;
            }

            public void setOweFee(int oweFee) {
                this.oweFee = oweFee;
            }
            public int getOweFee() {
                return oweFee;
            }

            public void setPaytype(String paytype) {
                this.paytype = paytype;
            }
            public String getPaytype() {
                return paytype;
            }

            public void setCensus(int census) {
                this.census = census;
            }
            public int getCensus() {
                return census;
            }

            public void setTotalfee(int totalfee) {
                this.totalfee = totalfee;
            }
            public int getTotalfee() {
                return totalfee;
            }

            public void setCCashiername(String cCashiername) {
                this.cCashiername = cCashiername;
            }
            public String getCCashiername() {
                return cCashiername;
            }

            public void setLastfee(double lastfee) {
                this.lastfee = lastfee;
            }
            public double getLastfee() {
                return lastfee;
            }

            public void setIsread(int isread) {
                this.isread = isread;
            }
            public int getIsread() {
                return isread;
            }

            public void setReadid(int readid) {
                this.readid = readid;
            }
            public int getReadid() {
                return readid;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }
            public String getUsertype() {
                return usertype;
            }

            public void setJiaofee(int jiaofee) {
                this.jiaofee = jiaofee;
            }
            public int getJiaofee() {
                return jiaofee;
            }

            public void setNumend(String numend) {
                this.numend = numend;
            }
            public String getNumend() {
                return numend;
            }

            public void setChargetype(String chargetype) {
                this.chargetype = chargetype;
            }
            public String getChargetype() {
                return chargetype;
            }

            public void setOwnername(String ownername) {
                this.ownername = ownername;
            }
            public String getOwnername() {
                return ownername;
            }

            public void setIsic(int isic) {
                this.isic = isic;
            }
            public int getIsic() {
                return isic;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
            public String getUserid() {
                return userid;
            }

            public void setState(String state) {
                this.state = state;
            }
            public String getState() {
                return state;
            }

            public void setBookseq(int bookseq) {
                this.bookseq = bookseq;
            }
            public int getBookseq() {
                return bookseq;
            }

            public void setUsersts(String usersts) {
                this.usersts = usersts;
            }
            public String getUsersts() {
                return usersts;
            }


    }

}
