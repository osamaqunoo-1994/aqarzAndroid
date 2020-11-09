package sa.aqarz.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class NewSiginUpActivity extends AppCompatActivity {
    EditText phone_ed;
    TextView dont_have_account;
    TextView sign_in;
    CheckBox chechbox;
    AppCompatButton register;
    ImageView back;


    String type = "user";
    IResult mResultCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sigin_up);
        back = findViewById(R.id.back);
        register = findViewById(R.id.register);
        chechbox = findViewById(R.id.chechbox);
        dont_have_account = findViewById(R.id.dont_have_account);
        sign_in = findViewById(R.id.sign_in);
        phone_ed = findViewById(R.id.phone_ed);

        init_volley();

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(NewSiginUpActivity.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                finish();
            }
        });

        chechbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    type = "provider";

                } else {
                    type = "user";

                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phone_ed.getText().toString().equals("")) {

                } else {


                    WebService.loading(NewSiginUpActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, NewSiginUpActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {


                        sendObj.put("mobile", phone_ed.getText().toString() + "");
                        sendObj.put("device_token", "157");
                        sendObj.put("device_type", "android");

                        sendObj.put("type", type);
                        sendObj.put("country_code", "+966");

                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("Register", WebService.register, sendObj);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(NewSiginUpActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
//                        "data":{"code":"076218"}

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

                        WebService.Make_Toast_color(NewSiginUpActivity.this, message, "success");


                        Intent intent = new Intent(NewSiginUpActivity.this, ConfirmationActivity.class);
                        intent.putExtra("mobile", phone_ed.getText().toString() + "");
                        intent.putExtra("code", code + "");
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                        finish();

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(NewSiginUpActivity.this, message, "error");
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


                    WebService.Make_Toast_color(NewSiginUpActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(NewSiginUpActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}