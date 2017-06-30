package com.mym.max.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mym.max.utils.UiUtils;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addActivity(Activity activity) {
        if (UiUtils.act == null) {
            UiUtils.act = new ArrayList<>();
        }
        UiUtils.act.add(activity);
    }

    public void finishSavedActivitys() {
        if (UiUtils.act == null) {
            return;
        }
        for (int i = 0; i < UiUtils.act.size(); i++) {
            UiUtils.act.get(i).finish();
        }
        UiUtils.act = null;
    }

    public void finishHomeActivity() {
        if (UiUtils.homeAct != null) {
            UiUtils.homeAct.finish();
        }
    }
}
