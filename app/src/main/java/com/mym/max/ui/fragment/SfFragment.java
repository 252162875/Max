package com.mym.max.ui.fragment;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mym.max.R;
import com.mym.max.base.BaseFragment;
import com.mym.max.databinding.FragmentSfBinding;
import com.mym.max.model.SfFragmentModel;
import com.mym.max.ui.view.MultiStateView;
import com.mym.max.viewmodel.SfFragmentViewModel;


public class SfFragment extends BaseFragment {
    private FragmentSfBinding mBinding;

    @Override
    public MultiStateView initView(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sf, container, false);
        myModel = new SfFragmentModel();
        myViewModel = new SfFragmentViewModel(this, myModel);
        mBinding.setMfvm((SfFragmentViewModel) myViewModel);
        MultiStateView state_view = (MultiStateView) mBinding.getRoot().findViewById(R.id.state_view);
        if (state_view == null) {
            throw new NullPointerException("Empty state_view");
        }
        return state_view;
    }

    @Override
    public void getData() {
        ((SfFragmentViewModel) myViewModel).getGankIoData("all", 1, 50);
    }
}