package com.stfalcon.lostandfound;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stfalcon.lostandfound.profile.MyFoundThingsActivity;
import com.stfalcon.lostandfound.profile.MyLostThingsActivity;
import com.stfalcon.lostandfound.profile.MyNotActivatedThingsActivity;
import com.stfalcon.lostandfound.profile.MyNotModeratedThingsActivity;
import com.stfalcon.lostandfound.tabs.SlidingTabLayout;

import java.util.List;
import java.util.Vector;

/**
 * Created by shpak on 26.03.15.
 */
public class ProfileActivity extends MainActivity {
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        mPager=(ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs=(SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setViewPager(mPager);
    }

    class MyPagerAdapter extends FragmentPagerAdapter{
        int tabCount;
        String[] tabs;
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs= getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    MyLostThingsActivity tab1 = new MyLostThingsActivity();
                    return tab1;
                case 1:
                    MyFoundThingsActivity tab2 = new MyFoundThingsActivity();
                    return tab2;
                case 2:
                    MyNotActivatedThingsActivity tab3 = new MyNotActivatedThingsActivity();
                    return tab3;
                case 3:
                    MyNotModeratedThingsActivity tab4 = new MyNotModeratedThingsActivity();
                    return tab4;
            }
            return  null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 4;
        }
    }




}
