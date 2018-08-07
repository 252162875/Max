package com.mym.max.ui.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mym.max.R;
import com.mym.max.base.BaseFragment;
import com.mym.max.databinding.FragmentClassificationBinding;
import com.mym.max.model.SfFragmentModel;
import com.mym.max.ui.view.MultiStateView;
import com.mym.max.viewmodel.ClassificationFragmentViewModel;

@SuppressLint("ValidFragment")
public class ClassficationFragment extends BaseFragment {
    public FragmentClassificationBinding mBinding;
    private String type = "all";

    public ClassficationFragment() {
    }

    public ClassficationFragment(String type) {
        this.type = type;
    }

    @Override
    public MultiStateView initView(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_classification, container, false);
        myModel = new SfFragmentModel();
        myViewModel = new ClassificationFragmentViewModel(this, myModel, type);
        mBinding.setAfvm((ClassificationFragmentViewModel) myViewModel);
        MultiStateView state_view = (MultiStateView) mBinding.getRoot().findViewById(R.id.state_view);
        if (state_view == null) {
            throw new NullPointerException("Empty state_view");
        }
        return state_view;
    }

    @Override
    public void getData() {
        ((ClassificationFragmentViewModel) myViewModel).getGankIoData(type, 1, 20);
    }
}