package com.dtd.interyou.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dtd.interyou.fragments.ItemPollFragment;
import com.dtd.interyou.model.ItemPoll;
import com.dtd.interyou.model.Polls;

import java.util.ArrayList;

/**
 * Created by 123 on 09.05.2015.
 */
public class PollsPagerAdapter extends FragmentPagerAdapter {

    ArrayList<ItemPoll> Items;
    public PollsPagerAdapter(FragmentManager fm, Polls poll) {
        super(fm);
        Items = poll.getItemPolls();
    }

    @Override
    public Fragment getItem(int position){
        return ItemPollFragment.newInstance(position, Items.get(position), Items.size(), Items.get(position).getType());
    }

    @Override
    public int getCount() {
        return Items.size();
    }
}
