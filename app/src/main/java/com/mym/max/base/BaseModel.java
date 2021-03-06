package com.mym.max.base;



import com.mym.max.http.HttpClient;
import com.mym.max.http.HttpResultInterface;

import java.util.HashMap;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseModel {
    public HttpClient douBanService;
    public HttpClient gankIOServer;
    public HttpClient tingServer;
    public Subscriber subscriber;
    public HttpResultInterface httpResultIml;
    private CompositeSubscription mCompositeSubscription;

    public BaseModel() {
        douBanService = HttpClient.Builder.getDouBanService();
        gankIOServer = HttpClient.Builder.getGankIOServer();
        tingServer = HttpClient.Builder.getTingServer();
    }

    public void setHttpResCallBack(final HttpResultInterface httpResultIml) {
        this.httpResultIml = httpResultIml;
    }

    public void sendRequest(HashMap<String, String> requestData) {
    }

    public void unSubscriber() {
        if (subscriber == null) {
            return;
        }
        subscriber.unsubscribe();
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    public void removeSubscription() {
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.clear();
        }
    }
}