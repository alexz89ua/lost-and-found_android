package com.stfalcon.lostandfound.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stfalcon.lostandfound.R;
import com.stfalcon.lostandfound.profile.MyTestTabActivity;
import com.stfalcon.lostandfound.tabs.SlidingTabLayout;

/**
 * Created by shpak on 26.03.15.
 */
public class ProfileActivity extends Fragment {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mTabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        mTabs.setViewPager(mPager);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        int tabCount=5;
        String[] tabs= getResources().getStringArray(R.array.tabs);


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MyTestTabActivity();
                case 1:
                    return new MyTestTabActivity();
                case 2:
                    return new MyTestTabActivity();
                case 3:
                    return new MyTestTabActivity();
                case 4:
                    return new MyTestTabActivity();
                default:return  null;
            }

        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }
}
