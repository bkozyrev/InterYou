package com.dtd.interyou.model;

public interface GetItemHandler<T>{
    public void done(T data);
    public void error(String responseError);
}
