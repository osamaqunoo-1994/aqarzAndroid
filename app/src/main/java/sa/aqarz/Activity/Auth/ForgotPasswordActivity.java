package sa.aqarz.Activity.Auth;

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

import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

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

                        sendObj.put("mobile", email_ed.getText().toString());
                        sendObj.put("country_code", "+966");


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley_without_token("forget*********password", WebService.forget_password1,sendObj);

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

                        String data = response.getString("data");

                        JSONObject jsonObject=new JSONObject(data);
                        String code = jsonObject.getString("code");

//                        Hawk.put("user", data);
//                        JsonParser parser = new JsonParser();
//                        JsonElement mJson = parser.parse(data);
//
//                        Gson gson = new Gson();
//                        User userModules = gson.fromJson(mJson, User.class);
//
//                        Hawk.put("api_token", "token " + userModules.getApi_token() + "");

                        String message = response.getString("message");

                        WebService.Make_Toast_color(ForgotPasswordActivity.this, message, "success");


                        Intent intent = new Intent(ForgotPasswordActivity.this, ConfirmationForgotActivity.class);
                        intent.putExtra("mobile", email_ed.getText().toString() + "");
                        intent.putExtra("code", code + "");
                        startActivity(intent);
//                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                        finish();

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

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}