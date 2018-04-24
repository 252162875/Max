package com.mym.max.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.mym.max.R;
import com.mym.max.app.Constants;
import com.mym.max.app.RequestCode;
import com.mym.max.base.BaseDataBindingActivity;
import com.mym.max.ui.fragment.FragmentMap;
import com.mym.max.ui.fragment.SfFragment;
import com.mym.max.utils.StatusBarUtil;
import com.mym.max.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.iv_avatar_drawer)
    ImageView iv_avatar_drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        fragmentMap = new FragmentMap();
        setSupportActionBar(toolbar);
        StatusBarUtil.setColorForDrawerLayout(this, drawerlayout, getResources().getColor(R.color.appMainColor), 0);//content的容器必须是FramLayout否则statusbar底部会有一条深色线
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
                    fragment = new SfFragment();
                    break;
                case 1:
                    fragment = new SfFragment();
                    break;
                case 2:
                    fragment = new SfFragment();
                    break;
                case 3:
                    fragment = new SfFragment();
                    break;
                case 4:
                    fragment = new SfFragment();
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

    @OnClick({R.id.iv_avatar, R.id.toolbar,R.id.iv_avatar_drawer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                if (!drawerlayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerlayout.openDrawer(Gravity.LEFT, true);
                } else {
                    drawerlayout.closeDrawer(Gravity.LEFT, true);
                }
                break;
            case R.id.toolbar:
                break;
            case R.id.iv_avatar_drawer:
                UiUtils.startActivityForResult(HomeActivity.this,SetAvatarActivity.class, RequestCode.REQUEST_CODE_HOME_AVATAR);
                break;
        }
    }

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
