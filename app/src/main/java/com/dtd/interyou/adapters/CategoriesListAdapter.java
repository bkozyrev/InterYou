package com.dtd.interyou.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.CategoriesViewHolder;
import com.dtd.interyou.model.Category;

import java.util.ArrayList;

/**
 * Created by 123 on 02.05.2015.
 */
public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private final Context mContext;
    private ArrayList<Category> categories;
    private final View.OnClickListener listener;
    private String url = "http://interyou.tk/images/category/";

    public CategoriesListAdapter(final Context context, final ArrayList<Category> categories, final View.OnClickListener listener){
        mContext = context;
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_polls_list, viewGroup, false);
        return new CategoriesViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int i) {
        holder.setTitle(categories.get(i).getTitle());
        holder.setCount(categories.get(i).getPollsCount());
        holder.setImage(mContext, categories.get(i).getUrl());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
