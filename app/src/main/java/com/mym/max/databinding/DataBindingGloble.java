package com.mym.max.databinding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.mym.max.R;
import com.mym.max.ui.view.MultiStateView;


/**
 * Custom binding.
 * <p>
 * Created by Eric on 15/6/23.
 */
public class DataBindingGloble {

//    @BindingAdapter({"initMultiStateView"})
//    public static void multiStateViewState(MultiStateView state_view, int multiState) {
//        if (state_view == null) {
//            throw new NullPointerException("state_view View is null");
//        }
//        state_view.setViewForState(R.layout.empty_view, MultiStateView.VIEW_STATE_EMPTY);
//        state_view.setViewForState(R.layout.error_view, MultiStateView.VIEW_STATE_ERROR);
//        state_view.setViewForState(R.layout.loading_view, MultiStateView.VIEW_STATE_LOADING);
//        state_view.setViewState(multiState);
//    }

    @BindingAdapter({"status"})
    public static void bindStatus(MultiStateView stateView, int status) {
        stateView.setViewState(status);
    }

    @BindingAdapter({"refresh"})
    public static void refresh(SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener listener) {
        swipeRefreshLayout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"layoutManager"})
    public static void layoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"adapter"})
    public static void adapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"decoration"})
    public static void decoration(RecyclerView recyclerView, RecyclerView.ItemDecoration decoration) {
        recyclerView.addItemDecoration(decoration);
    }
}
