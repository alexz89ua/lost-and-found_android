package com.stfalcon.lostandfound.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.stfalcon.lostandfound.R;
import com.stfalcon.lostandfound.refresh_list.ListFragment;


/**
 * Created by hp1 on 21-01-2015.
 */
public class MyLostThingsActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_lost_things_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        ListFragment fragment = new ListFragment();
        transaction.replace(R.id.lost_list_fragment, fragment);
        transaction.commit();
    }

}
