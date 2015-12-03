package com.dtd.interyou.adapters.view_holders;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dtd.interyou.R;

/**
 * Created by Egor on 02.06.2015.
 */
public class ProfileWriteViewHolder {

    private EditText Value;
    private TextView Title;



    public ProfileWriteViewHolder(View itemView) {

        Value = (EditText) itemView.findViewById(R.id.edit);
        Title = (TextView) itemView.findViewById(R.id.title);
    }


    public void setTitle(String title){
        Title.setText(title);
    }

    public String getValue(){
        return Value.getText().toString();
    }


}
