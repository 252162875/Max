package com.mym.max.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jaeger.library.StatusBarUtil;
import com.mym.max.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pagebottomtablayout)
    PageBottomTabLayout bottomTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.appMainColor), 0);
        bottomTablayout.material()
                .addItem(R.drawable.rb_me, R.drawable.rb_me_click, "影魔", getResources().getColor(R.color.appMainColor))
                .addItem(R.drawable.rb_home, R.drawable.rb_home_click, "祈求着", getResources().getColor(R.color.appMainColor))
                .addItem(R.drawable.ti5_off, R.drawable.ti5_on, "精灵龙", getResources().getColor(R.color.appMainColor))
                .addItem(R.drawable.rb_discovery, R.drawable.rb_discovery_click, "幻影刺客", getResources().getColor(R.color.appMainColor))
                .addItem(R.drawable.rb_video, R.drawable.rb_video_click, "末日使者", getResources().getColor(R.color.appMainColor)).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
