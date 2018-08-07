package com.mym.max.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mym.max.R;
import com.mym.max.ui.view.MultiStateView;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    public BaseModel myModel;
    public BaseViewModel myViewModel;
    public boolean isInitView = false;
    public MultiStateView state_view;
    public LayoutInflater mInflater;
    public Button retry;
    private boolean isFirstLoad = true;
    private boolean isVisible = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        state_view = initView(inflater, container);
        if (state_view == null) {
            throw new NullPointerException("state_view View");
        }
        if (mInflater == null) mInflater = LayoutInflater.from(getContext());
        View empty_view = mInflater.inflate(R.layout.empty_view, state_view, false);
        View error_view = mInflater.inflate(R.layout.error_view, state_view, false);
        retry = (Button) error_view.findViewById(R.id.retry);
        empty_view.setOnClickListener(this);
        retry.setOnClickListener(this);
        View loading_view = mInflater.inflate(R.layout.loading_view, state_view, false);
        ImageView iv_loading = (ImageView) loading_view.findViewById(R.id.iv_loading);
        Glide.with(getContext()).load(R.drawable.nvhai).into(iv_loading);
        state_view.setViewForState(empty_view, MultiStateView.VIEW_STATE_EMPTY);
        state_view.setViewForState(error_view, MultiStateView.VIEW_STATE_ERROR);
        state_view.setViewForState(loading_view, MultiStateView.VIEW_STATE_LOADING);
        state_view.setViewState(MultiStateView.VIEW_STATE_LOADING);
        return state_view;
    }

    public abstract MultiStateView initView(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        isInitView = true;
        lazyLoad();
    }

    private void lazyLoad() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            return;
        }
        //加载数据
        getData();
        //设置非第一次加载
        isFirstLoad = false;
    }

    public abstract void getData();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.empty:
            case R.id.retry:
                state_view.setViewState(MultiStateView.VIEW_STATE_LOADING);
                getData();
                break;
        }
    }
}