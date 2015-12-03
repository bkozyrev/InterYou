package com.dtd.interyou.model;

/**
 * Created by Egor on 02.06.2015.
 */
public class Operation {
    public enum Type{
        OUTPUT,
        EXCHANGE,
        INPUT
    }

    private String Date;
    private Type type;
    private String Money;

    public Operation() {
    }

    public Operation(String date, Type type, String money) {
        Date = date;
        this.type = type;
        Money = money;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }
}
