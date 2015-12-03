package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.MoneyViewHolder;
import com.dtd.interyou.model.ItemMoney;

import java.util.ArrayList;

/**
 * Created by Egor on 01.06.2015.
 */
public class MoneyListAdapter extends RecyclerView.Adapter<MoneyViewHolder> {

    private final Context mContext;
    private ArrayList<ItemMoney> mItems;
    private final View.OnClickListener listener;


    public MoneyListAdapter(final Context context, final ArrayList<ItemMoney> itemMoneys, final View.OnClickListener listener){
        mContext = context;
        this.mItems = itemMoneys;
        this.listener = listener;
    }

    @Override
    public MoneyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_money_list, viewGroup, false);
        return new MoneyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(MoneyViewHolder holder, int i) {
        holder.setImage(mItems.get(i).getImageId());
        holder.setTitle(mItems.get(i).getTitle());
        holder.setSum(mItems.get(i).getSum());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
