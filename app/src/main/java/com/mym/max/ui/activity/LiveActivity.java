package com.mym.max.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.mym.max.app.BaseApplication;
import com.mym.max.base.BaseActivity;

public class LiveActivity extends BaseActivity {
    private static LiveActivity instance;
    private static boolean b = false;
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            killKeepLive();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.e("LiveActivity", "instance");
        Window window = getWindow();
        //放在左上角
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        //宽高设计为1个像素
        attributes.width = 1;
        attributes.height = 1;
        //起始坐标
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (instance == null) {
            instance = this;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (instance == null) {
            instance = this;
        }
    }

    /**
     * 开启保活页面
     */
    public static void startKeepLive() {
        b = true;
//        Log.e("LiveActivity", "startActivity");
        Intent intent = new Intent(BaseApplication.getContext(), LiveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getContext().startActivity(intent);
    }

    /**
     * 关闭保活页面
     */
    public static void killKeepLive() {
        if (b) {
            if (instance != null) {
                b = false;
                instance.finish();
//                Log.e("LiveActivity", "instance != null");
            } else {
//                Log.e("LiveActivity", "instance = null");
                //当startKeepLive调用以后（b为true），LiveActivity实例instance还是null是就不停的递归调用killKeepLive直到instance != null 时关闭页面
                mHandler.sendEmptyMessage(0);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}