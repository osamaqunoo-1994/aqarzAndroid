package sa.aqarz.Activity.Auth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class NewPasswordActivity extends AppCompatActivity {
    EditText Oldpassword;
    EditText password;
    EditText Cpassword;
    ImageView back;
    AppCompatButton change;
    IResult mResultCallback;

    boolean is_show = false;
    boolean cis_show = false;
    boolean cis_show1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

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

        Oldpassword = findViewById(R.id.Oldpassword);
        password = findViewById(R.id.password);
        Cpassword = findViewById(R.id.Cpassword);
        change = findViewById(R.id.change);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Hawk.get("lang").toString().equals("ar")) {


            Oldpassword.setGravity(Gravity.RIGHT);
            password.setGravity(Gravity.RIGHT);
            Cpassword.setGravity(Gravity.RIGHT);

        } else {
            Oldpassword.setGravity(Gravity.LEFT);
            password.setGravity(Gravity.LEFT);
            Cpassword.setGravity(Gravity.LEFT);


        }

        Oldpassword.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (is_show) {
                    Oldpassword.setTransformationMethod(new PasswordTransformationMethod());
                    Oldpassword.setSelected(false);
//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.show_pass_bg));
                    is_show = false;
                } else {
                    Oldpassword.setTransformationMethod(null);
                    Oldpassword.setSelected(true);

//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.ic_private));
                    is_show = true;
                }
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (cis_show) {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    password.setSelected(false);
//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.show_pass_bg));
                    cis_show = false;
                } else {
                    password.setTransformationMethod(null);
                    password.setSelected(true);

//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.ic_private));
                    cis_show = true;
                }
            }
        });
        Cpassword.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (cis_show1) {
                    Cpassword.setTransformationMethod(new PasswordTransformationMethod());
                    Cpassword.setSelected(false);
//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.show_pass_bg));
                    cis_show1 = false;
                } else {
                    Cpassword.setTransformationMethod(null);
                    Cpassword.setSelected(true);

//                    pass_checkbox.setImageDrawable(getDrawable(R.drawable.ic_private));
                    cis_show1 = true;
                }
            }
        });


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Oldpassword.getText().toString().equals("") |
                        password.getText().toString().equals("") | Cpassword.getText().toString().equals("")) {

                    WebService.Make_Toast_color(NewPasswordActivity.this, getResources().getString(R.string.AllFiledsREquered), "error");

                } else if (password.getText().toString().equals(Cpassword.getText().toString().equals(""))) {

                    WebService.Make_Toast_color(NewPasswordActivity.this, getResources().getString(R.string.PasswordnadConfirmnotmatch), "error");

                } else {

                    WebService.loading(NewPasswordActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, NewPasswordActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {
                        sendObj.put("old_password", Oldpassword.getText().toString());

                        sendObj.put("password", password.getText().toString());
                        sendObj.put("password_confirmation", Cpassword.getText().toString());


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("update_password", WebService.update_password, sendObj);

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
                WebService.loading(NewPasswordActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {


                        String message = response.getString("message");

                        WebService.Make_Toast_color(NewPasswordActivity.this, message, "success");


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(NewPasswordActivity.this, message, "error");
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


                    WebService.Make_Toast_color(NewPasswordActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }
                WebService.loading(NewPasswordActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }


}