package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.PollsViewHolder;
import com.dtd.interyou.model.PollInfo;

import java.util.ArrayList;

/**
 * Created by 123 on 02.05.2015.
 */
public class PollsListAdapter extends RecyclerView.Adapter<PollsViewHolder> {

    private final Context mContext;
    private ArrayList<PollInfo> polls;
    private final View.OnClickListener listener;

    public PollsListAdapter(final Context context, final ArrayList<PollInfo> polls, final View.OnClickListener listener){
        mContext = context;
        this.polls = polls;
        this.listener = listener;
    }

    @Override
    public PollsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_polls_list, viewGroup, false);
        return new PollsViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(PollsViewHolder holder, int i) {
        holder.setTitle(polls.get(i).getTitle());
        holder.setPrice(polls.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return polls.size();
    }
}
