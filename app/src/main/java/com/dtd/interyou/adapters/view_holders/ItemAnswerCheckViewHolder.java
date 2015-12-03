package com.dtd.interyou.adapters.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtd.interyou.R;

/**
 * Created by 123 on 03.07.2015.
 */
public class ItemAnswerCheckViewHolder extends RecyclerView.ViewHolder{

    TextView Answer;
    ImageView Check;
    EditText EditText;

    public ItemAnswerCheckViewHolder(View itemView, View.OnClickListener listener, boolean isPreLastPosition, boolean isLastPosition) {
        super(itemView);

        if(!isLastPosition && !isPreLastPosition){
            Answer = (TextView) itemView.findViewById(R.id.answer);
            Check = (ImageView) itemView.findViewById(R.id.image_check);
            Check.setOnClickListener(listener);
        }
        else if(isPreLastPosition){
            EditText = (EditText) itemView.findViewById(R.id.other_edit_text);
        }
        else{
            TextView next = (TextView)itemView.findViewById(R.id.next);
            next.setOnClickListener(listener);
        }
    }

    public void setAnswer(String answer){
        Answer.setText(answer);
    }
}
