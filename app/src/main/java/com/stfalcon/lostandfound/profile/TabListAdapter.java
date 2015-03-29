package com.stfalcon.lostandfound.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stfalcon.lostandfound.R;

import java.util.ArrayList;

/**
 * Created by shpak on 29.03.15.
 */
public class TabListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<TabContent> objets;

    TabListAdapter(Context _context,ArrayList<TabContent> _objects){
        context=_context;
        objets=_objects;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objets.size();
    }

    @Override
    public Object getItem(int position) {
        return objets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int _position, View _view, ViewGroup _viewGroup) {
        View view =_view;
        if(view==null){
            view=layoutInflater.inflate(R.layout.list_item_layout,_viewGroup,false);
        }
        TabContent cnt=getTabContent(_position);

        ((TextView)view.findViewById(R.id.name)).setText(cnt.getName());
        ((TextView)view.findViewById(R.id.time)).setText(cnt.getTime());
        ((TextView)view.findViewById(R.id.text)).setText(cnt.getText());
        ((ImageView)view.findViewById(R.id.image)).setImageResource(cnt.getImage());
        return view;
    }

    TabContent getTabContent(int position){
        return ((TabContent) getItem(position));
    }
}
