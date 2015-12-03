package com.dtd.interyou.adapters.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtd.interyou.R;
import com.dtd.interyou.model.Polls;

public class HistoryPollsGridViewHolder extends RecyclerView.ViewHolder {
    private ImageView Image;
    private TextView Date;
    private TextView Title;
    private TextView Price;
    private Context mContext;


    public HistoryPollsGridViewHolder(View itemView, Context context, View.OnClickListener listener) {
        super(itemView);

        Image = (ImageView) itemView.findViewById(R.id.image);
        Date = (TextView) itemView.findViewById(R.id.date);
        Title = (TextView) itemView.findViewById(R.id.title);
        Price = (TextView) itemView.findViewById(R.id.price);
        mContext = context;

        itemView.setOnClickListener(listener);
    }

    public void setTitle(String title){
        Title.setText(title);
    }

    public void setDate(String date){
        Date.setText(date);
    }

    public void setPrice(String price){
        Price.setText(price  + "Р");
    }

    public void setImage(Polls.Category category) {
        int id = 0;
        id = mContext.getResources().getIdentifier("ic_category_" + category.toString().toLowerCase(), "drawable", mContext.getPackageName());
        if (id != 0){
            Image.setImageResource(id);
        }else
            Image.setImageResource(R.drawable.ic_category_sport);
    }

}