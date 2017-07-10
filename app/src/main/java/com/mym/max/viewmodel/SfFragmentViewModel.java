package com.mym.max.viewmodel;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.mym.max.BR;
import com.mym.max.adapter.FragmentSfAdapter;
import com.mym.max.base.BaseBean;
import com.mym.max.base.BaseFragment;
import com.mym.max.base.BaseModel;
import com.mym.max.base.BaseViewModel;
import com.mym.max.bean.GankIoBean;
import com.mym.max.model.SfFragmentModel;
import com.mym.max.ui.activity.WebActivity;
import com.mym.max.ui.view.MultiStateView;
import com.mym.max.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;


public class SfFragmentViewModel extends BaseViewModel implements OnRefreshListener, OnLoadMoreListener {
    @Bindable
    public int pageStatus = MultiStateView.VIEW_STATE_LOADING;
    public LinearLayoutManager linearLayoutManager;
    public FragmentSfAdapter adapter;
    public OnRefreshListener refreshListener = this;
    public OnLoadMoreListener loadMoreListener = this;
    @Bindable
    public boolean refreshState = false;
    @Bindable
    public boolean loadMoreState = false;
    @Bindable
    public ArrayList<GankIoBean.ResultsBean> data = new ArrayList<>();

    public SfFragmentViewModel(BaseFragment baseFragment, BaseModel baseModel) {
        super(baseFragment, baseModel);
        initEvent();
    }

    public void initEvent() {
        linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        adapter = new FragmentSfAdapter(context, data);
        adapter.setOnItemClickListener(new FragmentSfAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClicked(View v, int pos) {
//                Toast.makeText(context, "GOTO :" + data.get(pos).getUrl(), Toast.LENGTH_SHORT).show();
                String url = data.get(pos).getUrl();
                if (url == null || url.isEmpty() || url.equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                UiUtils.startActivityWithBundle(context, WebActivity.class, bundle);
            }
        });
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
        refreshState = false;
        notifyPropertyChanged(BR.refreshState);
        loadMoreState = false;
        notifyPropertyChanged(BR.loadMoreState);
    }


    @Override
    public void successData(BaseBean data) {
        if (data instanceof GankIoBean) {
            refreshState = false;
            notifyPropertyChanged(BR.refreshState);
            loadMoreState = false;
            notifyPropertyChanged(BR.loadMoreState);
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

    @BindingAdapter({"setRefresh"})
    public static void setRefresh(SwipeToLoadLayout swipeToLoadLayout, OnRefreshListener listener) {
        swipeToLoadLayout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"setLoadMore"})
    public static void setLoadMore(SwipeToLoadLayout swipeToLoadLayout, OnLoadMoreListener listener) {
        swipeToLoadLayout.setOnLoadMoreListener(listener);
    }

    @BindingAdapter({"refreshState"})
    public static void refreshState(SwipeToLoadLayout swipeToLoadLayout, boolean refreshState) {
        swipeToLoadLayout.setRefreshing(refreshState);
    }

    @BindingAdapter({"loadMoreState"})
    public static void loadMoreState(SwipeToLoadLayout swipeToLoadLayout, boolean loadMoreState) {
        swipeToLoadLayout.setLoadingMore(loadMoreState);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(context, "REFRESH", Toast.LENGTH_SHORT).show();
        refreshState = true;
        notifyPropertyChanged(BR.refreshState);
        getGankIoData("all", 1, 30);
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(context, "LOADMORE", Toast.LENGTH_SHORT).show();
        loadMoreState = true;
        notifyPropertyChanged(BR.loadMoreState);
        getGankIoData("all", 1, 30);
    }
}