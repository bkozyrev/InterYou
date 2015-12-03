package com.dtd.interyou.adapters.view_holders;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtd.interyou.R;

/**
 * Created by Egor on 03.06.2015.
 */
public class LeftDrawerViewHolder extends RecyclerView.ViewHolder {

    private ImageView Image;
    private TextView Title;

    public LeftDrawerViewHolder(View itemView, View.OnClickListener listener) {
        super(itemView);
        Image = (ImageView) itemView.findViewById(R.id.image);
        Title = (TextView) itemView.findViewById(R.id.title);

        itemView.setOnClickListener(listener);
    }

    public void setTitle(String title){
        Title.setText(title);
    }

    public void setImage(Drawable drawable){
        Image.setImageDrawable(drawable);
    }
}
