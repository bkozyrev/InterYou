package com.dtd.interyou.model;

import com.dtd.interyou.server.Server;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 123 on 25.07.2015.
 */
public class Category {
    private int Id;
    private String Title;
    private int PollsCount;
    private ArrayList<Integer> PollsId;
    String Url = "http://interyou.tk:8888/api/mobile/config/";

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPollsCount(){
        return PollsCount;
    }

    public ArrayList<Integer> getPollsId(){
        return PollsId;
    }

    public String getUrl(){
        return Url;
    }

    public Category(JSONObject json_categories)
    {
        try {
            Id = json_categories.getInt("id");
            Title = json_categories.getString("title");
            Url += json_categories.getString("image");
            PollInfo.getAllPollsInfo(new GetListHandler<PollInfo>() {
                @Override
                public void done(ArrayList<PollInfo> data) {
                    PollsCount = data.size();
                }

                @Override
                public void error(String responseError) {

                }
            }, Id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void getAllCategories(final GetListHandler<Category> handler)
    {
        RequestParams params = new RequestParams();
        Server.get("config", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {
                    onSuccess(statusCode, headers, response.getJSONArray("category"));
                } catch (JSONException e) {
                    onFailure(statusCode, headers, e.getMessage(), e);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray booksJSON) {
                ArrayList<Category> categories = fromJson(booksJSON);

                handler.done(categories);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                handler.error(responseString);
            }

        });
    }


    public static ArrayList<Category> fromJson(JSONArray jsonArray) {
        ArrayList<Category> ArraySeries = new ArrayList<Category>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject newsJson = null;
            try {
                newsJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            if (newsJson != null) {
                ArraySeries.add(new Category(newsJson));
            }
        }
        return ArraySeries;
    }
}
