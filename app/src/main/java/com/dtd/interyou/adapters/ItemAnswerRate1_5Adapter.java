package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.ItemAnswerRate1_5ViewHolder;

import java.util.ArrayList;

/**
 * Created by 123 on 02.07.2015.
 */
public class ItemAnswerRate1_5Adapter extends RecyclerView.Adapter<ItemAnswerRate1_5ViewHolder> {

    private final Context mContext;
    private ArrayList<String> items;
    private final View.OnClickListener listener;
    private boolean isLastPosition = false;

    public ItemAnswerRate1_5Adapter(final Context context, final ArrayList<String> items, final View.OnClickListener listener){
        mContext = context;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ItemAnswerRate1_5ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(!isLastPosition) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_answer_multiplescale_1_5, viewGroup, false);
            return new ItemAnswerRate1_5ViewHolder(v, listener, false);
        }else{
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_next, viewGroup, false);
            return new ItemAnswerRate1_5ViewHolder(v, listener, true);
        }
    }

    @Override
    public void onBindViewHolder(ItemAnswerRate1_5ViewHolder holder, int position) {
        if(position != items.size()) {
            holder.setQuestion(items.get(position));

            if(position == items.size() - 1){
                isLastPosition = true;
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size() + 1;
    }
}
