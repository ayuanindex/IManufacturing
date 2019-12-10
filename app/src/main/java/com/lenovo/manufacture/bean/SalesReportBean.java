package com.lenovo.manufacture.bean;

import java.util.Timer;

public class SalesReportBean {
    private String carName;
    private double price;
    private String outTime;
    private int outCount;

    public SalesReportBean() {
    }

    public SalesReportBean(String carName, double price, String outTime, int outCount) {
        this.carName = carName;
        this.price = price;
        this.outTime = outTime;
        this.outCount = outCount;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public int getOutCount() {
        return outCount;
    }

    public void setOutCount(int outCount) {
        this.outCount = outCount;
    }

    @Override
    public String toString() {
        return this.carName + "," + this.price + "," + this.outTime + "," + this.outCount;
    }
}
