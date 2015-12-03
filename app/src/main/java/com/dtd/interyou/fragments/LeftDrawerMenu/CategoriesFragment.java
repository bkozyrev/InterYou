package com.dtd.interyou.fragments.LeftDrawerMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtd.interyou.R;
import com.dtd.interyou.activities.PollsListActivity;
import com.dtd.interyou.adapters.CategoriesListAdapter;
import com.dtd.interyou.adapters.DividerItemDecoration;
import com.dtd.interyou.fragments.BaseFragment;
import com.dtd.interyou.model.Category;
import com.dtd.interyou.model.GetListHandler;

import java.util.ArrayList;

/**
 * Created by 123 on 02.05.2015.
 */
public class CategoriesFragment extends BaseFragment implements View.OnClickListener{

    private RecyclerView mCategoriesList;
    private ArrayList<Category> mCategories;
    public static String POLL_KEY = "poll_type";
    public static String POLLS_ID = "polls_id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_polls, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mCategoriesList = (RecyclerView) view.findViewById(R.id.polls_list);
        mCategoriesList.setHasFixedSize(true);
        mCategoriesList.setItemAnimator(new DefaultItemAnimator());
        mCategoriesList.setLayoutManager(new LinearLayoutManager(mContext));
        mCategoriesList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        loadCategories();

        /*for(int i = 0; i < 24; i++){
            if(i < 3) {
                mPolls.add(new Polls("Опрос - список ", i + 1 + "00", "url", "List"));
            }
            else if(i >= 3 && i < 6){
                mPolls.add(new Polls("Опрос - шкала 1-10", i + 1 + "00", "url", "Rate1_10"));
            }
            else if(i >= 6 && i < 9){
                mPolls.add(new Polls("Опрос - шкала 1-5", i + 1 + "00", "url", "Rate1_5"));
            }
            else if(i >= 9 && i < 12){
                mPolls.add(new Polls("Опрос - dropdown", i + 1 + "00", "url", "DropDown"));
            }
            else if(i >= 12 && i < 15){
                mPolls.add(new Polls("Опрос - check", i + 1 + "00", "url", "Check"));
            }
            else if(i >= 15 && i < 18){
                mPolls.add(new Polls("Опрос - коммент", i + 1 + "00", "url", "Comment"));
            }
            else if(i >= 18 && i < 21){
                mPolls.add(new Polls("Опрос - big dropdown", i + 1 + "00", "url", "BigDropDown"));
            }
            else{
                mPolls.add(new Polls("Опрос - картинки, тип 1", i + 1 + "00", "url", "PicHor"));
            }
        }*/
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.polls_rr:
                int position = mCategoriesList.getChildLayoutPosition(view);
                Intent intent = new Intent(mContext, PollsListActivity.class);
                intent.putExtra(POLL_KEY, mCategories.get(position).getId());
                intent.putExtra(POLLS_ID, mCategories.get(position).getPollsId());
                startActivity(intent);
                break;
        }
    }

    public void loadCategories(){
        Category.getAllCategories(new GetListHandler<Category>() {
            @Override
            public void done(ArrayList<Category> data) {
                mCategories = data;
                mCategoriesList.setAdapter(new CategoriesListAdapter(mContext, mCategories, CategoriesFragment.this));
            }

            @Override
            public void error(String responseError) {
                Log.d("Error", responseError);
            }
        });
    }
}
