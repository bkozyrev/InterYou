package com.dtd.interyou.adapters.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dtd.interyou.R;
import com.dtd.interyou.activities.SinglePollActivity;
import com.dtd.interyou.adapters.NothingSelectedSpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by 123 on 02.07.2015.
 */
public class ItemAnswerDropDownViewHolder extends RecyclerView.ViewHolder {

    TextView Answer;
    Spinner spinner;
    ArrayList<String> Answers;

    public ItemAnswerDropDownViewHolder(View itemView, final Context context, View.OnClickListener listener, ArrayList<String> answers, boolean isLastItem) {
        super(itemView);

        if(!isLastItem) {
            Answer = (TextView) itemView.findViewById(R.id.answer);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
            Answers = answers;

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_textview, R.id.spinnerView, Answers) {
                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        convertView = vi.inflate(R.layout.spinner_dropdown, null);
                    }

                    TextView textView = (TextView) convertView.findViewById(R.id.spinnerTxt);
                    textView.setText(Answers.get(position));

                    return convertView;
                }
            };

            spinner.setAdapter(new NothingSelectedSpinnerAdapter(
                    adapter,
                    R.layout.spinner_row_nothing_selected,
                    context));

            SinglePollActivity.setSpinnerListener(spinner);

        }else{
            TextView next = (TextView)itemView.findViewById(R.id.next);
            next.setOnClickListener(listener);
        }
    }

    public void setQuestion(String answer){
        Answer.setText(answer);
    }
}
