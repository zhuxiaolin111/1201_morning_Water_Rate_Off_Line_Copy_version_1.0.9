package com.northsoft.model;

import java.util.List;

/**
 * Created by chensiqi on 2016/11/18.
 */

public class jiekou4_1_model {

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

        private int meterid;
        private int waterused;
        private double waterfee;
        private String billid;
        private String billcycle;
        private String metertype;
        private int meterend;
        private int meterstart;
        private double drainfee;


        public void setMeterid(int meterid) {
            this.meterid = meterid;
        }
        public int getMeterid() {
            return meterid;
        }

        public void setWaterused(int waterused) {
            this.waterused = waterused;
        }
        public int getWaterused() {
            return waterused;
        }

        public void setWaterfee(double waterfee) {
            this.waterfee = waterfee;
        }
        public double getWaterfee() {
            return waterfee;
        }

        public void setBillid(String billid) {
            this.billid = billid;
        }
        public String getBillid() {
            return billid;
        }

        public void setBillcycle(String billcycle) {
            this.billcycle = billcycle;
        }
        public String getBillcycle() {
            return billcycle;
        }

        public void setMetertype(String metertype) {
            this.metertype = metertype;
        }
        public String getMetertype() {
            return metertype;
        }

        public void setMeterend(int meterend) {
            this.meterend = meterend;
        }
        public int getMeterend() {
            return meterend;
        }

        public void setMeterstart(int meterstart) {
            this.meterstart = meterstart;
        }
        public int getMeterstart() {
            return meterstart;
        }

        public void setDrainfee(double drainfee) {
            this.drainfee = drainfee;
        }
        public double getDrainfee() {
            return drainfee;
        }

    }


}
