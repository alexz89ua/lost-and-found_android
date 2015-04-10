package com.stfalcon.lostandfound.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.stfalcon.lostandfound.*;
import com.stfalcon.lostandfound.ui.fragments.FaqFragment;
import com.stfalcon.lostandfound.ui.fragments.MapFragment;
import com.stfalcon.lostandfound.ui.fragments.ProfileFragment;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawer;
    private ListView mListView;
    private String[] menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView = (ListView) findViewById(R.id.left_drawer);
        menuItems = getResources().getStringArray(R.array.menu_items);

        // Set the adapter for the list view
        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menuItems));


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new MapFragment();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        break;
                    case 4: {
                        fragment = new FaqFragment();
                    }
                }
                transaction.replace(R.id.container, fragment);
                transaction.commit();
                mDrawer.closeDrawers();
            }
        });

    }

}

