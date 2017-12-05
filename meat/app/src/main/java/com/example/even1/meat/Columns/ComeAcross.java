package com.example.even1.meat.Columns;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.even1.meat.Listener.PagerChangeListener;
import com.example.even1.meat.Adapter.mSimpleAdapter;
import com.example.even1.meat.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComeAcross extends AppCompatActivity {

    /*private ViewPager viewpager;
    private TabLayout tab;
    private List<Fragment> fragment= new ArrayList<Fragment>();
    private List<String> titlelist= new ArrayList<String>();*/
    @Bind(R.id.main_vp_container) ViewPager mVpContainer;
    @Bind(R.id.toolbar_tl_tab) TabLayout mTlTab;
    @Bind(R.id.appbar_iv_outgoing)
    ImageView mIvOutgoing;
    @Bind(R.id.appbar_iv_target) ImageView mIvTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_come_across);
        ButterKnife.bind(this);
        /*fragment.add(new Usually());
        fragment.add(new Sometimes());
        fragment.add(new Seldom());


        viewpager = (ViewPager)findViewById(R.id.viewpager);
        mFragmentPagerAdapter adapter = new mFragmentPagerAdapter(getSupportFragmentManager(),fragment,titlelist);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        titlelist.add("经常");
        titlelist.add("有时");
        titlelist.add("偶尔");
        tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(viewpager);
        tab.setTabTextColors(Color.WHITE,Color.BLACK);
        tab.addTab(tab.newTab().setText(titlelist.get(0)));
        tab.addTab(tab.newTab().setText(titlelist.get(1)));
        tab.addTab(tab.newTab().setText(titlelist.get(2)));
*/

        // 导航栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 设置ViewPager布局
        mSimpleAdapter adapter = new mSimpleAdapter(getSupportFragmentManager());
        mVpContainer.setAdapter(adapter);
        mVpContainer.addOnPageChangeListener(PagerChangeListener.newInstance(adapter, mIvTarget, mIvOutgoing));
        mTlTab.setupWithViewPager(mVpContainer); // 注意在Toolbar中关联ViewPager

        setTitle("擦肩而过的人");

    }
}
