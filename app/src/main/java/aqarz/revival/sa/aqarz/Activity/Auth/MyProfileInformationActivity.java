package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class MyProfileInformationActivity extends AppCompatActivity {
    EditText name_ed;
    TextView email_ed;
    TextView phone_ed;

    Button done;


    IResult mResultCallback;

    ImageView back;
    ImageView edit_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_information);

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
        name_ed = findViewById(R.id.name_ed);
        email_ed = findViewById(R.id.email_ed);
        phone_ed = findViewById(R.id.phone_ed);
        done = findViewById(R.id.done);
        back = findViewById(R.id.back);
        edit_image = findViewById(R.id.edit_image);


        try {
            name_ed.setText(Settings.GetUser().getName());
            phone_ed.setText(Settings.GetUser().getMobile());
            email_ed.setText(Settings.GetUser().getEmail());


        } catch (Exception e) {

        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {










            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name_ed.getText().toString().equals("") |
                        email_ed.getText().toString().equals("") |
                        phone_ed.getText().toString().equals("")) {
                    WebService.Make_Toast_color(MyProfileInformationActivity.this, getResources().getString(R.string.fillallfileds) + "", "error");
                } else {
                    WebService.loading(MyProfileInformationActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, MyProfileInformationActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("name", name_ed.getText().toString());
//                        sendObj.put("email", email_ed.getText().toString());
//                        sendObj.put("mobile", phone_ed.getText().toString());


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("Update", WebService.update_my_profile, sendObj);

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
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(MyProfileInformationActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                        Hawk.put("user", data);


                        String message = response.getString("message");

                        WebService.Make_Toast_color(MyProfileInformationActivity.this, message, "success");

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(MyProfileInformationActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.toString());

                try {

                    NetworkResponse response = error.networkResponse;
                    String json = new String(response.data);


                } catch (Exception e) {

                }


                WebService.loading(MyProfileInformationActivity.this, false);
                WebService.Make_Toast_color(MyProfileInformationActivity.this, error.getMessage(), "error");


            }
        };


    }


}