package com.dtd.interyou.fragments.LeftDrawerMenu;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.SlidingTabLayout;
import com.dtd.interyou.adapters.CustomFragmentPagerAdapter;
import com.dtd.interyou.fragments.BaseFragment;

/**
 * Created by Egor on 21.04.2015.
 */
public class PersonalAreaFragment extends BaseFragment {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    public PersonalAreaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_personal_area, container, false);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new CustomFragmentPagerAdapter(getActivity().getSupportFragmentManager(), 3));
        //mViewPager.setAdapter(new SamplePagerAdapter());

        // Give the SlidingTabLayout the ViewPager, this must be
        // done AFTER the ViewPager has had it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.blue));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        mSlidingTabLayout.setDividerColors(getResources().getColor(R.color.white));
        mSlidingTabLayout.setDividerColors(getResources().getColor(R.color.white));
        ////


    }

}
