package com.stfalcon.lostandfound.profile;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.stfalcon.lostandfound.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by shpak on 29.03.15.
 */
public class MyTestTabActivity extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TabListAdapter listAdapter;
    private ListView listView;
    private String tittle;


    Random random = new Random();
    ArrayList<TabContent> objects =new ArrayList<TabContent>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_test_tab_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.test_refresh_layout);
        fillData();
        listView=(ListView)view.findViewById(R.id.test_listview);
        setupAdapter();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_scheme_1_1, R.color.color_scheme_1_2,
                R.color.color_scheme_1_3, R.color.color_scheme_1_4);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        randomizeData();
                        setupAdapter();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
    }

    void randomizeData(){
        objects.clear();
        for (int i=0;i<20;i++){
            objects.add(new TabContent("Прізвище Ім’я "+(random.nextInt(20)+1),random.nextInt(20)+1+":"+((random.nextInt(20)+1)*2+10),
                    "Etiam a lacinia massa, sit amet auctor orci. Praesent ornare massa eget dapibus vehicula.",
                    R.drawable.ic_plusone_standard_off_client));
        }
    }

    void fillData(){
        for (int i=0;i<20;i++){
            objects.add(new TabContent("Прізвище Ім’я "+i,random.nextInt(20)+1+":"+((random.nextInt(20)+1)*2+10),
                    "Etiam a lacinia massa, sit amet auctor orci. Praesent ornare massa eget dapibus vehicula.",
                    R.mipmap.ic_launcher));
        }

    }

    private void setupAdapter() {
        listAdapter =new TabListAdapter(getActivity(),objects);
        listView.setAdapter(listAdapter);
    }

}
