package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Modules.User;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;


public class LoginActivity extends AppCompatActivity {
    EditText phone_ed;
    EditText password;
    TextView forget_pass;
    AppCompatButton new_account;
    AppCompatButton Login;

    IResult mResultCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init_volley();
        init();


        AppCompatImageView yourView = findViewById(R.id.immage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }


    }

    public void init() {
        phone_ed = findViewById(R.id.phone_ed);
        password = findViewById(R.id.password);
        new_account = findViewById(R.id.new_account);
        Login = findViewById(R.id.Login);
        forget_pass = findViewById(R.id.forget_pass);

        phone_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    phone_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));

                    Drawable img = getResources().getDrawable(R.drawable.ic_phone_color);

                    phone_ed.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

                } else {
                    phone_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
                    Drawable img = getResources().getDrawable(R.drawable.ic_phone);

                    phone_ed.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look), null, null, null);

                } else {
                    password.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look_color), null, null, null);

                }
            }
        });

        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                finish();

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone_ed.getText().toString().equals("") |
                        password.getText().toString().equals("")) {
                    WebService.Make_Toast_color(LoginActivity.this, getResources().getString(R.string.fillallfileds) + "", "error");

                } else {
                    WebService.loading(LoginActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, LoginActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("username", phone_ed.getText().toString());
                        sendObj.put("password", password.getText().toString());
                        sendObj.put("device_token", "154");
                        sendObj.put("device_type", "android");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mVolleyService.postDataVolley_without_token("Login", WebService.login, sendObj);
                }

            }
        });
        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }
        });
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(LoginActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                        Hawk.put("user", data);
                        JsonParser parser = new JsonParser();
                        JsonElement mJson = parser.parse(data);

                        Gson gson = new Gson();
                        User userModules = gson.fromJson(mJson, User.class);

                        Hawk.put("api_token", "token " + userModules.getApi_token() + "");


                        String message = response.getString("message");
                        WebService.Make_Toast_color(LoginActivity.this, message, "success");
                        finish();

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(LoginActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(LoginActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading(LoginActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}