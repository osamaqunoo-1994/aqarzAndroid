package sa.aqarz.Activity.Auth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import sa.aqarz.Activity.TermsActivity;
import sa.aqarz.Modules.User;
import sa.aqarz.NewAqarz.WebViewActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class ChoseTypeActivity extends AppCompatActivity {
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


    String code = "";
    String mobile = "";


    TextView acept_terms;


    LinearLayout user_lay;
    TextView user_txt;
    ImageView user_img;

    LinearLayout aqarz_lay;
    TextView aqarz_txt;
    ImageView aqarz_img;


    String type_man = "";
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_type);

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
        user_lay = findViewById(R.id.user_lay);
        user_txt = findViewById(R.id.user_txt);
        user_img = findViewById(R.id.user_img);

        aqarz_lay = findViewById(R.id.aqarz_lay);
        aqarz_txt = findViewById(R.id.aqarz_txt);
        aqarz_img = findViewById(R.id.aqarz_img);

        code_ed = findViewById(R.id.code_ed);
        Cpassword = findViewById(R.id.Cpassword);
        phone_ed = findViewById(R.id.phone_ed);
        password = findViewById(R.id.password);
        sign_up = findViewById(R.id.sign_up);
        acept_terms = findViewById(R.id.acept_terms);

        pass_checkbox = findViewById(R.id.pass_checkbox);
        cpass_checkbox = findViewById(R.id.Cpass_checkbox);
        confirm = findViewById(R.id.confirm);
        back = findViewById(R.id.back);


        if (Hawk.get("lang").toString().equals("ar")) {


            password.setGravity(Gravity.RIGHT);
            Cpassword.setGravity(Gravity.RIGHT);

        } else {
            password.setGravity(Gravity.LEFT);
            Cpassword.setGravity(Gravity.LEFT);


        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            mobile = getIntent().getStringExtra("mobile");
            code = getIntent().getStringExtra("code");
//            phone_ed.setText(mobile);
        } catch (Exception e) {

        }


        user_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_man = "user";
                confirm.setBackground(getResources().getDrawable(R.drawable.button_login1));
                user_lay.setBackground(getResources().getDrawable(R.drawable.button_login1));
                aqarz_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_edit));

                user_img.setColorFilter(ContextCompat.getColor(ChoseTypeActivity.this, R.color.white), android.graphics.PorterDuff.Mode.SRC_ATOP);
                aqarz_img.setColorFilter(ContextCompat.getColor(ChoseTypeActivity.this, R.color.textColor1), android.graphics.PorterDuff.Mode.SRC_ATOP);


                confirm.setTextColor(getResources().getColor(R.color.white));
                user_txt.setTextColor(getResources().getColor(R.color.white));
                aqarz_txt.setTextColor(getResources().getColor(R.color.textColor));


            }
        });

        aqarz_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_man = "provider";

                user_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_edit));
                aqarz_lay.setBackground(getResources().getDrawable(R.drawable.button_login1));
                confirm.setBackground(getResources().getDrawable(R.drawable.button_login1));

                user_img.setColorFilter(ContextCompat.getColor(ChoseTypeActivity.this, R.color.textColor1), android.graphics.PorterDuff.Mode.SRC_ATOP);
                aqarz_img.setColorFilter(ContextCompat.getColor(ChoseTypeActivity.this, R.color.white), android.graphics.PorterDuff.Mode.SRC_ATOP);


                user_txt.setTextColor(getResources().getColor(R.color.textColor));
                aqarz_txt.setTextColor(getResources().getColor(R.color.white));
                confirm.setTextColor(getResources().getColor(R.color.white));
            }
        });


//
//        phone_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    phone_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
//
////                    Drawable img = getResources().getDrawable(R.drawable.ic_phone_color);
////
////                    phone_ed.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
//
//                } else {
//                    phone_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
////                    Drawable img = getResources().getDrawable(R.drawable.ic_phone);
////
////                    phone_ed.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
//
//                }
//            }
//        });

//        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    password.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
////                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look), null, null, null);
//
//                } else {
//                    password.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
////                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look_color), null, null, null);
//
//                }
//            }
//        });
//        Cpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    Cpassword.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
////                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look), null, null, null);
//
//                } else {
//                    Cpassword.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
////                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look_color), null, null, null);
//
//                }
//            }
//        });
//

//        code_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    code_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
////                    email_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_user_name), null, null, null);
//
//                } else {
//                    code_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
////                    email_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_email_color), null, null, null);
//
//                }
//            }
//        });

        acept_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ChoseTypeActivity.this, TermsActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (
                        type.equals("")) {
                    WebService.Make_Toast_color(ChoseTypeActivity.this, getResources().getString(R.string.select_type) + "", "error");
                } else {
                    WebService.loading(ChoseTypeActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, ChoseTypeActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("mobile", mobile);
                        sendObj.put("code", code);

                        sendObj.put("type", type_man + "");
//                        sendObj.put("password_confirmation", Cpassword.getText().toString());
                        sendObj.put("country_code", "+966");

//                        sendObj.put("device_token", "157");
//                        sendObj.put("type", type);
//                        sendObj.put("country_code", "+972");
//                        sendObj.put("device_type", "android");

                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley_without_token("verifyNew", WebService.verifyNew, sendObj);

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
                WebService.loading(ChoseTypeActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//

                        Intent intent=new Intent(ChoseTypeActivity.this, WebViewActivity.class);
                        intent.putExtra("data",data);
                        startActivity(intent);


//                        Hawk.put("user", data);
//                        JsonParser parser = new JsonParser();
//                        JsonElement mJson = parser.parse(data);
//
//                        Gson gson = new Gson();
//                        User userModules = gson.fromJson(mJson, User.class);
//
//                        Hawk.put("api_token", "token " + userModules.getApi_token() + "");
//
//
//                        String message = response.getString("message");
//                        WebService.Make_Toast_color(ChoseTypeActivity.this, message, "success");
//                        finish();

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(ChoseTypeActivity.this, message, "error");
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


                    WebService.Make_Toast_color(ChoseTypeActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(ChoseTypeActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }


}