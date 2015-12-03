package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.ItemAnswerMultipleHandWrittenViewHolder;

import java.util.ArrayList;

/**
 * Created by 123 on 30.07.2015.
 */
public class ItemAnswerMultipleHandWrittenAdapter extends RecyclerView.Adapter<ItemAnswerMultipleHandWrittenViewHolder> {
    private final Context mContext;
    private ArrayList<String> questions;
    private final View.OnClickListener listener;
    private boolean isLastPosition = false;

    public ItemAnswerMultipleHandWrittenAdapter(final Context context, final ArrayList<String> questions, final View.OnClickListener listener){
        mContext = context;
        this.questions = questions;
        this.listener = listener;
    }

    @Override
    public ItemAnswerMultipleHandWrittenViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(!isLastPosition) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_answer_multiplehandwritten, viewGroup, false);
            return new ItemAnswerMultipleHandWrittenViewHolder(v, mContext, listener, false);
        }else{
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_next, viewGroup, false);
            return new ItemAnswerMultipleHandWrittenViewHolder(v, mContext, listener, true);
        }
    }

    @Override
    public void onBindViewHolder(ItemAnswerMultipleHandWrittenViewHolder holder, int position) {
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
