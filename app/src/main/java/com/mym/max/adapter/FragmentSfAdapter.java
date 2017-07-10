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
import android.widget.Toast;

import com.mym.max.R;
import com.mym.max.bean.GankIoBean;
import com.mym.max.databinding.ItemGankHomeNormalBinding;
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

public class FragmentSfAdapter extends RecyclerView.Adapter {
    private final int FIRST_VIEW = 0;
    private final int SECOND_VIEW = 1;
    Context context;
    List<GankIoBean.ResultsBean> data;
    private OnItemClickLisenter onItemClickLisenter;
    private LayoutInflater layoutInflater;

    public FragmentSfAdapter(Context context, List<GankIoBean.ResultsBean> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载Item View的时候根据不同TYPE加载不同的布局
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == FIRST_VIEW) {
            return new ItemFirstViewHolder(inflater.inflate(R.layout.item_gank_home_header, parent, false));
        } else {
            return ItemSecondViewHolder.createViewHolder(ItemGankHomeNormalBinding.inflate(layoutInflater));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemFirstViewHolder) {
            final ArrayList<String> images = new ArrayList<String>();
            final ArrayList<String> titles = new ArrayList<String>();
            images.add("http://i1.img.969g.com/pub/imgx2015/01/09/326_161451_9bc72_lit.png");
            images.add("http://i1.img.969g.com/pub/imgx2015/01/09/326_161418_283a6.png");
            images.add("http://i3.img.969g.com/pub/imgx2015/01/09/326_161430_a6b41_lit.png");
            images.add("http://i1.img.969g.com/pub/imgx2015/01/09/326_161447_fe593_lit.png");
            images.add("http://i3.img.969g.com/pub/imgx2015/01/09/326_161453_1a3f0_lit.png");
            images.add("http://i3.img.969g.com/pub/imgx2015/01/09/326_161457_205bd_lit.png");
            images.add("http://i2.img.969g.com/pub/imgx2015/01/09/326_161458_328b8_lit.png");
            images.add("http://i1.img.969g.com/pub/imgx2015/01/09/326_161522_3f3c6_lit.png");
            images.add("http://i1.img.969g.com/pub/imgx2015/01/09/326_161542_9d4bf_lit.png");
            titles.add("title-1");
            titles.add("title-2");
            titles.add("title-3");
            titles.add("title-4");
            titles.add("title-5");
            titles.add("title-6");
            titles.add("title-7");
            titles.add("title-8");
            titles.add("title-9");
//            images.add("aaaaa");
            //设置banner样式
            ((ItemFirstViewHolder) holder).banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            //设置图片加载器
            ((ItemFirstViewHolder) holder).banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            ((ItemFirstViewHolder) holder).banner.setImages(images);
            //设置标题集合
            ((ItemFirstViewHolder) holder).banner.setBannerTitles(titles);
            //设置banner动画效果
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.Default);
            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.Accordion);//这个在7.0没问题
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.BackgroundToForeground);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.ForegroundToBackground);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.CubeIn);//这个不错,但是在7.0上没效果
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.CubeOut);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.DepthPage);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.FlipHorizontal);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.FlipVertical);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.RotateDown);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.RotateUp);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.ScaleInOut);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.Stack);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.Tablet);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.ZoomIn);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.ZoomOut);
//            ((ItemFirstViewHolder) holder).banner.setBannerAnimation(Transformer.ZoomOutSlide);
            //设置自动轮播，默认为true
            ((ItemFirstViewHolder) holder).banner.isAutoPlay(true);
            //设置轮播时间
            ((ItemFirstViewHolder) holder).banner.setDelayTime(4000);
            //设置指示器位置（当banner模式中有指示器时）
            ((ItemFirstViewHolder) holder).banner.setIndicatorGravity(BannerConfig.CENTER);
            ((ItemFirstViewHolder) holder).banner.start();
            ((ItemFirstViewHolder) holder).banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", images.get(position));
                    UiUtils.startActivityWithBundle(context, WebActivity.class, bundle);
                }
            });
            ((ItemFirstViewHolder) holder).tvAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "打开新的activity，展示分类", Toast.LENGTH_SHORT).show();
                }
            });


        } else if (holder instanceof ItemSecondViewHolder) {
            ((ItemSecondViewHolder) holder).bindData(data.get(position - 1));
            ((ItemSecondViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickLisenter != null) {
                        onItemClickLisenter.onItemClicked(v, position - 1);
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
        if (position == 0) {
            return FIRST_VIEW;
        } else {
            return SECOND_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }


    public class ItemFirstViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.iv_all)
        ImageView ivAll;
        @BindView(R.id.tv_all)
        TextView tvAll;

        public ItemFirstViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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