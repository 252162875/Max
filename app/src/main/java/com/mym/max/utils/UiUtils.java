package com.mym.max.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mym.max.app.BaseApplication;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;


/**
 * 提供ui操作的帮助类
 */
public class UiUtils {
    private static final String TAG_FAKE_STATUS_BAR_VIEW = "statusBarView";
    private static final String TAG_MARGIN_ADDED = "marginAdded";
    public static ArrayList<Activity> act;
    public static Activity homeAct;

    // 获取context对象
    public static Context getContext() {
        return BaseApplication.getContext();
    }

    // 获取主线程的handler
    public static Handler getMainThreadHandler() {
        return BaseApplication.getHandler();
    }

    // 获取主线程的线程id
    public static int getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    // 获取字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    // 获取字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    // 获取drawable
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    // 获取color
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    // 获取color的状态选择器
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }

    // dimen下对应的像素值
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }

    // dp-->px
    public static int dip2px(int dip) {
        float density = getContext().getResources().getDisplayMetrics().density;// 320dpi/160=2
        return (int) (dip * density + 0.5);// 加上0.5 四舍五入
    }

    // 判断是否在主线程中运行
    public static boolean isRunOnUiThread() {
        int currentThreadId = android.os.Process.myTid();// 获取当前线程所在的id
        return currentThreadId == getMainThreadId();
    }

    // 让r一定在主线程当中执行
    public static void runOnUiThread(Runnable r) {
        if (isRunOnUiThread()) {
            r.run();// r.start--启动新的线程
        } else {
            getMainThreadHandler().post(r);// 把r放到主线程的消息队列执行
        }
    }

    // 加载布局文件
    public static View inflateView(int id) {
        return View.inflate(getContext(), id, null);
    }

    public static void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    // 启动新的Activity
    public static void startActivity(Context context, Class cls) {
        Intent i = new Intent(context, cls);
        context.startActivity(i);
    }

    // 启动新的Activity,携带参数
    public static void startActivityWithBundle(Context context, Class cls, Bundle bundle) {
        Intent i = new Intent(context, cls);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    // 启动新的Activity,并得到返回结果
    public static void startActivityForResult(Activity activity, Class cls, int requestCode) {
        Intent i = new Intent(activity, cls);
        activity.startActivityForResult(i, requestCode);
    }

    // 启动新的Activity,携带参数,并得到返回结果
    public static void startActivityForResultWithBundle(Activity activity, Class cls, Bundle bundle, int requestCode) {
        Intent i = new Intent(activity, cls);
        i.putExtras(bundle);
        activity.startActivityForResult(i, requestCode);
    }

    // 得到Activity,携带的“BUNDLE”参数
    public static Bundle getActivityBundle(Activity activity) {
        Intent intent = activity.getIntent();
        Bundle bundle = intent.getExtras();
        return bundle;
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 关闭软键盘
     **/
    public static boolean hideInputSoft(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    /**
     * 获取屏幕宽高
     **/
    public static int[] getScreenWH(Context context) {
        int[] wh = new int[2];
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        int height = defaultDisplay.getHeight();
        int width = defaultDisplay.getWidth();
        wh[1] = height;
        wh[0] = width;
        return wh;
    }

    /**
     * return statusBar's Height in pixels
     */
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }


    /**
     * use reserved order to remove is more quickly.
     */
    private static void removeFakeStatusBarViewIfExist(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup mDecorView = (ViewGroup) window.getDecorView();

        View fakeView = mDecorView.findViewWithTag(TAG_FAKE_STATUS_BAR_VIEW);
        if (fakeView != null) {
            mDecorView.removeView(fakeView);
        }
    }

    /**
     * remove marginTop to simulate set FitsSystemWindow false
     */
    private static void removeMarginTopOfContentChild(View mContentChild, int statusBarHeight) {
        if (mContentChild == null) {
            return;
        }
        if (TAG_MARGIN_ADDED.equals(mContentChild.getTag())) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mContentChild.getLayoutParams();
            lp.topMargin -= statusBarHeight;
            mContentChild.setLayoutParams(lp);
            mContentChild.setTag(null);
        }
    }

    /**
     * 设置图片到imageview
     * @param view
     * @param url
     */
    public static void setImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).apply(BaseApplication.getRequestOptions()).into(view);
    }
}
