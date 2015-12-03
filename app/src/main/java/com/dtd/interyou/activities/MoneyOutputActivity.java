package com.dtd.interyou.activities;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.DividerItemDecoration;
import com.dtd.interyou.adapters.MoneyListAdapter;
import com.dtd.interyou.model.ItemMoney;

import java.util.ArrayList;

/**
 * Created by Egor on 03.06.2015.
 */
public class MoneyOutputActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mList;
    LinearLayout linearLayoutContent1, linearLayoutContent2, linearLayoutContent3, LinearLayoutFirst, LinearLayoutSecond, LinearLayoutThird;
    RelativeLayout relativeLayoutContent1;
    ImageView Arrow1, Arrow2, Arrow3;

    @Override
    protected int getLayoutResourceIdentifier() {
        return R.layout.activity_out_money;
    }

    @Override
    protected String getTitleToolBar() {
        return null;
    }

    @Override
    protected boolean getDisplayHomeAsUp() {
        return false;
    }

    @Override
    protected boolean getHomeButtonEnabled() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleToolBarColor(R.color.blue);

        mList = (RecyclerView) findViewById(R.id.rv_content2);
        mList.setHasFixedSize(true);
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setLayoutManager(new LinearLayoutManager(mContext));
        mList.addItemDecoration(new DividerItemDecoration(MoneyOutputActivity.this, DividerItemDecoration.VERTICAL_LIST));

        ArrayList<ItemMoney> money = new ArrayList<>();
        money.add(new ItemMoney(null, "Код скидки в «KFC»", "100 Р"));
        money.add(new ItemMoney(null, "Бонусы в «Спортмастер»", "100 Р"));
        money.add(new ItemMoney(null, "Код скидки в «IVI.ru»", "100 Р"));

        mList.setAdapter(new MoneyListAdapter(mContext, money, MoneyOutputActivity.this));



        linearLayoutContent1 = (LinearLayout) findViewById(R.id.ll_content1);
        linearLayoutContent2 = (LinearLayout) findViewById(R.id.ll_content2);
        linearLayoutContent3 = (LinearLayout) findViewById(R.id.ll_content3);
        LinearLayoutFirst = (LinearLayout) findViewById(R.id.linearLayout1);
        LinearLayoutSecond = (LinearLayout) findViewById(R.id.linearLayout2);
        LinearLayoutThird = (LinearLayout) findViewById(R.id.linearLayout3);
        Arrow1 = (ImageView) findViewById(R.id.arrow1);
        Arrow2 = (ImageView) findViewById(R.id.arrow2);
        Arrow3 = (ImageView) findViewById(R.id.arrow3);

        LinearLayoutFirst.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (linearLayoutContent1.getVisibility()==View.GONE){
                    expand(linearLayoutContent1);
                    Arrow1.setImageDrawable(mContext.getResources().getDrawable(R.drawable.money_out_dropdown_arrow_select));
                }else{
                    collapse(linearLayoutContent1);
                    Arrow1.setImageDrawable(mContext.getResources().getDrawable(R.drawable.money_out_dropdown_arrow_nonselect));
                }
            }
        });

        LinearLayoutSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutContent2.getVisibility()==View.GONE){
                    expand(linearLayoutContent2);
                    Arrow2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.money_out_dropdown_arrow_select));
                }else{
                    collapse(linearLayoutContent2);
                    Arrow2.setImageDrawable(mContext.getResources().getDrawable(R.drawable.money_out_dropdown_arrow_nonselect));
                }
            }
        });

        LinearLayoutThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutContent3.getVisibility()==View.GONE){
                    expand(linearLayoutContent3);
                    Arrow3.setImageDrawable(mContext.getResources().getDrawable(R.drawable.money_out_dropdown_arrow_select));
                }else{
                    collapse(linearLayoutContent3);
                    Arrow3.setImageDrawable(mContext.getResources().getDrawable(R.drawable.money_out_dropdown_arrow_nonselect));
                }
            }
        });





    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.money_cell:
                final Dialog dialog = new Dialog(MoneyOutputActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.discount_dialog);

                // set the custom dialog components - text, image and button

                Button dialogButton = (Button) dialog.findViewById(R.id.button);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;
        }
    }


    private void expand(View v) {
        //set Visible
        v.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(widthSpec, heightSpec);

        ValueAnimator mAnimator = slideAnimator(v, 0, v.getMeasuredHeight());
        mAnimator.start();
    }

    private void collapse(final View v) {
        int finalHeight = v.getHeight();

        ValueAnimator mAnimator = slideAnimator(v, finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimator.start();
    }

    private ValueAnimator slideAnimator(final View v, int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

}
