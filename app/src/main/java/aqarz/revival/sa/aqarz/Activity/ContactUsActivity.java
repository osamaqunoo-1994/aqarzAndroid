package aqarz.revival.sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Modules.User;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class ContactUsActivity extends AppCompatActivity {
    TextView email_info;
    TextView phone;
    ImageView back;
    IResult mResultCallback;


    Button send;
    EditText name_ed, email_ed, phone_ed, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        email_info = findViewById(R.id.email_info);
        phone = findViewById(R.id.phone);
        back = findViewById(R.id.back);
        name_ed = findViewById(R.id.name_ed);
        email_ed = findViewById(R.id.email_ed);
        phone_ed = findViewById(R.id.phone_ed);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);


        email_info.setText(Settings.getSettings().getEmail());
        phone.setText(Settings.getSettings().getMobile());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebService.loading(ContactUsActivity.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, ContactUsActivity.this);


                JSONObject sendObj = new JSONObject();

                try {

                    sendObj.put("name", name_ed.getText().toString());
                    sendObj.put("email", email_ed.getText().toString());
                    sendObj.put("mobile", phone_ed.getText().toString());
                    sendObj.put("msg", message.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mVolleyService.postDataVolley_without_token("contact_us", WebService.contact_us, sendObj);


            }
        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(ContactUsActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {


                        String message = response.getString("message");
                        WebService.Make_Toast_color(ContactUsActivity.this, message, "success");


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(ContactUsActivity.this, message, "error");
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


                    WebService.Make_Toast_color(ContactUsActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading(ContactUsActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}