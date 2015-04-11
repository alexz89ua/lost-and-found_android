package com.stfalcon.lostandfound;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Faq> faqList;

    public RecyclerAdapter(List<Faq> faqList) {
        this.faqList = faqList;
    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Faq faq = faqList.get(i);
        viewHolder.questionTextView.setText(faq.getQuestion());
        viewHolder.answerTextView.setText("***"+faq.getAnswer());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.recycler_view_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

}
