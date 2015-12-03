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
public class PollInfo {
    private int Id;
    private String Title, Price;

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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public PollInfo(JSONObject json_categories)
    {
        try {
            Id = json_categories.getInt("id");
            Title = json_categories.getString("title");
            Price = json_categories.getString("price");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void getAllPollsInfo(final GetListHandler<PollInfo> handler, int categoryNumber)
    {
        RequestParams params = new RequestParams();
        Server.get("voting/by/category/" + categoryNumber, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {
                    onSuccess(statusCode, headers, response.getJSONArray("items"));
                } catch (JSONException e) {
                    onFailure(statusCode, headers, e.getMessage(), e);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray booksJSON) {
                ArrayList<PollInfo> polls = fromJson(booksJSON);

                handler.done(polls);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                handler.error(responseString);
            }

        });
    }


    public static ArrayList<PollInfo> fromJson(JSONArray jsonArray) {
        ArrayList<PollInfo> ArraySeries = new ArrayList<PollInfo>(jsonArray.length());
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
                ArraySeries.add(new PollInfo(newsJson));
            }
        }
        return ArraySeries;
    }
}
