package com.dtd.interyou.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.dtd.interyou.server.Server;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Egor on 22.04.2015.
 */
public class Polls implements Parcelable{
    private String Date;
    private String Title;
    private String ImageUri;
    private Category category;
    private ArrayList<ItemPoll> itemPolls;
    private int Id;

    public enum Category{
        SPORT,
        FOOD,
        FASHION,
        POLITICS,
        SCIENCE_AND_TECHNOLOGY,
        PEOPLE_AND_RELATIONSHIPS,
        MEDIA,
        ECONOMY,
        HABITS,
        RELAX_AND_FUN,
        PEOPLE_AND_SOCIETY,
        TRANSPORT,
        WORLD_VIEW,
        WISH
    }

    public Polls() {
    }

    public Polls(String date, String title, String price, String imageUri, Category category) {
        Date = date;
        Title = title;
        ImageUri = imageUri;
        this.category = category;
    }

    public Polls(String date, String title, String price, String imageUri) {
        Date = date;
        Title = title;
        ImageUri = imageUri;
    }

    /*public Polls(String title, String price, String imageUri, String type) {
        Title = title;
        Price = price;
        ImageUri = imageUri;
    }*/

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<ItemPoll> getItemPolls(){
        return itemPolls;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Polls(JSONObject json_categories)
    {
        itemPolls = new ArrayList<>();
        try {
            Title = json_categories.getString("title");
            JSONArray jsonArray = json_categories.getJSONArray("options");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String type = jsonObject.getString("type");
                String title = jsonObject.getString("title");
                Boolean others = jsonObject.getBoolean("others");
                int id = jsonObject.getInt("id");
                ArrayList<String> listQuestions = new ArrayList<>();
                JSONArray questions = jsonObject.getJSONArray("questions");
                for(int j = 0; j < questions.length(); j++){
                    JSONObject question = questions.getJSONObject(j);
                    listQuestions.add(question.getString("title"));
                }
                ArrayList<String> listAnswers = new ArrayList<>();
                JSONArray answers = jsonObject.getJSONArray("answers");
                for(int j = 0; j < answers.length(); j++){
                    JSONObject answer = answers.getJSONObject(j);
                    listAnswers.add(answer.getString("title"));
                }
                itemPolls.add(new ItemPoll(listQuestions, listAnswers, type, title, others, id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void getPoll(final GetListHandler<Polls> handler, int pollId)
    {
        RequestParams params = new RequestParams();
        Server.get("voting/" + pollId, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                ArrayList<Polls> polls = fromJson(response);

                handler.done(polls);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray booksJSON) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                handler.error(responseString);
            }

        });
    }


    public static ArrayList<Polls> fromJson(JSONObject jsonObject) {
        ArrayList<Polls> ArraySeries = new ArrayList<Polls>();
        ArraySeries.add(new Polls(jsonObject));

        return ArraySeries;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeList(itemPolls);
        parcel.writeInt(Id);
    }

    public Polls(Parcel in) {
        itemPolls = in.readArrayList(Polls.class.getClassLoader());
        Id = in.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Polls createFromParcel(Parcel in) {
            return new Polls(in);
        }

        @Override
        public Polls[] newArray(int size) {
            return new Polls[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
