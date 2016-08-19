package com.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.tablayout.fragment.HomeFragment;
import com.tablayout.fragment.LiveFragment;
import com.tablayout.fragment.MyTaoBaoFragment;
import com.tablayout.fragment.ShopCarFragment;
import com.tablayout.fragment.TinyTaoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabs;
    private ViewPager mViewPager;
    private List<Fragment> list_fragment;
    private List<String> list_tab;
    private MyViewPagerAdapter mMyViewPagerAdapter;
    private int COUNT = 5;
    private Fragment mHomeFragment, mLiveFragment,
    mShopCarFragment,mMyTaoBaoFragment;
    private TinyTaoFragment mTinyTaoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        tabs = (TabLayout) findViewById(R.id.tablayout);
        tabs.setSmoothScrollingEnabled(true);
        //tabs.setSelectedTabIndicatorHeight(10);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        list_tab = new ArrayList<>();
        list_tab.add("首页");
        list_tab.add("微淘");
        list_tab.add("生活");
        list_tab.add("购物车");
        list_tab.add("我的淘宝");
        LayoutInflater mLayoutInflater = this.getLayoutInflater();
        for (int i = 0; i < COUNT; i++) {

            TabLayout.Tab tab = tabs.newTab();

            View view = mLayoutInflater.inflate(R.layout.tab, null);
            tab.setCustomView(view);

            TextView text = (TextView) view.findViewById(R.id.textView);
            text.setText(list_tab.get(i));
            Log.e("msg", "-----" + list_tab.get(i) + "---------");
            tabs.addTab(tab);
        }
        initFragment();

        //设置viewpager和tab连动
        //tabs.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    public void initFragment() {
        mHomeFragment=new HomeFragment();
        mTinyTaoFragment=new TinyTaoFragment();
        mLiveFragment=new LiveFragment();
        mShopCarFragment=new ShopCarFragment();
        mMyTaoBaoFragment=new MyTaoBaoFragment();
        list_fragment = new ArrayList<Fragment>();
        list_fragment.add(mHomeFragment);
        list_fragment.add(mTinyTaoFragment);
        list_fragment.add(mLiveFragment);
        list_fragment.add(mShopCarFragment);
        list_fragment.add(mMyTaoBaoFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mMyViewPagerAdapter = new MyViewPagerAdapter(fragmentManager, list_fragment);
        mViewPager.setAdapter(mMyViewPagerAdapter);
    }



    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        private FragmentManager fm;
        private List<Fragment> list;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.fm = fm;
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {

            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


}
