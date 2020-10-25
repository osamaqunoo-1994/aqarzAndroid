package aqarz.revival.sa.aqarz.Activity.Auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

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

public class NewConfirmationActivity extends AppCompatActivity {
    EditText code_ed;
    EditText Cpassword;
    EditText phone_ed;
    EditText password;
    AppCompatButton sign_up;


    boolean is_show = false;
    boolean cis_show = false;


    IResult mResultCallback;

    String type = "user";
    ImageView pass_checkbox;
    ImageView cpass_checkbox;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        code_ed = findViewById(R.id.code_ed);
        Cpassword = findViewById(R.id.Cpassword);
        phone_ed = findViewById(R.id.phone_ed);
        password = findViewById(R.id.password);
        sign_up = findViewById(R.id.sign_up);

        pass_checkbox = findViewById(R.id.pass_checkbox);
        cpass_checkbox = findViewById(R.id.Cpass_checkbox);
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            String mobile = getIntent().getStringExtra("mobile");
            phone_ed.setText(mobile);
        } catch (Exception e) {

        }

        phone_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    phone_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));

//                    Drawable img = getResources().getDrawable(R.drawable.ic_phone_color);
//
//                    phone_ed.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

                } else {
                    phone_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
//                    Drawable img = getResources().getDrawable(R.drawable.ic_phone);
//
//                    phone_ed.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
//                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look), null, null, null);

                } else {
                    password.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
//                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look_color), null, null, null);

                }
            }
        });
        Cpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Cpassword.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
//                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look), null, null, null);

                } else {
                    Cpassword.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
//                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look_color), null, null, null);

                }
            }
        });


        code_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    code_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
//                    email_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_user_name), null, null, null);

                } else {
                    code_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
//                    email_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_email_color), null, null, null);

                }
            }
        });


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phone_ed.getText().toString().equals("") |
                        code_ed.getText().toString().equals("") |
                        phone_ed.getText().toString().equals("") |
                        password.getText().toString().equals("")) {
                    WebService.Make_Toast_color(NewConfirmationActivity.this, getResources().getString(R.string.fillallfileds) + "", "error");
                } else {
                    WebService.loading(NewConfirmationActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, NewConfirmationActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("mobile", phone_ed.getText().toString());
                        sendObj.put("code", code_ed.getText().toString());

                        sendObj.put("password", password.getText().toString());
                        sendObj.put("password_confirmation", Cpassword.getText().toString());
                        sendObj.put("country_code", "+966");

//                        sendObj.put("device_token", "157");
//                        sendObj.put("type", type);
//                        sendObj.put("country_code", "+972");
//                        sendObj.put("device_type", "android");

                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley_without_token("verify", WebService.verify, sendObj);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        pass_checkbox.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (is_show) {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    pass_checkbox.setSelected(false);
//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.show_pass_bg));
                    is_show = false;
                } else {
                    password.setTransformationMethod(null);
                    pass_checkbox.setSelected(true);

//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.ic_private));
                    is_show = true;
                }
            }
        });
        Cpassword.setTransformationMethod(new PasswordTransformationMethod());

        cpass_checkbox.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (cis_show) {
                    Cpassword.setTransformationMethod(new PasswordTransformationMethod());
                    cpass_checkbox.setSelected(false);
//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.show_pass_bg));
                    cis_show = false;
                } else {
                    Cpassword.setTransformationMethod(null);
                    cpass_checkbox.setSelected(true);

//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.ic_private));
                    cis_show = true;
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
                WebService.loading(NewConfirmationActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//
                        Hawk.put("user", data);
                        JsonParser parser = new JsonParser();
                        JsonElement mJson = parser.parse(data);

                        Gson gson = new Gson();
                        User userModules = gson.fromJson(mJson, User.class);

                        Hawk.put("api_token", "token " + userModules.getApi_token() + "");


                        String message = response.getString("message");
                        WebService.Make_Toast_color(NewConfirmationActivity.this, message, "success");
                        finish();

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(NewConfirmationActivity.this, message, "error");
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


                    WebService.Make_Toast_color(NewConfirmationActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(NewConfirmationActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }


}