package com.dtd.interyou.server;

import com.dtd.interyou.InterYou;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.net.URLEncoder;

/**
 * Created by Egor on 22.07.2015.
 */
public class Registration {

    public static void registrate(String phoneNumber,JsonHttpResponseHandler handler ){

        RequestParams params = new RequestParams();

        Server.post("phone/validate?number=" + URLEncoder.encode(phoneNumber), params,handler);
    }

    public static void check(String phoneNumber, String code, JsonHttpResponseHandler handler ){

        RequestParams params = new RequestParams();

        Server.post("phone/validation/check?number=" + URLEncoder.encode(phoneNumber) + "&code=" + URLEncoder.encode(code), params,handler);
    }

}
