package com.dtd.interyou.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dtd.interyou.InterYou;
import com.dtd.interyou.R;
import com.dtd.interyou.adapters.DividerItemDecoration;
import com.dtd.interyou.adapters.ItemAnswerCheckAdapter;
import com.dtd.interyou.adapters.ItemAnswerDropDownAdapter;
import com.dtd.interyou.adapters.ItemAnswerImageHorizontalAdapter;
import com.dtd.interyou.adapters.ItemAnswerMultipleHandWrittenAdapter;
import com.dtd.interyou.adapters.ItemAnswerRate1_5Adapter;
import com.dtd.interyou.adapters.NothingSelectedSpinnerAdapter;
import com.dtd.interyou.model.Polls;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by 123 on 02.05.2015.
 */
public class SinglePollActivity extends BaseActivitySinglePoll implements View.OnClickListener{

    private int currentQuestion;
    private Polls poll;
    private TextView mQuestion, mPageNumber;
    private static RecyclerView mAnswersList;
    private HashMap<Integer, TextView> rateMap;
    private TextView next;
    private int pressedRateId = -1;
    private Spinner spinner;
    private int multipleScaleItemsPressCount = 0;
    private JSONArray jsonArray;
    private String url = "http://interyou.tk:8888/api/mobile/voting/votes";
    public static int[] answersIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        poll = getIntent().getParcelableExtra(PollsListActivity.POLL_KEY);
        currentQuestion = getIntent().getIntExtra(PollsListActivity.POLL_START_QUESTION_KEY, -1);

        rateMap = new HashMap<>();
        answersIds = new int[poll.getItemPolls().get(currentQuestion).getQuestionsCount()];

        switch (poll.getItemPolls().get(currentQuestion).getType()) {
            case "scale":
                if(poll.getItemPolls().get(currentQuestion).getAnswersCount() > 5) {
                    setContentView(R.layout.item_answer_scale_1_10);
                }else{
                    setContentView(R.layout.item_answer_scale_1_5);
                }

                next = (TextView)findViewById(R.id.next);
                next.setOnClickListener(this);

                TextView rate1 = (TextView)findViewById(R.id.rate_1);
                rateMap.put(rate1.getId(), rate1);
                rate1.setOnClickListener(this);
                TextView rate2 = (TextView)findViewById(R.id.rate_2);
                rateMap.put(rate2.getId(), rate2);
                rate2.setOnClickListener(this);
                TextView rate3 = (TextView)findViewById(R.id.rate_3);
                rateMap.put(rate3.getId(), rate3);
                rate3.setOnClickListener(this);
                TextView rate4 = (TextView)findViewById(R.id.rate_4);
                rateMap.put(rate4.getId(), rate4);
                rate4.setOnClickListener(this);
                TextView rate5 = (TextView)findViewById(R.id.rate_5);
                rateMap.put(rate5.getId(), rate5);
                rate5.setOnClickListener(this);

                if(poll.getItemPolls().get(currentQuestion).getAnswersCount() > 5) {
                    TextView rate6 = (TextView) findViewById(R.id.rate_6);
                    rateMap.put(rate6.getId(), rate6);
                    rate6.setOnClickListener(this);
                    TextView rate7 = (TextView) findViewById(R.id.rate_7);
                    rateMap.put(rate7.getId(), rate7);
                    rate7.setOnClickListener(this);
                    TextView rate8 = (TextView) findViewById(R.id.rate_8);
                    rateMap.put(rate8.getId(), rate8);
                    rate8.setOnClickListener(this);
                    TextView rate9 = (TextView) findViewById(R.id.rate_9);
                    rateMap.put(rate9.getId(), rate9);
                    rate9.setOnClickListener(this);
                    TextView rate10 = (TextView) findViewById(R.id.rate_10);
                    rateMap.put(rate10.getId(), rate10);
                    rate10.setOnClickListener(this);
                }

                break;
            case "matrix":
                setContentView(R.layout.item_list_next);

                next = (TextView)findViewById(R.id.next);
                next.setOnClickListener(this);

                /*mAnswersList = (RecyclerView) findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerListAdapter(mContext, poll.getItemPolls().get(currentQuestion).getQuestion(), this));*/

                break;
            case "multiplescale":
                setContentView(R.layout.item_poll_list_type);

                mAnswersList = (RecyclerView) findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerRate1_5Adapter(mContext, poll.getItemPolls().get(currentQuestion).getQuestion(), this));

