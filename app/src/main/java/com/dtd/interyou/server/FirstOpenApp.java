package com.dtd.interyou.server;

import android.util.Log;

import com.dtd.interyou.InterYou;
import com.dtd.interyou.model.GetItemHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * Created by Egor on 11.07.2015.
 */
public class FirstOpenApp {

    public static void setFirstOpenAppOperation(final GetItemHandler<String> callback){

        Log.d("FirstOpen - ","GET DID");
        RequestParams params = new RequestParams();
         Server.post("device/register?uuid=" + URLEncoder.encode(InterYou.UUID) + "&os=" + URLEncoder.encode(InterYou.OS)+ "&osVersion=" + URLEncoder.encode(InterYou.OSVERSION) + "&model=" + URLEncoder.encode(InterYou.MODEL), params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {

                    callback.done(response.getString("did"));
                    Log.d("DID ===", InterYou.DID);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.error(e.toString());
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray newsJSON) {
                Log.d("statusCode = ", String.valueOf(statusCode));
                Integer i = statusCode;
                //handler.done(news);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("FAIL !!!", String.valueOf(statusCode));
                Integer i = statusCode;
                callback.error(responseString);
                //handler.error(responseString);
            }
        });

    }


}
