package com.mym.max.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.jaeger.library.StatusBarUtil;
import com.mym.max.R;
import com.mym.max.base.BaseDataBindingActivity;
import com.mym.max.ui.fragment.FragmentMap;
import com.mym.max.ui.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class HomeActivity extends BaseDataBindingActivity {
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    private FragmentMap fragmentMap;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pagebottomtablayout)
    PageBottomTabLayout bottomTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        fragmentMap = new FragmentMap();
        setSupportActionBar(toolbar);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.appMainColor), 0);
        StatusBarUtil.setColorForDrawerLayout(this, drawerlayout, getResources().getColor(R.color.appMainColor), 0);
        NavigationController controller = bottomTablayout.material()
                .addItem(R.drawable.rb_me, R.drawable.rb_me_click, "影魔", getResources().getColor(R.color.appMainColor))
                .addItem(R.drawable.rb_home, R.drawable.rb_home_click, "祈求着", getResources().getColor(R.color.appMainColor))
                .addItem(R.drawable.ti5_off, R.drawable.ti5_on, "精灵龙", getResources().getColor(R.color.appMainColor))
                .addItem(R.drawable.rb_discovery, R.drawable.rb_discovery_click, "幻影刺客", getResources().getColor(R.color.appMainColor))
                .addItem(R.drawable.rb_video, R.drawable.rb_video_click, "末日使者", getResources().getColor(R.color.appMainColor)).build();
        controller.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                showFragment(index);
            }

            @Override
            public void onRepeat(int index) {

            }
        });
        //默认初始化第一页，但是不走回调，所以手动调用showFragment(0)加载第一页数据
        controller.setSelect(0);
        showFragment(0);

    }

    /**
     * 根据index设置显示的fragment
     *
     * @param index
     */
    protected void showFragment(int index) {
        Fragment fragment = fragmentMap.getFragment(index);
        if (fragment == null) {
            switch (index) {
                case 0:
                    fragment = new MineFragment();
                    break;
                case 1:
                    fragment = new MineFragment();
                    break;
                case 2:
                    fragment = new MineFragment();
                    break;
                case 3:
                    fragment = new MineFragment();
                    break;
                case 4:
                    fragment = new MineFragment();
                    break;
            }
            fragmentMap.addFragment(index, fragment);
        }
        FragmentManager manage = getSupportFragmentManager();
        fragmentMap.showRightToTargt(index, flContent, manage);
    }

    @Override
    public void getData() {

    }
}
