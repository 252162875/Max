package com.mym.max.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mym.max.R;
import com.mym.max.bean.GankIoBean;
import com.mym.max.databinding.ItemGankHomeNormalBinding;
import com.mym.max.ui.activity.GankClassificationActivity;
import com.mym.max.ui.activity.WebActivity;
import com.mym.max.utils.GlideImageLoader;
import com.mym.max.utils.UiUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentClassificationAdapter extends RecyclerView.Adapter {
    private final int SECOND_VIEW = 1;
    Context context;
    List<GankIoBean.ResultsBean> data;
    private OnItemClickLisenter onItemClickLisenter;
    private LayoutInflater layoutInflater;

    public FragmentClassificationAdapter(Context context, List<GankIoBean.ResultsBean> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return ItemSecondViewHolder.createViewHolder(ItemGankHomeNormalBinding.inflate(layoutInflater));

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
         if (holder instanceof ItemSecondViewHolder) {
            ((ItemSecondViewHolder) holder).bindData(data.get(position));
            ((ItemSecondViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickLisenter != null) {
                        onItemClickLisenter.onItemClicked(v, position);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickLisenter listener) {
        this.onItemClickLisenter = listener;

    }

    @Override
    public int getItemViewType(int position) {
            return SECOND_VIEW;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ItemSecondViewHolder extends RecyclerView.ViewHolder {

        public ItemSecondViewHolder(View root, ViewDataBinding binding) {
            super(root);
            itemView.setTag(binding);
        }

        public static ItemSecondViewHolder createViewHolder(ViewDataBinding binding) {
            return new ItemSecondViewHolder(binding.getRoot(), binding);
        }

        public void bindData(GankIoBean.ResultsBean data) {
            ItemGankHomeNormalBinding binding = (ItemGankHomeNormalBinding) itemView.getTag();
            binding.setRb(data);
            binding.executePendingBindings();
        }
    }


    public interface OnItemClickLisenter {
        void onItemClicked(View v, int pos);
    }

    @BindingAdapter({"type", "url"})
    public static void loadBigImage(ImageView view, String type, String url) {
        if (type.equals("福利")) {
            view.setVisibility(View.VISIBLE);
            UiUtils.setImage(view, url);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"url"})
    public static void loadSmallImage(ImageView view, List<String> images) {
        String url = "";
        if (images != null && images.size() > 0) {
            url = images.get(0);
        }
        if (url == null || url.isEmpty() || url.equals("")) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
            UiUtils.setImage(view, url);
        }
    }

    //    @BindingAdapter({"images"})
    public void initBanner(Banner banner, ArrayList<String> images) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }
}