package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.ItemAnswerCheckViewHolder;

import java.util.ArrayList;

/**
 * Created by 123 on 03.07.2015.
 */
public class ItemAnswerCheckAdapter extends RecyclerView.Adapter<ItemAnswerCheckViewHolder> {

    private final Context mContext;
    private ArrayList<String> items;
    private final View.OnClickListener listener;
    private boolean isLastPosition = false, isPreLastPosition = false, others;

    public ItemAnswerCheckAdapter(final Context context, final ArrayList<String> items, final View.OnClickListener listener, Boolean others){
        mContext = context;
        this.items = items;
        this.listener = listener;
        this.others = others;
    }

    @Override
    public ItemAnswerCheckViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v;

        if(!isLastPosition && !isPreLastPosition) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_answer_check, viewGroup, false);
            return new ItemAnswerCheckViewHolder(v, listener, false, false);
        }
        else if(others && isPreLastPosition){
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_answer_check_special, viewGroup, false);
            return new ItemAnswerCheckViewHolder(v, listener, true, false);
        }else{
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_next, viewGroup, false);
            return new ItemAnswerCheckViewHolder(v, listener, false, true);
        }
    }

    @Override
    public void onBindViewHolder(ItemAnswerCheckViewHolder holder, int position) {
        if(position < items.size()) {
            holder.setAnswer(items.get(position));
        }
        if(position == items.size() - 1){
            isPreLastPosition = true;
        }
        if(position == items.size()){
            isLastPosition = true;
        }
    }

    @Override
    public int getItemCount() {
        if(others) {
            return items.size() + 2;
        }else{
            return items.size() + 1;
        }
    }
}
