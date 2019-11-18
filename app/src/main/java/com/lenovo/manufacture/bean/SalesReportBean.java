package com.lenovo.manufacture.bean;

public class SalesReportBean {
    private String carName;
    private double price;
    private String outTime;
    private String outCount;

    public SalesReportBean(String carName, double price, String outTime, String outCount) {
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

    public String getOutCount() {
        return outCount;
    }

    public void setOutCount(String outCount) {
        this.outCount = outCount;
    }

    @Override
    public String toString() {
        return carName + "," + price + "," + outTime + "," + outCount;
    }
}
