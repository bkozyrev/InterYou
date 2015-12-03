package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.ItemAnswerImageHorizontalViewHolder;

import java.util.ArrayList;

/**
 * Created by 123 on 03.07.2015.
 */
public class ItemAnswerImageHorizontalAdapter extends RecyclerView.Adapter<ItemAnswerImageHorizontalViewHolder> {

    private final Context mContext;
    private ArrayList<String> items;
    private final View.OnClickListener listener;

    public ItemAnswerImageHorizontalAdapter(final Context context, final ArrayList<String> items, final View.OnClickListener listener){
        mContext = context;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ItemAnswerImageHorizontalViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_answer_image_horizontal, viewGroup, false);
        return new ItemAnswerImageHorizontalViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ItemAnswerImageHorizontalViewHolder holder, int position) {
        holder.setImage(mContext, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
