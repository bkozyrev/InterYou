package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.ItemAnswerDropDownViewHolder;

import java.util.ArrayList;

/**
 * Created by 123 on 02.07.2015.
 */
public class ItemAnswerDropDownAdapter extends RecyclerView.Adapter<ItemAnswerDropDownViewHolder> {

    private final Context mContext;
    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private final View.OnClickListener listener;
    private boolean isLastPosition = false;

    public ItemAnswerDropDownAdapter(final Context context, final ArrayList<String> questions, ArrayList<String> answers, final View.OnClickListener listener){
        mContext = context;
        this.questions = questions;
        this.answers = answers;
        this.listener = listener;
    }

    @Override
    public ItemAnswerDropDownViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(!isLastPosition) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_answer_drop_down, viewGroup, false);
            return new ItemAnswerDropDownViewHolder(v, mContext, listener, answers, false);
        }else{
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_next, viewGroup, false);
            return new ItemAnswerDropDownViewHolder(v, mContext, listener, answers, true);
        }
    }

    @Override
    public void onBindViewHolder(ItemAnswerDropDownViewHolder holder, int position) {
        if(position != questions.size()) {
            holder.setQuestion(questions.get(position));

            if(position == questions.size() - 1){
                isLastPosition = true;
            }
        }
    }

    @Override
    public int getItemCount() {
        return questions.size() + 1;
    }
}
