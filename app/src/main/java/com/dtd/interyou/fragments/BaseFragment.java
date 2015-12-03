package com.dtd.interyou.fragments;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.dtd.interyou.activities.BaseActivity;

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected Toolbar mToolBarFragment;


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mContext = getActivity();
        //mToolBarFragment = (Toolbar) getActivity().findViewById(R.id.toolbar);//TODO #lastclear
        mToolBarFragment = ((BaseActivity) getActivity()).getToolBar();
    }

}
