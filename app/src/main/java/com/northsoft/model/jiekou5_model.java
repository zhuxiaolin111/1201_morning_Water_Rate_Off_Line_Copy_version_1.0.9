package com.northsoft.model;

import java.util.List;

/**
 * Created by chensiqi on 2016/11/18.
 */

public class jiekou5_model {

    /**
     * Result : 0
     * Data : [{"Payment":153.1,"PayDate":"2017-01-25"},{"Payment":0,"PayDate":"2017-01-25"},{"Payment":0,"PayDate":"2017-01-25"},{"Payment":100,"PayDate":"2016-05-24"},{"Payment":100,"PayDate":"2015-09-01"},{"Payment":230,"PayDate":"2015-02-16"}]
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
         * Payment : 153.1
         * PayDate : 2017-01-25
         */

        private double Payment;
        private String PayDate;

        public double getPayment() {
            return Payment;
        }

        public void setPayment(double Payment) {
            this.Payment = Payment;
        }

        public String getPayDate() {
            return PayDate;
        }

        public void setPayDate(String PayDate) {
            this.PayDate = PayDate;
        }
    }
}
