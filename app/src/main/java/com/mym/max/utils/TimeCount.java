package com.mym.max.utils;

import android.os.CountDownTimer;

/**
 * 倒计时工具类
 */
public class TimeCount extends CountDownTimer {
    private CountDownTimerListener countDownTimerListener;

    public TimeCount(long millisInFuture, long countDownInterval, CountDownTimerListener countDownTimerListener) {
        super((millisInFuture), countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.countDownTimerListener = countDownTimerListener;//过程监听
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        countDownTimerListener.onFinish();
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        countDownTimerListener.onTick(millisUntilFinished);
    }

    public interface CountDownTimerListener {
        void onFinish();

        void onTick(long millisUntilFinished);
    }
}
