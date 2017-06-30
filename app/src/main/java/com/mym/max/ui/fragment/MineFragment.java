package com.mym.max.ui.fragment;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mym.max.R;
import com.mym.max.base.BaseFragment;
import com.mym.max.databinding.FragmentMineBinding;
import com.mym.max.model.MineFragmentModel;
import com.mym.max.ui.view.MultiStateView;
import com.mym.max.viewmodel.MineFragmentViewModel;


public class MineFragment extends BaseFragment {
    private FragmentMineBinding mBinding;

    @Override
    public MultiStateView initView(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        myModel = new MineFragmentModel();
        myViewModel = new MineFragmentViewModel(this, myModel);
        mBinding.setMfvm((MineFragmentViewModel) myViewModel);
        MultiStateView state_view = (MultiStateView) mBinding.getRoot().findViewById(R.id.state_view);
        if (state_view == null) {
            throw new NullPointerException("Empty state_view");
        }
        return state_view;
    }

    @Override
    public void getData() {
        myViewModel.requestData();
    }
}