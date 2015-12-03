package com.dtd.interyou.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.DividerItemDecoration;
import com.dtd.interyou.adapters.PollsListAdapter;
import com.dtd.interyou.fragments.LeftDrawerMenu.CategoriesFragment;
import com.dtd.interyou.model.GetListHandler;
import com.dtd.interyou.model.PassedPolls;
import com.dtd.interyou.model.PollInfo;
import com.dtd.interyou.model.Polls;

import java.util.ArrayList;

/**
 * Created by 123 on 25.07.2015.
 */
public class PollsListActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mPollsList;
    private ArrayList<PollInfo> mPolls;
    private int categoryNumber;
    private Polls poll;
    private ArrayList<Integer> pollsId;
    public static String POLL_KEY = "poll";
    public static String POLL_START_QUESTION_KEY = "start_question_key";

    @Override
    protected int getLayoutResourceIdentifier() {
        return R.layout.activity_polls_list;
    }

    @Override
    protected String getTitleToolBar() {
        return null;
    }

    @Override
    protected boolean getDisplayHomeAsUp() {
        return true;
    }

    @Override
    protected boolean getHomeButtonEnabled() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPollsList = (RecyclerView) findViewById(R.id.polls_list);
        mPollsList.setHasFixedSize(true);
        mPollsList.setItemAnimator(new DefaultItemAnimator());
        mPollsList.setLayoutManager(new LinearLayoutManager(mContext));
        mPollsList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        pollsId = new ArrayList<>();
        categoryNumber = getIntent().getIntExtra(CategoriesFragment.POLL_KEY, -1);
        pollsId = getIntent().getIntegerArrayListExtra(CategoriesFragment.POLLS_ID);

        loadPollsInfo();

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
                int position = mPollsList.getChildLayoutPosition(view);
                loadPoll(mPolls.get(position).getId());
        }
    }

    public void loadPollsInfo(){
        PollInfo.getAllPollsInfo(new GetListHandler<PollInfo>() {
            @Override
            public void done(ArrayList<PollInfo> data) {
                mPolls = data;
                /*for(int i = 0; i < mPolls.size(); i++){
                    mPolls.get(i).setId(pollsId.get(i));
                }*/
                PassedPolls.getAllPassedIds(new GetListHandler<PassedPolls>() {
                    @Override
                    public void done(ArrayList<PassedPolls> data) {
                        mPollsList.setAdapter(new PollsListAdapter(mContext, mPolls, PollsListActivity.this));
                    }

                    @Override
                    public void error(String responseError) {

                    }
                });
            }

            @Override
            public void error(String responseError) {

            }
        }, categoryNumber);
    }

    public void loadPoll(final int pollId){
        Polls.getPoll(new GetListHandler<Polls>() {
            @Override
            public void done(ArrayList<Polls> data) {
                poll = data.get(0);
                poll.setId(pollId);

                Intent intent = new Intent(mContext, SinglePollActivity.class);
                intent.putExtra(POLL_KEY, poll);
                intent.putExtra(POLL_START_QUESTION_KEY, 0);
                startActivity(intent);
            }

            @Override
            public void error(String responseError) {

            }
        }, pollId);
    }
}
