package com.northsoft.model;

import java.util.Date;
import java.util.List;

/**
 * Created by chensiqi on 2016/11/18.
 */

public class jiekou5_model {


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

        private int payment;
        private Date paydate;


        public void setPayment(int payment) {
            this.payment = payment;
        }
        public int getPayment() {
            return payment;
        }

        public void setPaydate(Date paydate) {
            this.paydate = paydate;
        }
        public Date getPaydate() {
            return paydate;
        }

    }


}
