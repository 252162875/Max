package com.mym.max.model;


import com.mym.max.base.BaseModel;
import com.mym.max.bean.GankIoBean;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SfFragmentModel extends BaseModel {
    public void getGankIoData(String id, int page, int perPage) {
        Subscription subscribe = gankIOServer.getGankIoData(id, page, perPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GankIoBean>() {
            @Override
            public void onStart() {
                httpResultIml.onRequestStart();
            }

            @Override
            public void onCompleted() {
                httpResultIml.onRequestFinished();
            }

            @Override
            public void onError(Throwable e) {
                httpResultIml.onRequestError(e);
            }

            @Override
            public void onNext(GankIoBean gankIoBean) {
                httpResultIml.onRequestSuccess(gankIoBean);
            }
        });
        addSubscription(subscribe);
    }
}