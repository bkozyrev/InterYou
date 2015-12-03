package com.dtd.interyou.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.DividerItemDecoration;
import com.dtd.interyou.adapters.ItemAnswerCheckAdapter;
import com.dtd.interyou.adapters.ItemAnswerDropDownAdapter;
import com.dtd.interyou.adapters.ItemAnswerImageHorizontalAdapter;
import com.dtd.interyou.adapters.ItemAnswerListAdapter;
import com.dtd.interyou.adapters.ItemAnswerRate1_5Adapter;
import com.dtd.interyou.adapters.NothingSelectedSpinnerAdapter;
import com.dtd.interyou.model.ItemPoll;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 123 on 09.05.2015.
 */
public class ItemPollFragment extends BaseFragment implements View.OnClickListener{

    private static final String ARGUMENT_POLL_NUMBER = "arg_poll_number";
    private static final String ARGUMENT_POLL_ITEM = "arg_poll_item";
    private static final String ARGUMENT_POLL_TOTAL_NUMBER = "arg_poll_total_number";
    private static final String ARGUMENT_POLL_TYPE = "arg_poll_type";

    private int pollNumber, totalNumber, pressedRateId = -1;
    private ItemPoll poll;

    private String pollType;

    private TextView mQuestion, mPageNumber;
    private RecyclerView mAnswersList;

    HashMap<Integer, TextView> rateMap;

    public static ItemPollFragment newInstance(int number, ItemPoll poll, int totalNumber, String pollType) {
        ItemPollFragment fragment = new ItemPollFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_POLL_NUMBER, number);
        arguments.putParcelable(ARGUMENT_POLL_ITEM, poll);
        arguments.putInt(ARGUMENT_POLL_TOTAL_NUMBER, totalNumber);
        arguments.putString(ARGUMENT_POLL_TYPE, pollType);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pollNumber = getArguments().getInt(ARGUMENT_POLL_NUMBER) + 1;
        poll = getArguments().getParcelable(ARGUMENT_POLL_ITEM);
        totalNumber = getArguments().getInt(ARGUMENT_POLL_TOTAL_NUMBER);
        pollType = getArguments().getString(ARGUMENT_POLL_TYPE);

        rateMap = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView next;
        View view = null;
        switch (pollType) {
            case "scale":
                view = inflater.inflate(R.layout.item_answer_scale_1_10, null);

                mQuestion = (TextView) view.findViewById(R.id.question_poll);
                mPageNumber = (TextView) view.findViewById(R.id.page_number);

                mQuestion.setText(poll.getTitle());
                mPageNumber.setText(pollNumber + "/" + totalNumber);

                TextView rate1 = (TextView)view.findViewById(R.id.rate_1);
                rateMap.put(rate1.getId(), rate1);
                rate1.setOnClickListener(this);
                TextView rate2 = (TextView)view.findViewById(R.id.rate_2);
                rateMap.put(rate2.getId(), rate2);
                rate2.setOnClickListener(this);
                TextView rate3 = (TextView)view.findViewById(R.id.rate_3);
                rateMap.put(rate3.getId(), rate3);
                rate3.setOnClickListener(this);
                TextView rate4 = (TextView)view.findViewById(R.id.rate_4);
                rateMap.put(rate4.getId(), rate4);
                rate4.setOnClickListener(this);
                TextView rate5 = (TextView)view.findViewById(R.id.rate_5);
                rateMap.put(rate5.getId(), rate5);
                rate5.setOnClickListener(this);
                TextView rate6 = (TextView)view.findViewById(R.id.rate_6);
                rateMap.put(rate6.getId(), rate6);
                rate6.setOnClickListener(this);
                TextView rate7 = (TextView)view.findViewById(R.id.rate_7);
                rateMap.put(rate7.getId(), rate7);
                rate7.setOnClickListener(this);
                TextView rate8 = (TextView)view.findViewById(R.id.rate_8);
                rateMap.put(rate8.getId(), rate8);
                rate8.setOnClickListener(this);
                TextView rate9 = (TextView)view.findViewById(R.id.rate_9);
                rateMap.put(rate9.getId(), rate9);
                rate9.setOnClickListener(this);
                TextView rate10 = (TextView)view.findViewById(R.id.rate_10);
                rateMap.put(rate10.getId(), rate10);
                rate10.setOnClickListener(this);

                next = (TextView)view.findViewById(R.id.next);
                next.setOnClickListener(this);

            break;
            case "matrix":
                view = inflater.inflate(R.layout.item_poll_list_type, null);

                mQuestion = (TextView) view.findViewById(R.id.question_poll);
                mPageNumber = (TextView) view.findViewById(R.id.page_number);

                mAnswersList = (RecyclerView) view.findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerListAdapter(mContext, poll.getQuestion(), this));

                mQuestion.setText(poll.getTitle());
                mPageNumber.setText(pollNumber + "/" + totalNumber);

                setRecyclerViewHeightBasedOnChildren(mAnswersList);

