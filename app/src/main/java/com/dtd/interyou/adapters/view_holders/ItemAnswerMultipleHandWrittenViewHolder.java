package com.dtd.interyou.adapters.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dtd.interyou.R;

/**
 * Created by 123 on 30.07.2015.
 */
public class ItemAnswerMultipleHandWrittenViewHolder extends RecyclerView.ViewHolder{

    TextView Question;
    EditText Answer;

    public ItemAnswerMultipleHandWrittenViewHolder(View itemView, final Context context, View.OnClickListener listener, boolean isLastItem) {
        super(itemView);

        if(!isLastItem) {
            Question = (TextView) itemView.findViewById(R.id.answer);
            Answer = (EditText) itemView.findViewById(R.id.edit_text);
        }else{
            TextView next = (TextView)itemView.findViewById(R.id.next);
            next.setOnClickListener(listener);
        }
    }

    public void setQuestion(String question){
        Question.setText(question);
    }
}
