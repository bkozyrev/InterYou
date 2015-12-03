package com.dtd.interyou;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

/**
 * Created by 123 on 16.05.2015.
 */

//ScrollListener created by Boris

public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {
    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;
    private Context mContext;

    public HidingScrollListener(Context context){
        mContext = context;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        Toast.makeText(mContext, "Scrolled " + scrolledDistance, Toast.LENGTH_SHORT).show();

        if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
            onHide();
            controlsVisible = false;
            scrolledDistance = 0;
        } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
            onShow();
            controlsVisible = true;
            scrolledDistance = 0;
        }

        if((controlsVisible && dy>0) || (!controlsVisible && dy<0)) {
            scrolledDistance += dy;
        }
    }

    public abstract void onHide();
    public abstract void onShow();
}
