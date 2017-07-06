package com.mym.max.ui.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.library.IWebLayout;
import com.mym.max.R;

/**
 * Created by cenxiaozhong on 2017/7/1.
 */

public class WebLayout implements IWebLayout {

    private Activity mActivity;
    private WebView mWebView = null;
    private final LinearLayout llWebLayout;

    public WebLayout(Activity activity) {
        this.mActivity = activity;
        llWebLayout = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.view_web_layout, null);
        mWebView = (WebView) llWebLayout.findViewById(R.id.webView);
    }

    @NonNull
    @Override
    public ViewGroup getLayout() {
        return llWebLayout;
    }

    @Nullable
    @Override
    public WebView getWeb() {
        return mWebView;
    }



}
