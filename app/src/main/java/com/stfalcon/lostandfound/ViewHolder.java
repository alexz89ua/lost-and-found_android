package com.stfalcon.lostandfound;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView questionTextView;
    protected TextView answerTextView;
    Animation animation;

    public ViewHolder(View view) {
        super(view);
        this.questionTextView = (TextView) view.findViewById(R.id.questionTextView);
        this.answerTextView = (TextView) view.findViewById(R.id.answerTextView);
        answerTextView.setVisibility(View.GONE);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (answerTextView.isShown())
            answerTextView.setVisibility(View.GONE);
        else {
            answerTextView.setVisibility(View.VISIBLE);
        }
    }

}
