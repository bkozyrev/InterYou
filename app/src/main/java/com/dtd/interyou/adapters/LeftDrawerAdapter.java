package com.dtd.interyou.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.LeftDrawerViewHolder;

import java.util.ArrayList;

/**
 * Created by Egor on 03.06.2015.
 */
public class LeftDrawerAdapter extends RecyclerView.Adapter<LeftDrawerViewHolder> {

    private Context mContext;
    private ArrayList<Drawable> mImages;
    private ArrayList<String> Titles;
    private View.OnClickListener listener;

    public LeftDrawerAdapter(Context mContext, ArrayList<Drawable> mImages, ArrayList<String> titles, View.OnClickListener listener) {
        this.mContext = mContext;
        this.mImages = mImages;
        Titles = titles;
        this.listener = listener;
    }

    @Override
    public LeftDrawerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_drawer_list, viewGroup, false);
        return new LeftDrawerViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(LeftDrawerViewHolder holder, int position) {
        holder.setImage(mImages.get(position));
        holder.setTitle(Titles.get(position));
    }

    @Override
    public int getItemCount() {
        return Titles.size();
    }
}
