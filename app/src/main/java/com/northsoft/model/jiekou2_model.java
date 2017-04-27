package com.northsoft.model;

import java.util.Date;
import java.util.List;

/**
 * Created by chensiqi on 2016/11/18.
 */

public class jiekou2_model {


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

    public static class Data{


            private String address;
            private int totalfee;
            private String cardnum;
            private double oweFee;
            private int waterused;
            private int census;
            private String lastfee;
            private int isread;
            private Date lastcheckmeterdate;
            private String jiaofee;
            private String ownername;
            private String userid;
            private int bookseq;
            private int metercount;


            public void setAddress(String address) {
                this.address = address;
            }
            public String getAddress() {
                return address;
            }

            public void setTotalfee(int totalfee) {
                this.totalfee = totalfee;
            }
            public int getTotalfee() {
                return totalfee;
            }

            public void setCardnum(String cardnum) {
                this.cardnum = cardnum;
            }
            public String getCardnum() {
                return cardnum;
            }

            public void setOweFee(double oweFee) {
                this.oweFee = oweFee;
            }
            public double getOweFee() {
                return oweFee;
            }

            public void setWaterused(int waterused) {
                this.waterused = waterused;
            }
            public int getWaterused() {
                return waterused;
            }

            public void setCensus(int census) {
                this.census = census;
            }
            public int getCensus() {
                return census;
            }

            public void setLastfee(String lastfee) {
                this.lastfee = lastfee;
            }
            public String getLastfee() {
                return lastfee;
            }

            public void setIsread(int isread) {
                this.isread = isread;
            }
            public int getIsread() {
                return isread;
            }

            public void setLastcheckmeterdate(Date lastcheckmeterdate) {
                this.lastcheckmeterdate = lastcheckmeterdate;
            }
            public Date getLastcheckmeterdate() {
                return lastcheckmeterdate;
            }

            public void setJiaofee(String jiaofee) {
                this.jiaofee = jiaofee;
            }
            public String getJiaofee() {
                return jiaofee;
            }

            public void setOwnername(String ownername) {
                this.ownername = ownername;
            }
            public String getOwnername() {
                return ownername;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
            public String getUserid() {
                return userid;
            }

            public void setBookseq(int bookseq) {
                this.bookseq = bookseq;
            }
            public int getBookseq() {
                return bookseq;
            }

            public void setMetercount(int metercount) {
                this.metercount = metercount;
            }
            public int getMetercount() {
                return metercount;
            }


    }

}
