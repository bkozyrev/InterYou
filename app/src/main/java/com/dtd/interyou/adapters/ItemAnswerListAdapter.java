package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.ItemAnswerListViewHolder;

import java.util.ArrayList;

/**
 * Created by 123 on 02.05.2015.
 */
public class ItemAnswerListAdapter extends RecyclerView.Adapter<ItemAnswerListViewHolder> {
    private final Context mContext;
    private ArrayList<String> items;
    private final View.OnClickListener listener;

    public ItemAnswerListAdapter(final Context context, final ArrayList<String> items, final View.OnClickListener listener){
        mContext = context;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ItemAnswerListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_answer_list, viewGroup, false);
        return new ItemAnswerListViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ItemAnswerListViewHolder holder, int i) {
        holder.setAnswer(items.get(i));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
