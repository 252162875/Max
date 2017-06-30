package com.mym.max.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;


import com.mym.max.http.HttpResultInterface;
import com.mym.max.utils.UiUtils;

import java.util.HashMap;

public abstract class BaseViewModel extends BaseObservable implements HttpResultInterface {
    @Bindable
    public String title = "TITLE";
    public BaseModel baseModel;
    public Context context;
    public Bundle bundle;
    public BaseFragment baseFragment;
//    public final ProgressDialog pd;
    HashMap<String, String> requestHashMap = new HashMap<>();

    public BaseViewModel(Context context, BaseModel baseModel) {
        this.context = context;
        this.baseModel = baseModel;
        setRequestData();
//        pd = DialogUtils.getWaitDialog(context, "正在处理...");
        baseModel.setHttpResCallBack(this);
    }

    public BaseViewModel(Context context, BaseModel baseModel, Bundle bundle) {
        this.bundle = bundle;
        this.context = context;
        this.baseModel = baseModel;
        setRequestData();
//        pd = DialogUtils.getWaitDialog(context, "正在处理...");
        baseModel.setHttpResCallBack(this);
    }

    public BaseViewModel(BaseFragment baseFragment, BaseModel baseModel) {
        this.context = baseFragment.getActivity();
        this.baseModel = baseModel;
        this.baseFragment = baseFragment;
//        pd = DialogUtils.getWaitDialog(context, "正在处理...");
        baseModel.setHttpResCallBack(this);
    }

    public abstract void finish(View view);

    public void setRequestData() {

    }


    public void requestData() {
        baseModel.sendRequest(requestHashMap);
    }

    @Override
    public void onRequestError(Throwable e) {
        UiUtils.showToast(e.getMessage());
//        pd.dismiss();
    }

    @Override
    public void onRequestStart() {
//        pd.show();
    }

    @Override
    public void onRequestFinished() {
//        pd.dismiss();
    }

    @Override
    public void onRequestSuccess(BaseBean data) {
        if (data.getStatus() == 200 && data != null) {
            successData(data);
        } else {
            UiUtils.showToast(data.getMessage());
        }
    }

    public void successData(BaseBean data) {
    }
}