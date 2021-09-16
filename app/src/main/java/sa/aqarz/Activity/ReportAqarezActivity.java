package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Adapter.RecyclerView_ChatRoom;
import sa.aqarz.Modules.MsgModules;
import sa.aqarz.NewAqarz.DetaislAqarzActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class ReportAqarezActivity extends AppCompatActivity {

    IResult mResultCallback;

    Button send;
    ImageView back;

    RadioButton st_1, st_2, st_3;
    EditText edt_des;
    String estate_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_aqarez);

        send = findViewById(R.id.send);
        back = findViewById(R.id.back);
        st_1 = findViewById(R.id.st_1);
        st_2 = findViewById(R.id.st_2);
        st_3 = findViewById(R.id.st_3);
        edt_des = findViewById(R.id.edt_des);


        try {
            estate_id = getIntent().getStringExtra("estate_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//('error_data', 'error_image', 'other')

                if (Settings.checkLogin()) {

                    String type = "";
                    if (st_1.isChecked()) {
                        type = "error_data";
                    }
                    if (st_2.isChecked()) {
                        type = "error_image";

                    }
                    if (st_3.isChecked()) {
                        type = "other";

                    }

                    init_volley();

                    VolleyService mVolleyService = new VolleyService(mResultCallback, ReportAqarezActivity.this);
                    JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("report_type", type);
                        jsonObject.put("estate_id", estate_id);

                        jsonObject.put("body", edt_des.getText().toString());


                    } catch (Exception e) {

                    }

                    WebService.loading(ReportAqarezActivity.this, true);
                    mVolleyService.postDataVolley("report", WebService.report, jsonObject);

                } else {
                    startActivity(new Intent(ReportAqarezActivity.this, LoginActivity.class));

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
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(ReportAqarezActivity.this, false);


//{"status":true,"code":200,"message":"User Profile","data"


                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");


                        WebService.Make_Toast_color(ReportAqarezActivity.this, message, "success");


                    } else {

                        String message = response.getString("message");


                        WebService.Make_Toast_color(ReportAqarezActivity.this, message, "error");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(ReportAqarezActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(ReportAqarezActivity.this, message, "error");


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(ReportAqarezActivity.this, false);

            }


        };


    }

}