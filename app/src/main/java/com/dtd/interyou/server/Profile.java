package com.dtd.interyou.server;

import com.dtd.interyou.model.GetListHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Egor on 14/08/15.
 */
public class Profile{

        public static void getConfig(final GetListHandler<ArrayList<String>> handler){
            RequestParams params = new RequestParams();

            Server.get("config", params, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray categoryJSON) {
                    //ArrayList<ConfigItem> categories = fromJson(categoryJSON);


                    handler.done(null);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    ArrayList<ArrayList<String>> array = new ArrayList<>();

                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("country"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("education"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("employment"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("income"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("financialStatus"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("children"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("familyStatus"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("car"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        ArrayList<String> item = createAndSortArray(response.getJSONArray("mobileOperator"));
                        array.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                    handler.done(array);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    handler.error(responseString);
                }
            });
        }




    public static class ConfigOption {
            private int id;
            private String title;

            public ConfigOption(int id, String title) {
                this.id = id;
                this.title = title;
            }

            public ConfigOption(JSONObject object){
                try{
                    id = Integer.parseInt(object.getString("id"));
                }catch (Exception e){
                    id = -1;
                }
                try{
                    title = object.getString("title");
                }catch (Exception e){
                    title = null;
                }
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

    static class SortedById implements Comparator<ConfigOption>{

        @Override
        public int compare(ConfigOption lhs, ConfigOption rhs) {
            if(lhs.getId()>rhs.getId()) return 1;
            else if(lhs.getId()<rhs.getId()) return -1;
            else return 0;
        }
    }

    private static ArrayList<String> createAndSortArray(JSONArray array){

        ArrayList<String> result;

        ArrayList<ConfigOption> arrayConfigs = new ArrayList<>();
        for(int i=0;i<array.length();i++){
            try {
                ConfigOption option = new ConfigOption(array.getJSONObject(i));
                arrayConfigs.add(option);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(arrayConfigs, new SortedById());

        result = new ArrayList<>();

        for(int i=0;i<arrayConfigs.size()-1;i++){
            result.add(arrayConfigs.get(i).getTitle());
        }

        return result;
    }


}
