package com.dtd.interyou.fragments.PersonalTabMenu;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.DividerItemDecoration;
import com.dtd.interyou.adapters.HistoryPollsListAdapter;
import com.dtd.interyou.fragments.BaseFragment;
import com.dtd.interyou.model.Polls;

import java.util.ArrayList;

/**
 * Created by Egor on 15.04.2015.
 */
public class TabHistory extends BaseFragment implements View.OnClickListener  {//TODO name of class

    private RecyclerView mList;


    public static TabHistory newInstance() {
        return new TabHistory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_history, container, false);
        mContext = view.getContext();

        mList = (RecyclerView) view.findViewById(R.id.polls_history_list);
        mList.setHasFixedSize(true);
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setLayoutManager(new LinearLayoutManager(mContext));
        mList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        //CustomScrollView scroll = (CustomScrollView) view.findViewById(R.id.tab_history_scroll);
        //scroll.setEnableScrolling(true);

        ArrayList<Polls> polls = new ArrayList<>();//TODO hardcode
        polls.add(new Polls("11.02.15", "Опрос №1", "+50", null, Polls.Category.SPORT));
        polls.add(new Polls("12.02.15", "Опрос №2", "+100", null, Polls.Category.ECONOMY));
        polls.add(new Polls("13.02.15", "Опрос №3", "+150", null, Polls.Category.FASHION));
        polls.add(new Polls("14.02.15", "Опрос №4", "+50", null, Polls.Category.FOOD));
        polls.add(new Polls("15.02.15", "Опрос №5", "+100", null, Polls.Category.HABITS));
        polls.add(new Polls("16.02.15", "Опрос №6", "+50", null, Polls.Category.SPORT));
        polls.add(new Polls("17.02.15", "Опрос №7", "+150", null, Polls.Category.MEDIA));
        polls.add(new Polls("18.02.15", "Опрос №8", "+50", null, Polls.Category.PEOPLE_AND_SOCIETY));
        polls.add(new Polls("19.02.15", "Опрос №9", "+100", null, Polls.Category.PEOPLE_AND_RELATIONSHIPS));
        polls.add(new Polls("20.02.15", "Опрос №10", "+150", null, Polls.Category.POLITICS));

        mList.setAdapter(new HistoryPollsListAdapter(mContext, polls, TabHistory.this));

        return view;
    }


    @Override
    public void onClick(View v) {

    }

}
