package com.northsoft.model;

import java.util.List;

/**
 * Created by zhuxiaolin on 2017/5/27 17:16.
 */

public class bb {
    /**
     * Result : 0
     * Data : [{"IsReaded":1,"Location":null,"InstallDate":null,"LockNum":null,"MeterID":1,"Calibre":20,"price_total":2.9,"MeterType":"居民用水","LastChangeDate":null,"NumStart":808,"Nameplate":null,"State":"正常","NumEnd":825,"LastEndNum":825}]
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
         * IsReaded : 1
         * Location : null
         * InstallDate : null
         * LockNum : null
         * MeterID : 1
         * Calibre : 20
         * price_total : 2.9
         * MeterType : 居民用水
         * LastChangeDate : null
         * NumStart : 808
         * Nameplate : null
         * State : 正常
         * NumEnd : 825
         * LastEndNum : 825
         */

        private int IsReaded;
        private Object Location;
        private Object InstallDate;
        private Object LockNum;
        private int MeterID;
        private int Calibre;
        private double price_total;
        private String MeterType;
        private Object LastChangeDate;
        private int NumStart;
        private Object Nameplate;
        private String State;
        private int NumEnd;
        private int LastEndNum;

        public int getIsReaded() {
            return IsReaded;
        }

        public void setIsReaded(int IsReaded) {
            this.IsReaded = IsReaded;
        }

        public Object getLocation() {
            return Location;
        }

        public void setLocation(Object Location) {
            this.Location = Location;
        }

        public Object getInstallDate() {
            return InstallDate;
        }

        public void setInstallDate(Object InstallDate) {
            this.InstallDate = InstallDate;
        }

        public Object getLockNum() {
            return LockNum;
        }

        public void setLockNum(Object LockNum) {
            this.LockNum = LockNum;
        }

        public int getMeterID() {
            return MeterID;
        }

        public void setMeterID(int MeterID) {
            this.MeterID = MeterID;
        }

        public int getCalibre() {
            return Calibre;
        }

        public void setCalibre(int Calibre) {
            this.Calibre = Calibre;
        }

        public double getPrice_total() {
            return price_total;
        }

        public void setPrice_total(double price_total) {
            this.price_total = price_total;
        }

        public String getMeterType() {
            return MeterType;
        }

        public void setMeterType(String MeterType) {
            this.MeterType = MeterType;
        }

        public Object getLastChangeDate() {
            return LastChangeDate;
        }

        public void setLastChangeDate(Object LastChangeDate) {
            this.LastChangeDate = LastChangeDate;
        }

        public int getNumStart() {
            return NumStart;
        }

        public void setNumStart(int NumStart) {
            this.NumStart = NumStart;
        }

        public Object getNameplate() {
            return Nameplate;
        }

        public void setNameplate(Object Nameplate) {
            this.Nameplate = Nameplate;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public int getNumEnd() {
            return NumEnd;
        }

        public void setNumEnd(int NumEnd) {
            this.NumEnd = NumEnd;
        }

        public int getLastEndNum() {
            return LastEndNum;
        }

        public void setLastEndNum(int LastEndNum) {
            this.LastEndNum = LastEndNum;
        }
    }
}
