package com.dtd.interyou.adapters.view_holders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtd.interyou.R;
import com.dtd.interyou.Utils.CustomSpinner;
import com.dtd.interyou.adapters.ProfileListAdapter;

import java.util.ArrayList;

/**
 * Created by Egor on 02.06.2015.
 */
public class ProfileChooseViewHolder {

    private Context mContext;
    private CustomSpinner Value;
    private TextView Title;
    private String position;


    private ArrayAdapter<String> adapter; //test

    public ProfileChooseViewHolder(View itemView, Context context, final ProfileListAdapter.GetValue callback, String position) {
        mContext = context;

        Value = (CustomSpinner) itemView.findViewById(R.id.spinner);

        this.position = position;

        Title = (TextView) itemView.findViewById(R.id.title);
        final ImageView Arrow = (ImageView) itemView.findViewById(R.id.arrow);
        final Animation rotatecounterclockwise = AnimationUtils.loadAnimation(mContext, R.anim.rotate_arrow_counter);
        rotatecounterclockwise.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final Animation rotateclockwise = AnimationUtils.loadAnimation(mContext, R.anim.rotate_arrow);

        Value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                callback.saveValue(String.valueOf(position));

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    if (position != adapter.getCount() - 1 && (((Drawable) Value.getBackground()).getConstantState().equals(mContext.getResources().getDrawable(R.drawable.roun_corn_backwhite_strokegray).getConstantState()))) {
                        Value.setBackgroundResource(R.drawable.roun_corn_allblue);
                        ((TextView) view.findViewById(android.R.id.text1)).setTextColor(mContext.getResources().getColor(R.color.white));
                        Arrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dropdown_edit));
                    } else if (position == adapter.getCount() - 1 && ((Drawable) Value.getBackground()).getConstantState().equals(mContext.getResources().getDrawable(R.drawable.roun_corn_allblue).getConstantState())) {
                        Value.setBackgroundResource(R.drawable.roun_corn_backwhite_strokegray);
                        ((TextView) view.findViewById(android.R.id.text1)).setTextColor(mContext.getResources().getColor(R.color.text_color_secondary));
                        Arrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dropdown_arrow));
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (position != adapter.getCount() - 1 && (((Drawable) Value.getBackground()).getConstantState().equals(mContext.getDrawable(R.drawable.roun_corn_backwhite_strokegray).getConstantState()))) {
                        Value.setBackgroundResource(R.drawable.roun_corn_allblue);
                        ((TextView) view.findViewById(android.R.id.text1)).setTextColor(mContext.getResources().getColor(R.color.white));
                        Arrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dropdown_edit));
                    } else if (position == adapter.getCount() - 1 && ((Drawable) Value.getBackground()).getConstantState().equals(mContext.getDrawable(R.drawable.roun_corn_allblue).getConstantState())) {
                        Value.setBackgroundResource(R.drawable.roun_corn_backwhite_strokegray);
                        ((TextView) view.findViewById(android.R.id.text1)).setTextColor(mContext.getResources().getColor(R.color.text_color_secondary));
                        Arrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dropdown_arrow));
                    }
                }
                /*Value.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.roun_corn_allblue));
                Log.d("Compare result: ", String.valueOf(((Drawable) Value.getBackground()).getConstantState().equals(mContext.getResources().getDrawable(R.drawable.roun_corn_allblue).getConstantState())));
                Log.d("First: ", String.valueOf(Value.getDrawableState()));
                Log.d("Second: ", String.valueOf(mContext.getResources().getDrawable(R.drawable.roun_corn_allblue).getConstantState()));*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Value.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onSpinnerOpened() {
//                Arrow.startAnimation(rotateclockwise);
                if (!Arrow.getDrawable().getConstantState().equals(mContext.getResources().getDrawable(R.drawable.dropdown_edit).getConstantState())) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    Bitmap bitmap = ((BitmapDrawable) Arrow.getDrawable()).getBitmap();
                    Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    Arrow.setImageBitmap(newBitmap);
                }
            }

            @Override
            public void onSpinnerClosed() {
                //Arrow.startAnimation(rotatecounterclockwise);
                if (!Arrow.getDrawable().getConstantState().equals(mContext.getResources().getDrawable(R.drawable.dropdown_edit).getConstantState())) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(-90);
                    Bitmap bitmap = ((BitmapDrawable) Arrow.getDrawable()).getBitmap();
                    Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    Arrow.setImageBitmap(newBitmap);
                }
            }
        });


    }

    public String getChoice(){
        return Value.getSelectedItem().toString();
    }

    public void setChoice(int position) {
        Value.setSelection(position);
    }

    public void setTitle(String title){
        Title.setText(title);
    }

    public void setVariants(ArrayList<String> variants){
        adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()-1) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setTextColor(mContext.getResources().getColor(R.color.text_color_secondary));
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(position)); //"Hint to be displayed"
                    ((TextView) v).setGravity(Gravity.CENTER);
                }else{
                    ((TextView)v.findViewById(android.R.id.text1)).setTextColor(mContext.getResources().getColor(R.color.white));
                }

                return v;
            }

            public View getDropDownView(int position, View convertView,ViewGroup parent) {

                View v = super.getDropDownView(position, convertView,parent);

                ((TextView) v).setGravity(Gravity.CENTER);

                return v;

            }

            @Override
            public int getCount() {
                return super.getCount(); // you dont display last item. It is used as hint.
            }

        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(variants);
        adapter.add("Выбрать");//TODO hardcode

        Value.setAdapter(adapter);

        if (position == null) Value.setSelection(adapter.getCount()-1); //display hint
        else Value.setSelection(Integer.parseInt(position));

    }
}
