package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.HistoryPollsGridViewHolder;
import com.dtd.interyou.model.Polls;

import java.util.ArrayList;

/**
 * Created by Egor on 22.04.2015.
 */
public class HistoryPollsListAdapter extends RecyclerView.Adapter<HistoryPollsGridViewHolder> {

    private final Context mContext;
    private ArrayList<Polls> polls;
    private final View.OnClickListener listener;

    public HistoryPollsListAdapter(final Context context, final ArrayList<Polls> polls, final View.OnClickListener listener){
        mContext = context;
        this.polls = polls;
        this.listener = listener;
    }

    @Override
    public HistoryPollsGridViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pollshistory_list, viewGroup, false);
        return new HistoryPollsGridViewHolder(v, mContext, listener);
    }

    @Override
    public void onBindViewHolder(HistoryPollsGridViewHolder holder, int i) {
        holder.setImage(polls.get(i).getCategory());
        holder.setTitle(polls.get(i).getTitle());
        holder.setDate(polls.get(i).getDate());
        //holder.setPrice(polls.get(i).getPrice()); TODO на серве у отдельного опроса нет поля цены
    }

    @Override
    public int getItemCount() {
        return polls.size();
    }
}
