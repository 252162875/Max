package com.mym.max.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import com.bumptech.glide.request.RequestOptions;
import com.mym.max.R;
import com.mym.max.utils.ScreenBroadcastReceiver;
import com.mym.max.utils.UiUtils;

//import com.baidu.mapapi.SDKInitializer;
//import com.tencent.bugly.crashreport.CrashReport;

/**
 * 自定义的Application 1、运行的过程中只有一个实例 2、一个应用程序最先执行的方法 运行在主线程中
 * 注意：在AndroidManifest文件中进行注册
 */
public class BaseApplication extends Application {


    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static RequestOptions requestOptions;

    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
//        SDKInitializer.initialize(this);
        // 初始化context对象，context对象使用的非常多
        context = getApplicationContext();
        // 获取主线程的handler 相当于获取主线程的消息队列
        handler = new Handler();
        // 获取主线程的id 哪一个方法调用了myTid myTid就返回那个方法所在线程的id
        mainThreadId = android.os.Process.myTid();
//        // bugly异常处理
//        CrashReport.initCrashReport(getApplicationContext(), "0e20923237", false);
        requestOptions = RequestOptions.placeholderOf(R.drawable.place).error(R.drawable.error);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        ScreenBroadcastReceiver screenBroadcastReceiver = new ScreenBroadcastReceiver();
        UiUtils.getContext().registerReceiver(screenBroadcastReceiver, filter);
    }

    public static RequestOptions getRequestOptions() {
        return requestOptions;
    }

    public static void setRequestOptions(RequestOptions requestOptions) {
        BaseApplication.requestOptions = requestOptions;
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}