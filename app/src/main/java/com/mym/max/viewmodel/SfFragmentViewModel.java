package com.mym.max.viewmodel;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mym.max.BR;
import com.mym.max.adapter.FragmentSfAdapter;
import com.mym.max.base.BaseBean;
import com.mym.max.base.BaseFragment;
import com.mym.max.base.BaseModel;
import com.mym.max.base.BaseViewModel;
import com.mym.max.bean.GankIoBean;
import com.mym.max.model.SfFragmentModel;
import com.mym.max.ui.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;


public class SfFragmentViewModel extends BaseViewModel {
    @Bindable
    public int pageStatus = MultiStateView.VIEW_STATE_LOADING;
    public LinearLayoutManager linearLayoutManager;
    public FragmentSfAdapter adapter;
    @Bindable
    public ArrayList<GankIoBean.ResultsBean> data = new ArrayList<>();

    public SfFragmentViewModel(BaseFragment baseFragment, BaseModel baseModel) {
        super(baseFragment, baseModel);
        initEvent();
    }

    public void initEvent() {
        linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        adapter = new FragmentSfAdapter(context, data);
        notifyPropertyChanged(BR.data);
    }

    public void refresh() {

    }

    @Override
    public void setRequestData() {

    }

    @Override
    public void onRequestError(Throwable e) {
        super.onRequestError(e);
        pageStatus = MultiStateView.VIEW_STATE_ERROR;
        notifyPropertyChanged(BR.pageStatus);
    }


    @Override
    public void successData(BaseBean data) {
        if (data instanceof GankIoBean) {
            Toast.makeText(context, "GankIoBean数据拿到了", Toast.LENGTH_SHORT).show();
            pageStatus = MultiStateView.VIEW_STATE_CONTENT;
            notifyPropertyChanged(BR.pageStatus);
            this.data.clear();
            for (int i = 0; i < ((GankIoBean) data).getResults().size(); i++) {
                this.data.add(((GankIoBean) data).getResults().get(i));
            }
            notifyPropertyChanged(BR.data);
        }
    }

    public void getGankIoData(String id, int page, int perPage) {
        ((SfFragmentModel) baseModel).getGankIoData(id, page, perPage);
    }

    @Override
    public void finish(View view) {
    }

    public void nextPage(View view) {
        Toast.makeText(context, "下一个页面-个人资料", Toast.LENGTH_SHORT).show();
    }

    @BindingAdapter({"data"})
    public static void data(RecyclerView recyclerView, List data) {
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}