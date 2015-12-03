package com.dtd.interyou.adapters.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dtd.interyou.R;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {
    public ImageView Image, Logo;
    public TextView Title;
    public TextView Count;

    public CategoriesViewHolder(View itemView, View.OnClickListener listener) {
        super(itemView);

        Image = (ImageView) itemView.findViewById(R.id.image_money);
        Title = (TextView) itemView.findViewById(R.id.title_polls);
        Count = (TextView) itemView.findViewById(R.id.price_polls);

        itemView.setOnClickListener(listener);
    }

    public void setTitle(String title){
        Title.setText(title);
    }

    public void setCount(int count){
        Count.setText("" + count);
    }

    public void setImage(Context context, String url){
        Glide.with(context).load(url).into(Image);
        Log.d("CategoriesViewHolder", url);
    }
}