                break;

            case "multiplelist":
                setContentView(R.layout.item_poll_list_type);

                mAnswersList = (RecyclerView) findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerDropDownAdapter(mContext, poll.getItemPolls().get(currentQuestion).getQuestion(), poll.getItemPolls().get(currentQuestion).getAnswers(), this));

                break;

            case "choice":
                setContentView(R.layout.item_poll_list_type);

                mAnswersList = (RecyclerView) findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerCheckAdapter(mContext, poll.getItemPolls().get(currentQuestion).getQuestion(), this, poll.getItemPolls().get(currentQuestion).getOthers()));

                break;

            case "handwritten":
                setContentView(R.layout.item_answer_comment);

                next = (TextView)findViewById(R.id.next);
                next.setOnClickListener(this);

                break;

            case "list":
                setContentView(R.layout.item_answer_big_drop_down);

                spinner = (Spinner)findViewById(R.id.spinner);

                next = (TextView)findViewById(R.id.next);
                next.setOnClickListener(this);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.spinner_textview, R.id.spinnerView, poll.getItemPolls().get(currentQuestion).getAnswers()){
                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent){
                        if (convertView == null){
                            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            convertView = vi.inflate(R.layout.spinner_dropdown, null);
                        }

                        TextView textView = (TextView) convertView.findViewById(R.id.spinnerTxt);
                        textView.setText(poll.getItemPolls().get(currentQuestion).getAnswers().get(position));

                        return convertView;
                    }
                };

                spinner.setAdapter(new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.spinner_row_nothing_selected,
                        mContext));

                break;

            case "PicHor":
                setContentView(R.layout.item_poll_list_type);

                mAnswersList = (RecyclerView) findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerImageHorizontalAdapter(mContext, poll.getItemPolls().get(currentQuestion).getAnswers(), this));

                break;
            case "multiplehandwritten":
                setContentView(R.layout.item_poll_list_type);

                mAnswersList = (RecyclerView) findViewById(R.id.items_list);
                mAnswersList.setHasFixedSize(true);
                mAnswersList.setItemAnimator(new DefaultItemAnimator());
                mAnswersList.setLayoutManager(new LinearLayoutManager(mContext));
                mAnswersList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                mAnswersList.setOnClickListener(this);

                mAnswersList.setAdapter(new ItemAnswerMultipleHandWrittenAdapter(mContext, poll.getItemPolls().get(currentQuestion).getQuestion(), this));

                break;
        }

        if(!poll.getItemPolls().get(currentQuestion).getType().equals("matrix")) {
            mToolBar = (Toolbar) findViewById(R.id.toolbar);
            mToolBar.setBackgroundColor(getResources().getColor(R.color.dark_green));
            mToolBar.setTitle(getResources().getString(R.string.Polls));

            mQuestion = (TextView) findViewById(R.id.question_poll);
            mPageNumber = (TextView) findViewById(R.id.page_number);

            mQuestion.setText(poll.getItemPolls().get(currentQuestion).getTitle());
            mPageNumber.setText(String.valueOf(currentQuestion + 1) + "/" + poll.getItemPolls().size());
        }
    }

    @Override
    public void onClick(View view){
        TextView image;
        ImageView imageView;
        LinearLayout parent;
        int position;
        if(view.getId() != R.id.next) {
            switch (poll.getItemPolls().get(currentQuestion).getType()) {
                case "matrix":
                    /*switch (view.getId()) {
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
                    }*/
                    break;
                case "scale":
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
                    break;
                case "multiplescale":
                    image = (TextView) view;
                    LinearLayout linearLayout = (LinearLayout) view.getParent().getParent();
                    parent = (LinearLayout)linearLayout.getParent();
                    position = mAnswersList.getChildLayoutPosition(parent);
                    ArrayList<TextView> arrayImages = new ArrayList<>();
                    boolean allImagesUnpressed = true;
                    TextView rate1 = (TextView) linearLayout.findViewById(R.id.ll1).findViewById(R.id.rate_1);
                    TextView rate2 = (TextView) linearLayout.findViewById(R.id.ll2).findViewById(R.id.rate_2);
                    TextView rate3 = (TextView) linearLayout.findViewById(R.id.ll3).findViewById(R.id.rate_3);
                    TextView rate4 = (TextView) linearLayout.findViewById(R.id.ll4).findViewById(R.id.rate_4);
                    TextView rate5 = (TextView) linearLayout.findViewById(R.id.ll5).findViewById(R.id.rate_5);
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
                            multipleScaleItemsPressCount--;
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
                        multipleScaleItemsPressCount++;
                    }

                    for(int i = 0; i < arrayImages.size(); i++){
                        if(arrayImages.get(i).getTag().equals("pressed")){
                            answersIds[position] = i + 1;
                            break;
                        }
                    }
                    break;
                case "choice":
                    imageView = (ImageView) view;
                    parent = (LinearLayout)imageView.getParent();
                    position = mAnswersList.getChildLayoutPosition(parent);
                    if (view.getId() == R.id.image_check) {
                        if (view.getTag().equals("unpressed")) {
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_blue));
                            view.setTag("pressed");
                            answersIds[position] = 1;
                        } else {
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.check));
                            view.setTag("unpressed");
                            answersIds[position] = 0;
                        }
                    }
                    break;
                case "PicHor":
                    imageView = (ImageView) view;
                    if (imageView.getTag().equals("unpressed")) {
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_blue));
                        imageView.setTag("pressed");
                    } else {
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_white));
                        imageView.setTag("unpressed");
                    }
                    break;
            }
        }
        else{
             checkValidation();
        }
    }

    @Override
    protected String getTitleToolBar() {
        return null;
    }

    @Override
    protected boolean getDisplayHomeAsUp() {
        return false;
    }

    @Override
    protected boolean getHomeButtonEnabled() {
        return false;
    }

    public void checkValidation(){
        switch(poll.getItemPolls().get(currentQuestion).getType()){
            case "scale":
                Iterator it = rateMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    TextView textView = (TextView)pair.getValue();
                    if(textView.getTag().equals("pressed")){
                        int pressedId = (int)pair.getKey();
                        switch(pressedId){
                            case R.id.rate_1:
                                postAnswers(true, 1);
                                break;
                            case R.id.rate_2:
                                postAnswers(true, 2);
                                break;
                            case R.id.rate_3:
                                postAnswers(true, 3);
                                break;
                            case R.id.rate_4:
                                postAnswers(true, 4);
                                break;
                            case R.id.rate_5:
                                postAnswers(true, 5);
                                break;
                        }

                        return;
                    }
                }
                Toast.makeText(mContext, "Не выбран ответ", Toast.LENGTH_SHORT).show();
                break;
            case "list":
                if(spinner.getSelectedItem() != null){
                    postAnswers(true, spinner.getSelectedItemPosition());
                }else{
                    Toast.makeText(mContext, "Не выбран ответ", Toast.LENGTH_SHORT).show();
                }
                break;
            case "multiplescale":
                if(multipleScaleItemsPressCount == poll.getItemPolls().get(currentQuestion).getQuestionsCount()){
                    postAnswers(false, 0);
                }else{
                    Toast.makeText(mContext, "Не все ответы выбраны", Toast.LENGTH_SHORT).show();
                }
                break;
            case "multiplelist":
                for(int i = 0; i < poll.getItemPolls().get(currentQuestion).getQuestionsCount(); i++){
                    View view = mAnswersList.getChildAt(i);
                    spinner = (Spinner)view.findViewById(R.id.spinner);
                    if(spinner.getSelectedItem() == null){
                        Toast.makeText(mContext, "Не все ответы выбраны", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                postAnswers(false, 0);
                break;
            case "choice":
                for(int i = 0; i < poll.getItemPolls().get(currentQuestion).getQuestionsCount(); i++){
                    View view = mAnswersList.getChildAt(i);
                    ImageView imageView = (ImageView)view.findViewById(R.id.image_check);
                    if(imageView.getTag().equals("pressed")){
                        postAnswers(false, 0);
                        return;
                    }
                }
                Toast.makeText(mContext, "Не выбран ответ", Toast.LENGTH_SHORT).show();
                break;
            case "handwritten":
                EditText editText = (EditText)findViewById(R.id.other_edit_text);
                postAnswers(true, editText.getText().toString());
            default:
                postAnswers(false, 0);
                break;
        }
    }

    public void postAnswers(boolean isSimpleQuestion, int answerId){

        jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        TimeZone tz = TimeZone.getDefault();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(tz);
        String dateISO = df.format(new Date());
        dateISO = dateISO.substring(0, dateISO.length() - 2) + ":" + dateISO.substring(dateISO.length() - 2, dateISO.length());

        if(isSimpleQuestion) {
            try{
                jsonObject.put("votingId", poll.getId());
                jsonObject.put("votingOptionId", poll.getItemPolls().get(currentQuestion).getId());
                jsonObject.put("answer", answerId);
                jsonObject.put("created", dateISO);
                jsonArray.put(jsonObject);
            }catch (JSONException e){

            }
        }
        else {
            for (int i = 0; i < answersIds.length; i++) {
                try{
                    jsonObject = new JSONObject();
                    jsonObject.put("votingId", poll.getId());
                    jsonObject.put("questionId", i + 1);
                    jsonObject.put("votingOptionId", poll.getItemPolls().get(currentQuestion).getId());
                    jsonObject.put("answer", answersIds[i]);
                    jsonObject.put("created", dateISO);
                    jsonArray.put(jsonObject);
                }catch (JSONException e){

                }
            }
        }

        Log.d("postAnswers", jsonArray.toString());
        PostTask task = new PostTask();
        task.execute();
    }

    public void postAnswers(boolean isSimpleQuestion, String answer){
        jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        TimeZone tz = TimeZone.getDefault();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setTimeZone(tz);
        String dateISO = df.format(new Date());
        dateISO = dateISO.substring(0, dateISO.length() - 2) + ":" + dateISO.substring(dateISO.length() - 2, dateISO.length());

        if(isSimpleQuestion) {
            try{
                jsonObject.put("votingId", poll.getId());
                jsonObject.put("votingOptionId", poll.getItemPolls().get(currentQuestion).getId());
                jsonObject.put("answer", answer);
                jsonObject.put("created", dateISO);
                jsonArray.put(jsonObject);
            }catch (JSONException e){

            }
        }

        Log.d("postAnswers", jsonArray.toString());
        PostTask task = new PostTask();
        task.execute();
    }

    public void startNewQuestion(){
        if(currentQuestion + 1 != poll.getItemPolls().size()) {
            Intent intent = new Intent(mContext, SinglePollActivity.class);
            intent.putExtra(PollsListActivity.POLL_START_QUESTION_KEY, ++currentQuestion);
            intent.putExtra(PollsListActivity.POLL_KEY, poll);
            startActivity(intent);
        }
        else{
            setContentView(R.layout.item_answer_done);
        }
    }

    class PostTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext httpContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url);

            try {

                StringEntity se = new StringEntity(jsonArray.toString());

                httpPost.setEntity(se);
                httpPost.setHeader("Content-type", "application/json");
                httpPost.setHeader("did", InterYou.DID);

                HttpResponse response = httpClient.execute(httpPost, httpContext);
                HttpEntity entity = response.getEntity();

                String jsonString = EntityUtils.toString(entity);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void response) {
            startNewQuestion();
        }
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(mContext, "Невозможно вернуться к предыдущему вопросу", Toast.LENGTH_SHORT).show();
    }

    public static void setSpinnerListener(final Spinner spinner){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                LinearLayout linearLayout = (LinearLayout)spinner.getParent();
                int QuestionNumber = mAnswersList.getChildLayoutPosition(linearLayout);
                SinglePollActivity.answersIds[QuestionNumber] = position;
                for(int i = 0; i < SinglePollActivity.answersIds.length; i++) {
                    Log.d("ItemAnswerDropDownViewHolder", String.valueOf(SinglePollActivity.answersIds[i]));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
}
