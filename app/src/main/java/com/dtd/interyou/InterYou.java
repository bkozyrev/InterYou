package com.dtd.interyou;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.dtd.interyou.Utils.Devices;
import com.dtd.interyou.model.GetItemHandler;
import com.dtd.interyou.server.FirstOpenApp;

/**
 * Created by Egor on 11.07.2015.
 */
public class InterYou extends Application {

    private static final String APP_PREFERENCES = "InterYouPreferences";
    private static final String APP_DID = "did";

    public static String DID;

    public static String UUID;
    public static String OS;
    public static String OSVERSION;
    public static String MODEL;
    public String PUSH_TOKEN;

    SharedPreferences sPref;


    public static boolean profileFilled;

    @Override
    public void onCreate()
    {
        super.onCreate();
        TelephonyManager tManager = (TelephonyManager)getSystemService(getBaseContext().TELEPHONY_SERVICE);
        UUID = tManager.getDeviceId();
        MODEL = Devices.getDeviceName();
        OS = "android";
        OSVERSION = String.valueOf(Build.VERSION.RELEASE);

        sPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(!sPref.contains(APP_DID)){
            SharedPreferences.Editor editor =sPref.edit();
            FirstOpenApp.setFirstOpenAppOperation(new GetItemHandler<String>() {
                @Override
                public void done(String data) {
                    InterYou.DID = data;
                    SharedPreferences.Editor editor =sPref.edit();
                    editor.putString(APP_DID, InterYou.DID);
                    editor.apply();
                }

                @Override
                public void error(String responseError) {
                }
            });
        }else{
            InterYou.DID  = sPref.getString(APP_DID, "");
        }
    }
}
