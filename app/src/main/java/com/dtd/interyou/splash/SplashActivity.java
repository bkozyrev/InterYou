package com.dtd.interyou.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dtd.interyou.R;
import com.dtd.interyou.activities.BaseActivity;
import com.dtd.interyou.activities.MainActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

import static com.dtd.interyou.server.Registration.check;
import static com.dtd.interyou.server.Registration.registrate;

/**
 * Created by Egor on 21.05.2015.
 */
public class SplashActivity extends BaseActivity {

    private View auth1, auth2;
    Button btnNext, btnDone;
    private EditText phoneNumber, code;
    CircularProgressBar mBar;

    @Override
    protected int getLayoutResourceIdentifier() {
        return R.layout.activity_auth;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView img_bck = (ImageView) findViewById(R.id.img_bck);

        auth1 = (View) findViewById(R.id.auth_first_step);
        auth2 = (View) findViewById(R.id.auth_second_step);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnDone = (Button) findViewById(R.id.btn_done);

        phoneNumber = (EditText) findViewById(R.id.edit_phone);
        code = (EditText) findViewById(R.id.edit_code);
        mBar = (CircularProgressBar) findViewById(R.id.bar);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mBar.setVisibility(View.VISIBLE);
                registrate(phoneNumber.getText().toString(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // If the response is JSONObject instead of expected JSONArray
                        mBar.setVisibility(View.GONE);
                        Log.d("statusCode = ", String.valueOf(statusCode));
                        String str = null;
                        try {
                            str = response.getString("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (str != null) {
                            switch (str) {
                                case "ok":
                                    final Animation animationAlpha = AnimationUtils.loadAnimation(mContext, R.anim.alpha);
                                    Animation animationSlide = AnimationUtils.loadAnimation(mContext, R.anim.slide_down);
                                    animationSlide.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            auth2.startAnimation(animationAlpha);
                                            auth2.setVisibility(View.VISIBLE);

                                            btnDone.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {


                                                    mBar.setVisibility(View.VISIBLE);
                                                    check(phoneNumber.getText().toString(), code.getText().toString(), new JsonHttpResponseHandler() {//TODO проверка на пустоту поля "code"
                                                        @Override
                                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                                            super.onSuccess(statusCode, headers, response);
                                                            Log.d("Success statusCode = ", String.valueOf(statusCode));
                                                            String str = null;
                                                            try {
                                                                str = response.getString("status");
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                            if (str != null) {
                                                                switch (str) {
                                                                    case "ok":
                                                                        mBar.setVisibility(View.GONE);
                                                                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                                                        finish();
                                                                        break;
                                                                    case "error":
                                                                        mBar.setVisibility(View.GONE);
                                                                        try {
                                                                            Toast.makeText(mContext, response.getString("message"), Toast.LENGTH_SHORT)
                                                                                    .show();
                                                                        } catch (JSONException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        break;
                                                                    default:
                                                                        mBar.setVisibility(View.GONE);
                                                                        break;
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                                            super.onSuccess(statusCode, headers, response);
                                                            mBar.setVisibility(View.GONE);
                                                            Log.d("Success statusCode = ", String.valueOf(statusCode));
                                                        }

                                                        @Override
                                                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                                            super.onFailure(statusCode, headers, responseString, throwable);
                                                            mBar.setVisibility(View.GONE);
                                                            Log.d("Fail statusCode = ", String.valueOf(statusCode));
                                                            Toast.makeText(mContext, responseString, Toast.LENGTH_SHORT)//TODO delete this string!!!
                                                                    .show();
                                                            Log.d("Fail check response", responseString);

                                                        }
                                                    });


                                                }
                                            });
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }

                                    });
                                    auth1.startAnimation(animationSlide);
                                    auth1.setVisibility(View.GONE);
                                    break;

                                case "error":
                                    mBar.setVisibility(View.GONE);
                                    try {
                                        Toast.makeText(mContext, response.getString("message"), Toast.LENGTH_SHORT)
                                                .show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                default:
                                    mBar.setVisibility(View.GONE);
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray newsJSON) {
                        mBar.setVisibility(View.GONE);
                        Log.d("statusCode = ", String.valueOf(statusCode));
                        Integer i = statusCode;

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        mBar.setVisibility(View.GONE);
                        Log.d("FAIL !!!", String.valueOf(statusCode));
                    }

                });
            }
        });




        /*Thread logoTimer = new Thread()
        {
            public void run()
            {
                try {
                    sleep(2500);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        logoTimer.start();*/

    }

}
