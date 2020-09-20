package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import aqarz.revival.sa.aqarz.Modules.User;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class ForgotPasswordActivity extends AppCompatActivity {


    EditText email_ed;
    AppCompatButton send;
    IResult mResultCallback;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        init_volley();
        init();

        LinearLayout yourView = findViewById(R.id.lay);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }


    public void init() {

        email_ed = findViewById(R.id.email_ed);
        send = findViewById(R.id.send);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email_ed.getText().toString().equals("")) {


                } else {

                    WebService.loading(ForgotPasswordActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, ForgotPasswordActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("email", email_ed.getText().toString());


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("forget password", WebService.forget_password, sendObj);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }


        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(ForgotPasswordActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {


                        String message = response.getString("message");

                        WebService.Make_Toast_color(ForgotPasswordActivity.this, message, "success");


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(ForgotPasswordActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(ForgotPasswordActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }
                WebService.loading(ForgotPasswordActivity.this, false);


            }
        };


    }

}