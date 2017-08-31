package com.northsoft.model;

import java.util.List;

/**
 * Created by chensiqi on 2016/11/18.
 */

public class jiekou4_1_model {


    /**
     * Result : 0
     * Data : [{"MeterID":1,"WaterUsed":9,"WaterFee":17.55,"BillID":"0103246803","BillCycle":"201705","MeterType":"居民用水","MeterEnd":833,"MeterStart":824,"DrainFee":8.55}]
     * ErrText :
     */

    private int Result;
    private String ErrText;
    private List<DataBean> Data;

    public int getResult() {
        return Result;
    }

    public void setResult(int Result) {
        this.Result = Result;
    }

    public String getErrText() {
        return ErrText;
    }

    public void setErrText(String ErrText) {
        this.ErrText = ErrText;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * MeterID : 1
         * WaterUsed : 9
         * WaterFee : 17.55
         * BillID : 0103246803
         * BillCycle : 201705
         * MeterType : 居民用水
         * MeterEnd : 833
         * MeterStart : 824
         * DrainFee : 8.55
         */

        private int MeterID;
        private int WaterUsed;
        private double WaterFee;
        private String BillID;
        private String BillCycle;
        private String MeterType;
        private int MeterEnd;
        private int MeterStart;
        private double DrainFee;

        public int getMeterID() {
            return MeterID;
        }

        public void setMeterID(int MeterID) {
            this.MeterID = MeterID;
        }

        public int getWaterUsed() {
            return WaterUsed;
        }

        public void setWaterUsed(int WaterUsed) {
            this.WaterUsed = WaterUsed;
        }

        public double getWaterFee() {
            return WaterFee;
        }

        public void setWaterFee(double WaterFee) {
            this.WaterFee = WaterFee;
        }

        public String getBillID() {
            return BillID;
        }

        public void setBillID(String BillID) {
            this.BillID = BillID;
        }

        public String getBillCycle() {
            return BillCycle;
        }

        public void setBillCycle(String BillCycle) {
            this.BillCycle = BillCycle;
        }

        public String getMeterType() {
            return MeterType;
        }

        public void setMeterType(String MeterType) {
            this.MeterType = MeterType;
        }

        public int getMeterEnd() {
            return MeterEnd;
        }

        public void setMeterEnd(int MeterEnd) {
            this.MeterEnd = MeterEnd;
        }

        public int getMeterStart() {
            return MeterStart;
        }

        public void setMeterStart(int MeterStart) {
            this.MeterStart = MeterStart;
        }

        public double getDrainFee() {
            return DrainFee;
        }

        public void setDrainFee(double DrainFee) {
            this.DrainFee = DrainFee;
        }
    }
}
