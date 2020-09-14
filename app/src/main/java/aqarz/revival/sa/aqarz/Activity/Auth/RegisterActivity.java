package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class RegisterActivity extends AppCompatActivity {
    EditText name_ed;
    EditText email_ed;
    EditText phone_ed;
    EditText password;
    AppCompatButton sign_up;


    TextView Provider_type;
    TextView user_type;
    IResult mResultCallback;

    String type = "user";

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
        name_ed = findViewById(R.id.name_ed);
        email_ed = findViewById(R.id.email_ed);
        phone_ed = findViewById(R.id.phone_ed);
        password = findViewById(R.id.password);
        sign_up = findViewById(R.id.sign_up);
        Provider_type = findViewById(R.id.Provider_type);
        user_type = findViewById(R.id.user_type);

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
        name_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    name_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
                    name_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_user_name), null, null, null);

                } else {
                    name_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
                    name_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_user_name_color), null, null, null);

                }
            }
        });
        email_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    email_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
                    email_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_user_name), null, null, null);

                } else {
                    email_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
                    email_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_email_color), null, null, null);

                }
            }
        });


        Provider_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Provider_type.setBackgroundDrawable(getDrawable(R.drawable.button_login));
                Provider_type.setTextColor(getResources().getColor(R.color.white));
                user_type.setBackgroundDrawable(null);
                user_type.setTextColor(getResources().getColor(R.color.colorPrimary));
                type = "Provider";

            }
        });

        user_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                user_type.setBackgroundDrawable(getDrawable(R.drawable.button_login));
                user_type.setTextColor(getResources().getColor(R.color.white));
                Provider_type.setBackgroundDrawable(null);
                Provider_type.setTextColor(getResources().getColor(R.color.colorPrimary));
                type = "user";

            }
        });


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name_ed.getText().toString().equals("") |
                        email_ed.getText().toString().equals("") |
                        phone_ed.getText().toString().equals("") |
                        password.getText().toString().equals("")) {

                } else {
                    WebService.loading(RegisterActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, RegisterActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("name", name_ed.getText().toString());
                        sendObj.put("email", email_ed.getText().toString());
                        sendObj.put("mobile", phone_ed.getText().toString());
                        sendObj.put("password", password.getText().toString());
                        sendObj.put("password_confirmation", password.getText().toString());

                        sendObj.put("device_token", "######");
                        sendObj.put("type", type);
                        sendObj.put("country_code", "+970");
                        sendObj.put("device_type", "android");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mVolleyService.postDataVolley("Register", WebService.register, sendObj);

                }

            }
        });

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(RegisterActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//                        Hawk.put("user", data);
                        String message = response.getString("message");

                        WebService.Make_Toast_color(RegisterActivity.this, message, "success");


                        Intent intent = new Intent(RegisterActivity.this, ConfirmationActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(RegisterActivity.this, message, "success");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(RegisterActivity.this, false);
                WebService.Make_Toast_color(RegisterActivity.this, error.getMessage(), "error");


            }
        };


    }


}