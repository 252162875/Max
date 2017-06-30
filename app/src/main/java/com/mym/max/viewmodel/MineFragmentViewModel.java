package com.mym.max.viewmodel;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.Toast;

import com.mym.max.BR;
import com.mym.max.base.BaseBean;
import com.mym.max.base.BaseFragment;
import com.mym.max.base.BaseModel;
import com.mym.max.base.BaseViewModel;
import com.mym.max.bean.HomeBean;
import com.mym.max.ui.view.MultiStateView;
import com.mym.max.utils.UiUtils;


public class MineFragmentViewModel extends BaseViewModel {
    @Bindable
    public int pageStatus = MultiStateView.VIEW_STATE_LOADING;

    public MineFragmentViewModel(BaseFragment baseFragment, BaseModel baseModel) {
        super(baseFragment, baseModel);
        initEvent();
    }

    public void initEvent() {

    }

    public void refresh() {

    }

    @Override
    public void setRequestData() {

    }

    @Override
    public void onRequestError(Throwable e) {
        super.onRequestError(e);
//        pageStatus = MultiStateView.VIEW_STATE_ERROR;
//        notifyPropertyChanged(BR.pageStatus);
        pageStatus = MultiStateView.VIEW_STATE_CONTENT;
        notifyPropertyChanged(BR.pageStatus);
    }

    @Override
    public void onRequestFinished() {
        super.onRequestFinished();
        pageStatus = MultiStateView.VIEW_STATE_CONTENT;
        notifyPropertyChanged(BR.pageStatus);
    }

    @Override
    public void successData(BaseBean data) {
        if (data instanceof HomeBean) {
            pageStatus = MultiStateView.VIEW_STATE_CONTENT;
            notifyPropertyChanged(BR.pageStatus);
            Toast.makeText(context, "数据拿到了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finish(View view) {
    }

    public void nextPage(View view) {
        Toast.makeText(context, "下一个页面-个人资料", Toast.LENGTH_SHORT).show();
    }

    @BindingAdapter({"status"})
    public static void bindStatus(MultiStateView stateView, int status) {
        stateView.setViewState(status);
    }
}