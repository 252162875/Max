package com.mym.max.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mym.max.R;
import com.mym.max.bean.GankIoBean;
import com.mym.max.databinding.ItemGankHomeNormalBinding;

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
//            if (data.get(position - 1).getType().equals("福利")) {
//                Glide.with(context).load(data.get(position - 1).getUrl()).into(((ItemSecondViewHolder) holder).ivBig);
//                ((ItemSecondViewHolder) holder).ivBig.setVisibility(View.VISIBLE);
//                ((ItemSecondViewHolder) holder).ll_des_small_img.setVisibility(View.GONE);
//            } else {
//                ((ItemSecondViewHolder) holder).ivBig.setVisibility(View.GONE);
//                ((ItemSecondViewHolder) holder).ll_des_small_img.setVisibility(View.VISIBLE);
//                String url = "";
//                if (data != null) {
//                    if (data.get(position - 1) != null) {
//                        if (data.get(position - 1).getImages() != null) {
//                            url = data.get(position - 1).getImages().get(0);
//                        }
//                    }
//                }
//                if (url.equals("")) {
//                    ((ItemSecondViewHolder) holder).ivSmall.setVisibility(View.GONE);
//                } else {
//                    ((ItemSecondViewHolder) holder).ivSmall.setVisibility(View.VISIBLE);
//                    Glide.with(context).load(url).into(((ItemSecondViewHolder) holder).ivSmall);
//                }
//            }
//            ((ItemSecondViewHolder) holder).tvDes.setText(data.get(position - 1).getDesc());
//            ((ItemSecondViewHolder) holder).tvFrom.setText(data.get(position - 1).getType() + "·" + (data.get(position - 1).getWho() == null ? "佚名" : data.get(position - 1).getWho()));
//            ((ItemSecondViewHolder) holder).tvTime.setText(data.get(position - 1).getCreatedAt());
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
//        @BindView(R.id.tv_des)
//        TextView tvDes;
//        @BindView(R.id.tv_from)
//        TextView tvFrom;
//        @BindView(R.id.tv_time)
//        TextView tvTime;
//        @BindView(R.id.iv_big)
//        ImageView ivBig;
//        @BindView(R.id.iv_small)
//        ImageView ivSmall;
//        @BindView(R.id.ll_des_small_img)
//        LinearLayout ll_des_small_img;
//
//        public ItemSecondViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }

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

//    @BindingAdapter({"visibility"})
//    public static void data(View view, String type) {
//        if (type.equals("福利")) {
//            view.setVisibility(View.VISIBLE);
//        } else {
//            view.setVisibility(View.GONE);
//        }
//    }

    @BindingAdapter({"type", "url"})
    public static void loadBigImage(ImageView view, String type, String url) {
        if (type.equals("福利")) {
            view.setVisibility(View.VISIBLE);
            Glide.with(view.getContext()).load(url).into(view);
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
            Glide.with(view.getContext()).load(url).into(view);
        }
    }
}