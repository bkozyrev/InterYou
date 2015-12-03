package com.dtd.interyou.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtd.interyou.fragments.PersonalTabMenu.TabHistory;
import com.dtd.interyou.fragments.PersonalTabMenu.TabMoney;
import com.dtd.interyou.fragments.PersonalTabMenu.TabProfile;

/**
 * Created by Egor on 08.04.2015.
 */
public class CustomFragmentPagerAdapter extends FragmentStatePagerAdapter {
    FragmentManager fm;
    private  int NUM_PAGES;

    public CustomFragmentPagerAdapter(FragmentManager fm, int num_pages) {
        super(fm);
        this.fm = fm;
        this.NUM_PAGES = num_pages;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TabProfile.newInstance();
            case 1:
                return TabMoney.newInstance();
            case 2:
                return TabHistory.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {

            String str = "";
            switch(position){
                case 0:
                    str = "Профиль";
                    break;
                case 1:
                    str = "Средства";
                    break;
                case 2:
                    str = "История";
                    break;
            }
            return str;
    }

}
