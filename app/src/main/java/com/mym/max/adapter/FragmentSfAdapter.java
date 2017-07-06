package com.mym.max.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mym.max.R;
import com.mym.max.bean.GankIoBean;
import com.mym.max.databinding.ItemGankHomeNormalBinding;
import com.mym.max.utils.UiUtils;

import java.util.List;

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

        } else if (holder instanceof ItemSecondViewHolder) {
            ((ItemSecondViewHolder) holder).bindData(data.get(position - 1));
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
}