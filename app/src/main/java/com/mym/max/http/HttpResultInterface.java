package com.mym.max.http;


import com.mym.max.base.BaseBean;

public interface HttpResultInterface {
    void onRequestError(Throwable e);

    void onRequestStart();

    void onRequestFinished();

    void onRequestSuccess(BaseBean data);
}