                next = (TextView)view.findViewById(R.id.next);
                next.setOnClickListener(this);

            break;
            case "multiplescale":
                view = inflater.inflate(R.layout.item_poll_list_type, null);

                mQuestion = (TextView) view.findViewById(R.id.question_poll);
                mPageNumber = (TextView) view.findViewById(R.id.page_number);

                mAnswersList = (RecyclerView) view.findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerRate1_5Adapter(mContext, poll.getQuestion(), this));

                mQuestion.setText(poll.getTitle());
                mPageNumber.setText(pollNumber + "/" + totalNumber);

                setRecyclerViewHeightBasedOnChildren(mAnswersList);

                next = (TextView)view.findViewById(R.id.next);
                next.setOnClickListener(this);

            break;

            case "multiplelist":
                view = inflater.inflate(R.layout.item_poll_list_type, null);

                mQuestion = (TextView) view.findViewById(R.id.question_poll);
                mPageNumber = (TextView) view.findViewById(R.id.page_number);

                mAnswersList = (RecyclerView) view.findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerDropDownAdapter(mContext, poll.getQuestion(), poll.getAnswers(), this));

                mQuestion.setText(poll.getTitle());
                mPageNumber.setText(pollNumber + "/" + totalNumber);

                setRecyclerViewHeightBasedOnChildren(mAnswersList);

                next = (TextView)view.findViewById(R.id.next);
                next.setOnClickListener(this);

                break;

            case "choice":
                view = inflater.inflate(R.layout.item_poll_list_type, null);

                mQuestion = (TextView) view.findViewById(R.id.question_poll);
                mPageNumber = (TextView) view.findViewById(R.id.page_number);

                mAnswersList = (RecyclerView) view.findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerCheckAdapter(mContext, poll.getQuestion(), this, poll.getOthers()));

                mQuestion.setText(poll.getTitle());
                mPageNumber.setText(pollNumber + "/" + totalNumber);

                setRecyclerViewHeightBasedOnChildren(mAnswersList);

                next = (TextView)view.findViewById(R.id.next);
                next.setOnClickListener(this);

                break;

            case "handwritten":
                view = inflater.inflate(R.layout.item_answer_comment, null);

                mQuestion = (TextView) view.findViewById(R.id.question_poll);
                mPageNumber = (TextView) view.findViewById(R.id.page_number);

                mQuestion.setText(poll.getTitle());
                mPageNumber.setText(pollNumber + "/" + totalNumber);

                next = (TextView)view.findViewById(R.id.next);
                next.setOnClickListener(this);

                break;

            case "list":
                view = inflater.inflate(R.layout.item_answer_big_drop_down, null);

                mQuestion = (TextView) view.findViewById(R.id.question_poll);
                mPageNumber = (TextView) view.findViewById(R.id.page_number);

                mQuestion.setText(poll.getTitle());
                mPageNumber.setText(pollNumber + "/" + totalNumber);

                Spinner spinner = (Spinner)view.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.spinner_textview, R.id.spinnerView, poll.getAnswers()){
                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent){
                        if (convertView == null){
                            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            convertView = vi.inflate(R.layout.spinner_dropdown, null);
                        }

                        TextView textView = (TextView) convertView.findViewById(R.id.spinnerTxt);
                        textView.setText(poll.getAnswers().get(position));

                        return convertView;
                    }
                };

                spinner.setAdapter(new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.spinner_row_nothing_selected,
                        mContext));

                break;

            case "PicHor":
                view = inflater.inflate(R.layout.item_poll_list_type, null);

                mQuestion = (TextView) view.findViewById(R.id.question_poll);
                mPageNumber = (TextView) view.findViewById(R.id.page_number);

                mAnswersList = (RecyclerView) view.findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerImageHorizontalAdapter(mContext, poll.getAnswers(), this));

                mQuestion.setText(poll.getTitle());
                mPageNumber.setText(pollNumber + "/" + totalNumber);

                setRecyclerViewHeightBasedOnChildren(mAnswersList);

                next = (TextView)view.findViewById(R.id.next);
                next.setOnClickListener(this);

                break;
        }

        return view;
    }

    @Override
    public void onClick(View view){
        TextView image;
        ImageView imageView;
        switch (pollType) {
            case "matrix":
                switch (view.getId()) {
                    case R.id.btn_yes:
                        Button btnYes = (Button) view;
                        RelativeLayout layout = (RelativeLayout) view.getParent();
                        Button btnNo = (Button) layout.findViewById(R.id.btn_no);
                        if (btnYes.getTag().equals("unpressed")) {
                            btnYes.setBackgroundResource(R.drawable.yes_blue);
                            btnYes.setTag("pressed");
                            btnNo.setBackgroundResource(R.drawable.no_white);
                            btnNo.setTag("unpressed");
                        } else {
                            btnYes.setBackgroundResource(R.drawable.yes_white);
                            btnYes.setTag("unpressed");
                        }
                        break;
                    case R.id.btn_no:
                        btnNo = (Button) view;
                        layout = (RelativeLayout) view.getParent();
                        btnYes = (Button) layout.findViewById(R.id.btn_yes);
                        if (btnNo.getTag().equals("unpressed")) {
                            btnNo.setBackgroundResource(R.drawable.no_red);
                            btnNo.setTag("pressed");
                            btnYes.setBackgroundResource(R.drawable.yes_white);
                            btnYes.setTag("unpressed");
                        } else {
                            btnNo.setBackgroundResource(R.drawable.no_white);
                            btnNo.setTag("unpressed");
                        }
                        break;
                }
                break;
            case "scale":
                if(view.getId() != R.id.next) {
                    image = (TextView) view;
                    if (view.getTag().equals("unpressed")) {
                        image.setBackgroundResource(R.drawable.circle_blue);
                        image.setTextColor(getResources().getColor(R.color.white));
                        view.setTag("pressed");
                        if (pressedRateId != -1) {
                            TextView previousPressedRate = rateMap.get(pressedRateId);
                            previousPressedRate.setBackgroundResource(R.drawable.circle);
                            previousPressedRate.setTextColor(getResources().getColor(R.color.gray));
                            previousPressedRate.setTag("unpressed");
                        }
                        pressedRateId = view.getId();
                    } else {
                        image.setBackgroundResource(R.drawable.circle);
                        image.setTextColor(getResources().getColor(R.color.gray));
                        view.setTag("unpressed");
                        pressedRateId = -1;
                    }
                }
                break;
            case "multiplescale":
                if(view.getId() != R.id.next) {
                    image = (TextView) view;
                    LinearLayout linearLayout = (LinearLayout) view.getParent();
                    ArrayList<TextView> arrayImages = new ArrayList<>();
                    boolean allImagesUnpressed = true;
                    TextView rate1 = (TextView) linearLayout.findViewById(R.id.rate_1);
                    TextView rate2 = (TextView) linearLayout.findViewById(R.id.rate_2);
                    TextView rate3 = (TextView) linearLayout.findViewById(R.id.rate_3);
                    TextView rate4 = (TextView) linearLayout.findViewById(R.id.rate_4);
                    TextView rate5 = (TextView) linearLayout.findViewById(R.id.rate_5);
                    arrayImages.add(rate1);
                    arrayImages.add(rate2);
                    arrayImages.add(rate3);
                    arrayImages.add(rate4);
                    arrayImages.add(rate5);

                    for (TextView textView : arrayImages) {
                        if (textView.getTag().equals("pressed") && textView.getId() == image.getId()) {
                            image.setBackgroundResource(R.drawable.circle);
                            image.setTextColor(getResources().getColor(R.color.gray));
                            image.setTag("unpressed");
                            allImagesUnpressed = false;
                            break;
                        } else if (textView.getTag().equals("pressed") && !(textView.getId() == image.getId())) {
                            textView.setBackgroundResource(R.drawable.circle);
                            textView.setTextColor(getResources().getColor(R.color.gray));
                            image.setBackgroundResource(R.drawable.circle_blue);
                            image.setTextColor(getResources().getColor(R.color.white));
                            textView.setTag("unpressed");
                            image.setTag("pressed");
                            allImagesUnpressed = false;
                            break;
                        }
                    }
                    if (allImagesUnpressed) {
                        image.setBackgroundResource(R.drawable.circle_blue);
                        image.setTextColor(getResources().getColor(R.color.white));
                        image.setTag("pressed");
                    }
                }
                break;
            case "choice":
                if(view.getId() != R.id.next) {
                    imageView = (ImageView) view;
                    if (view.getId() == R.id.image_check) {
                        if (view.getTag().equals("unpressed")) {
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_blue));
                            view.setTag("pressed");
                        } else {
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.check));
                            view.setTag("unpressed");
                        }
                    }
                }
                break;
            case "PicHor":
                if(view.getId() != R.id.next) {
                    imageView = (ImageView) view;
                    if (imageView.getTag().equals("unpressed")) {
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_blue));
                        imageView.setTag("pressed");
                    } else {
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_white));
                        imageView.setTag("unpressed");
                    }
                }
                break;
        }
   }

    public void setRecyclerViewHeightBasedOnChildren(RecyclerView recyclerView) {
        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();

        if(pollType.equals("choice")){
            if(poll.getOthers()) {
                params.height = 120 * (poll.getQuestionsCount() + 1) + 40;  //TODO hardcode
            }else{
                params.height = 120 * (poll.getQuestionsCount()) + 40;  //TODO hardcode
            }
        }
        else if(pollType.equals("PicHor")){
            params.height = 270 * poll.getAnswersCount();  //TODO hardcode
        }
        else{
            params.height = 120 * poll.getQuestionsCount();  //TODO hardcode
        }

        recyclerView.setLayoutParams(params);
    }
}
