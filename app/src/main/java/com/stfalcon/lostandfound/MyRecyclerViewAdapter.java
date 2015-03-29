package com.stfalcon.lostandfound;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.NamesViewHolder> {

    private Context mContext;
    List<String> mCatNames;

    public MyRecyclerViewAdapter(Context context) {
        mContext = context;
        randomizeCatNames();
    }

    public void randomizeCatNames() {
        mCatNames = Arrays.asList(getCatNamesResource());
        Collections.shuffle(mCatNames);
    }

    public class NamesViewHolder extends RecyclerView.ViewHolder {
        TextView mCatNameTextView;

        public NamesViewHolder(View itemView) {
            super(itemView);
            mCatNameTextView = (TextView) itemView.findViewById(R.id.name);
        }
    }

    private String[] getCatNamesResource() {
        return mContext.getResources().getStringArray(R.array.list_items);
    }

    @Override
    public NamesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, viewGroup, false);
        return new NamesViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(NamesViewHolder viewHolder, int i) {
        String catName = getItem(i);
        viewHolder.mCatNameTextView.setText(catName);
    }

    public String getItem(int position) {
        return mCatNames.get(position);
    }

    @Override
    public int getItemCount() {
        return mCatNames.size();
    }


}
