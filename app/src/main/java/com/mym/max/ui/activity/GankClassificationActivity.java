package com.mym.max.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.mym.max.R;
import com.mym.max.adapter.GankClassificationAdapter;
import com.mym.max.base.BaseDataBindingActivity;
import com.mym.max.ui.fragment.ClassficationFragment;
import com.mym.max.ui.fragment.SfFragment;
import com.mym.max.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 干货分类
 */
public class GankClassificationActivity extends BaseDataBindingActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_classification);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.appMainColor), 0);
        initControls();
    }

    private void initControls() {
        vpContent.setOffscreenPageLimit(8);
        final ArrayList<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new ClassficationFragment("Android"));
        listFragment.add(new ClassficationFragment("iOS"));
        listFragment.add(new ClassficationFragment("前端"));
        listFragment.add(new ClassficationFragment("拓展资源"));
        listFragment.add(new ClassficationFragment("福利"));
        listFragment.add(new ClassficationFragment("瞎推荐"));
        listFragment.add(new ClassficationFragment("App"));
        listFragment.add(new ClassficationFragment("休息视频"));
        ArrayList<String> listTitle = new ArrayList<>();
        listTitle.add("Android");
        listTitle.add("iOS");
        listTitle.add("前端");
        listTitle.add("拓展资源");
        listTitle.add("福利 ");
        listTitle.add("瞎推荐");
        listTitle.add("App");
        listTitle.add("休息视频");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(0)).setTag(0));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(1)).setTag(1));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(2)).setTag(2));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(3)).setTag(3));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(4)).setTag(4));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(5)).setTag(5));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(6)).setTag(6));
        tabLayout.addTab(tabLayout.newTab().setText(listTitle.get(7)).setTag(7));
        GankClassificationAdapter gankClassificationAdapter = new GankClassificationAdapter(getSupportFragmentManager(), listFragment, listTitle);

        //viewpager加载adapter
        vpContent.setAdapter(gankClassificationAdapter);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(vpContent);
        tabLayout.setTabsFromPagerAdapter(gankClassificationAdapter);//给Tabs设置适配器
    }

    @Override
    public void getData() {

    }
}
