package com.mym.max.base;

import android.os.Bundle;

public abstract class BaseDataBindingActivity extends BaseActivity {
    public BaseModel myModel;
    public BaseViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    public abstract void getData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myModel != null) {
            myModel.removeSubscription();
        }
    }
}
