package com.dtd.interyou.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.dtd.interyou.R;

public abstract class BaseActivity extends ActionBarActivity {

    protected Toolbar mToolBar;
    protected Context mContext;

//TODO #fonts
//    @Override
//    protected void attachBaseContext(Context newBase){
//        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CalligraphyConfig.initDefault("fonts/Mark Simonson - Proxima Nova Regular.otf");TODO #fonts
        setContentView(getLayoutResourceIdentifier());
        loadComponents();
        loadInfoView();
        initializeToolBar();

    }

    private void loadComponents() {
        mContext = getApplicationContext();
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void loadInfoView() {
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
        }
    }

    protected void initializeToolBar() {
        if (mToolBar != null) {
            mToolBar.setTitle(getTitleToolBar());
            getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUp());
            getSupportActionBar().setHomeButtonEnabled(getHomeButtonEnabled());
        }
    }

    protected abstract int getLayoutResourceIdentifier();

    protected abstract String getTitleToolBar();

    protected void setTitleToolBarColor(int colorId) {
        mToolBar.setBackgroundColor(getResources().getColor(colorId));
    }

    public Toolbar getToolBar(){
        return mToolBar;
    }

    protected abstract boolean getDisplayHomeAsUp();

    protected abstract boolean getHomeButtonEnabled();

}