package com.dtd.interyou.adapters.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dtd.interyou.R;

/**
 * Created by 123 on 03.07.2015.
 */
public class ItemAnswerImageHorizontalViewHolder extends RecyclerView.ViewHolder{

    ImageView imageAnswer, imageCheck;

    public ItemAnswerImageHorizontalViewHolder(View itemView, View.OnClickListener listener) {
        super(itemView);

        imageAnswer = (ImageView)itemView.findViewById(R.id.img_answer);
        imageCheck = (ImageView)itemView.findViewById(R.id.image_check);

        imageCheck.setOnClickListener(listener);
    }

    public void setImage(Context context, String url){
        Glide.with(context)
             .load(url)
             .into(imageAnswer);
    }
}
