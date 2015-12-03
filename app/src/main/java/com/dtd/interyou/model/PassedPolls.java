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
 * Created by 123 on 23.08.2015.
 */
public class PassedPolls {
    ArrayList<Integer> passedIds;

    public ArrayList<Integer> getPassedIds(){
        return passedIds;
    }

    public PassedPolls(JSONObject json_categories)
    {

    }

    public static void getAllPassedIds(final GetListHandler<PassedPolls> handler)
    {
        RequestParams params = new RequestParams();
        Server.get("voting/passed/by/user", params, new JsonHttpResponseHandler() {
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
                ArrayList<PassedPolls> polls = fromJson(booksJSON);

                handler.done(polls);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                handler.error(responseString);
            }
        });
    }


    public static ArrayList<PassedPolls> fromJson(JSONArray jsonArray) {
        ArrayList<PassedPolls> ArraySeries = new ArrayList<>(jsonArray.length());
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
                ArraySeries.add(new PassedPolls(newsJson));
            }
        }
        return ArraySeries;
    }
}
