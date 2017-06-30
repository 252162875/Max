package com.mym.max.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.mym.max.R;
import com.mym.max.base.BaseDataBindingActivity;
import com.mym.max.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseDataBindingActivity {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_splash)
    public void onViewClicked() {
        UiUtils.startActivity(this, HomeActivity.class);
    }

    @Override
    public void getData() {

    }
}
