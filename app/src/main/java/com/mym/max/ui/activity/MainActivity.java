package com.mym.max.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mym.max.R;
import com.mym.max.utils.UiUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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
}
