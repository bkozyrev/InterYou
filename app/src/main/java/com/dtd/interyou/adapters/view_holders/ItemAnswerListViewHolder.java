package com.dtd.interyou.adapters.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dtd.interyou.R;

/**
 * Created by 123 on 02.05.2015.
 */
public class ItemAnswerListViewHolder extends RecyclerView.ViewHolder{
    TextView Answer;
    Button BtnNo, BtnYes;

    public ItemAnswerListViewHolder(View itemView, View.OnClickListener listener) {
        super(itemView);

        Answer = (TextView) itemView.findViewById(R.id.answer);
        BtnYes = (Button) itemView.findViewById(R.id.btn_yes);
        BtnNo = (Button) itemView.findViewById(R.id.btn_no);

        BtnYes.setOnClickListener(listener);
        BtnNo.setOnClickListener(listener);
    }

    public void setAnswer(String answer){
        Answer.setText(answer);
    }
}
