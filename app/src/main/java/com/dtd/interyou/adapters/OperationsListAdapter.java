package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.OperationViewHolder;
import com.dtd.interyou.model.Operation;

import java.util.ArrayList;

/**
 * Created by Egor on 02.06.2015.
 */
public class OperationsListAdapter extends RecyclerView.Adapter<OperationViewHolder>{

    private final Context mContext;
    private ArrayList<Operation> mItems;
    private final View.OnClickListener listener;


    public OperationsListAdapter(final Context context, final ArrayList<Operation> itemMoneys, final View.OnClickListener listener){
        mContext = context;
        this.mItems = itemMoneys;
        this.listener = listener;
    }

    @Override
    public OperationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_operation_list, viewGroup, false);
        return new OperationViewHolder(v, mContext, listener);
    }

    @Override
    public void onBindViewHolder(OperationViewHolder holder, int i) {
        holder.setTitle(mItems.get(i).getType());
        holder.setDate(mItems.get(i).getDate());
        holder.setSum(mItems.get(i).getMoney());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
