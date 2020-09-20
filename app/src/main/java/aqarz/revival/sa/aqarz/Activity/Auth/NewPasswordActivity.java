package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class NewPasswordActivity extends AppCompatActivity {
    EditText password;
    ImageView back;
    AppCompatButton update;
    IResult mResultCallback;

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

        password = findViewById(R.id.password);
        update = findViewById(R.id.update);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (password.getText().toString().equals("")) {


                } else {

                    WebService.loading(NewPasswordActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, NewPasswordActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("password", password.getText().toString());


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("New Password", WebService.update_password, sendObj);

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
        };


    }


}