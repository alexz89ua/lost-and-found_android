package com.stfalcon.lostandfound.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.stfalcon.lostandfound.R;

/**
 * Created by hp1 on 21-01-2015.
 */
public class MyFoundThingsActivity extends Fragment {

    private ListView myListView;
    private String[] listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.my_found_things_layout,container,false);

        myListView=(ListView) rootView.findViewById(R.id.my_found_list);
        listItems=getResources().getStringArray(R.array.found_list_items);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,listItems);
        myListView.setAdapter(objAdapter);


        return rootView;
    }
}
