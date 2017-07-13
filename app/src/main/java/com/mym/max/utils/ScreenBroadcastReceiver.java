package com.mym.max.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mym.max.ui.activity.LiveActivity;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class ScreenBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) { // 开屏
//            Log.e("ScreenBroadcastReceiver", "ON");
            LiveActivity.killKeepLive();
        } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) { // 锁屏
//            Log.e("ScreenBroadcastReceiver", "OFF");
            LiveActivity.startKeepLive();
        }
    }
}