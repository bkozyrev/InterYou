package com.dtd.interyou.adapters;

import android.support.v7.widget.RecyclerView;

public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {
  private static final int HIDE_THRESHOLD = 20;
  private int scrolledDistance = 0;
  private boolean controlsVisible = true;
  
  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
   
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

  @Override
  public void onScrollStateChanged(RecyclerView recyclerView, int newState)
  {

        /*if (newState == recyclerView.SCROLL_STATE_DRAGGING){
            onStop();
        }*/
//        if (newState == OnScrollListener){//TODO state stop
//            onStop();
//        }
        /*if (newState == recyclerView.SCROLL_STATE_SETTLING){
            onStop()
        }*/

  }

  public abstract void onStop();
  
  public abstract void onHide();
  public abstract void onShow();
 
}