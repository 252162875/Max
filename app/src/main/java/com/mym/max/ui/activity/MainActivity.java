package com.mym.max.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.mym.max.R;
import com.mym.max.base.BaseDataBindingActivity;
import com.mym.max.utils.TimeCount;
import com.mym.max.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseDataBindingActivity {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    private TimeCount timeCount;
    private boolean closed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        timeCount = delayToHome(5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeCount.cancel();
        timeCount.start();
    }

    private TimeCount delayToHome(int i) {
        TimeCount timeCount = new TimeCount(1000 * (i + 1), 100, new TimeCount.CountDownTimerListener() {
            @Override
            public void onFinish() {
                if (closed) {
                    //主动点击跳过的标记
                    return;
                }
                tvTime.setText("跳转中...");
                UiUtils.startActivity(MainActivity.this, HomeActivity.class);
                MainActivity.this.finish();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText(millisUntilFinished / 1000 + "s");
            }
        });
        return timeCount;
    }


    @Override
    public void getData() {

    }

    @OnClick(R.id.tv_skip)
    public void onViewClicked() {
        closed = true;
        UiUtils.startActivity(this, HomeActivity.class);
        MainActivity.this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK  && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
