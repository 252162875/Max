package com.mym.max.model;


import com.mym.max.base.BaseModel;
import com.mym.max.bean.HomeBean;

import java.util.HashMap;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MineFragmentModel extends BaseModel {
    @Override
    public void sendRequest(HashMap<String, String> requestData) {
        Subscription subscribe = service.getHomeData(requestData).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeBean>() {
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
                    public void onNext(HomeBean homeBean) {
                        httpResultIml.onRequestSuccess(homeBean);
                    }
                });
        addSubscription(subscribe);
    }
}