package com.northsoft.model;

import java.util.List;

/**
 * Created by chensiqi on 2016/11/18.
 */

public class jiekou4_model {


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

        private double totalfee;
        private int waterused;
        private double waterfee;
        private String billid;
        private String billcycle;
        private String readdate;
        private double drainfee;


        public void setTotalfee(double totalfee) {
            this.totalfee = totalfee;
        }
        public double getTotalfee() {
            return totalfee;
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

        public void setReaddate(String readdate) {
            this.readdate = readdate;
        }
        public String getReaddate() {
            return readdate;
        }

        public void setDrainfee(double drainfee) {
            this.drainfee = drainfee;
        }
        public double getDrainfee() {
            return drainfee;
        }

    }

}
