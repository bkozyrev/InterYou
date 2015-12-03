package com.dtd.interyou.adapters.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtd.interyou.R;

public class PollsViewHolder extends RecyclerView.ViewHolder {
    public ImageView Image;
    public TextView Title;
    public TextView Price;

    public PollsViewHolder(View itemView, View.OnClickListener listener) {
        super(itemView);

        Image = (ImageView) itemView.findViewById(R.id.image_money);
        Title = (TextView) itemView.findViewById(R.id.title_polls);
        Price = (TextView) itemView.findViewById(R.id.price_polls);

        itemView.setOnClickListener(listener);
    }

    public void setTitle(String title){
        Title.setText(title);
    }

    public void setPrice(String price){
        Price.setText(price + " Руб");
    }
}
