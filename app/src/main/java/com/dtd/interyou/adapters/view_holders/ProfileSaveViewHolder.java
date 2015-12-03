package com.dtd.interyou.adapters.view_holders;

import android.view.View;
import android.widget.Button;

import com.dtd.interyou.R;

/**
 * Created by Egor on 13/08/15.
 */
public class ProfileSaveViewHolder  {

    private Button btnSave;

    public ProfileSaveViewHolder(View itemView, View.OnClickListener listener) {

        btnSave = (Button) itemView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(listener);

    }


}
