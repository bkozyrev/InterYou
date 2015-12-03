package com.dtd.interyou.adapters.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtd.interyou.R;
import com.dtd.interyou.model.Operation;

/**
 * Created by Egor on 02.06.2015.
 */
public class OperationViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private ImageView Image;
    private TextView Date;
    private TextView Title;
    private TextView Sum;
    private View.OnClickListener listener;

    public OperationViewHolder(View itemView, Context context, View.OnClickListener listener) {
        super(itemView);
        mContext = context;
        this.listener = listener;

        Image = (ImageView) itemView.findViewById(R.id.image);
        Date = (TextView) itemView.findViewById(R.id.date);
        Title = (TextView) itemView.findViewById(R.id.title);
        Sum = (TextView) itemView.findViewById(R.id.sum);

        itemView.setOnClickListener(listener);

    }

    public void setTitle(Operation.Type title){

        int id = mContext.getResources().getIdentifier("ic_type_operation_" + title.toString().toLowerCase(), "drawable", mContext.getPackageName());
        if (id != 0)
            Image.setImageResource(id);
        switch(Operation.Type.valueOf(title.toString())){
            case OUTPUT:
                Sum.setText("-");
                Title.setText("Вывод");
                break;
            case INPUT:
                Sum.setText("+");
                Title.setText("Ввод");
                break;
            case EXCHANGE:
                Title.setText("Обмен");
                break;
        }
    }

    public void setDate(String date){
        Date.setText(date);
    }

    public void setSum(String sum){
        Sum.setText(Sum.getText() + sum);
    }

}
