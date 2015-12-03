package com.dtd.interyou.adapters.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dtd.interyou.R;

/**
 * Created by 123 on 02.07.2015.
 */
public class ItemAnswerRate1_5ViewHolder extends RecyclerView.ViewHolder {

    TextView Question;
    TextView Rate1, Rate2, Rate3, Rate4, Rate5;

    public ItemAnswerRate1_5ViewHolder(View itemView, View.OnClickListener listener, boolean isLastPosition) {
        super(itemView);

        if(!isLastPosition) {
            Question = (TextView) itemView.findViewById(R.id.question);

            Rate1 = (TextView) itemView.findViewById(R.id.rate_1);
            Rate2 = (TextView) itemView.findViewById(R.id.rate_2);
            Rate3 = (TextView) itemView.findViewById(R.id.rate_3);
            Rate4 = (TextView) itemView.findViewById(R.id.rate_4);
            Rate5 = (TextView) itemView.findViewById(R.id.rate_5);

            Rate1.setOnClickListener(listener);
            Rate2.setOnClickListener(listener);
            Rate3.setOnClickListener(listener);
            Rate4.setOnClickListener(listener);
            Rate5.setOnClickListener(listener);
        }else{
            TextView next = (TextView)itemView.findViewById(R.id.next);
            next.setOnClickListener(listener);
        }
    }

    public void setQuestion(String question){
        Question.setText(question);
    }
}
