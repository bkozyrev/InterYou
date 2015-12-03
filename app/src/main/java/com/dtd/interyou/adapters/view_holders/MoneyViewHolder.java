package com.dtd.interyou.adapters.view_holders;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtd.interyou.R;

/**
 * Created by Egor on 01.06.2015.
 */
public class MoneyViewHolder extends RecyclerView.ViewHolder {

    private ImageView Image;
    private TextView Title;
    private TextView Sum;

    public MoneyViewHolder(View itemView, View.OnClickListener listener) {
        super(itemView);
        Image = (ImageView) itemView.findViewById(R.id.image_money);
        Title = (TextView) itemView.findViewById(R.id.title_money);
        Sum = (TextView) itemView.findViewById(R.id.sum_money);

        itemView.setOnClickListener(listener);
    }

    public void setTitle(String title){
        Title.setText(title);
    }

    public void setImage(Drawable resource){
        Image.setImageDrawable(resource);
    }

    public void setSum(String sum){Sum.setText(sum);}

}
