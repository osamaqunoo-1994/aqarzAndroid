package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_chat_main;
import sa.aqarz.Modules.MsgModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class ChateActivity extends AppCompatActivity {
    RecyclerView chate;

    List<MsgModules> ordersModules = new ArrayList<>();

    IResult mResultCallback;
    LinearLayout nodata;

    TextView message;
    TextView notfication;

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chate);
        chate = findViewById(R.id.chate);
        nodata = findViewById(R.id.nodata);
        message = findViewById(R.id.message);
        notfication = findViewById(R.id.notfication);
        back = findViewById(R.id.back);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(ChateActivity.this, LinearLayoutManager.VERTICAL, false);
        chate.setLayoutManager(layoutManager1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
    }
    public void init() {
        WebService.loading(ChateActivity.this, true);

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, ChateActivity.this);

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setBackground(getResources().getDrawable(R.drawable.button_login));

                message.setTextColor(getResources().getColor(R.color.white));


                notfication.setBackground(getResources().getDrawable(R.drawable.mash));

                notfication.setTextColor(getResources().getColor(R.color.black));


                mVolleyService.getDataVolley("my_msg", WebService.my_msg);
            }
        });
        notfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notfication.setBackground(getResources().getDrawable(R.drawable.button_login));
                notfication.setTextColor(getResources().getColor(R.color.white));
                message.setBackground(getResources().getDrawable(R.drawable.mash));
                message.setTextColor(getResources().getColor(R.color.black));
            }
        });


        mVolleyService.getDataVolley("my_msg", WebService.my_msg);

    }
    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(ChateActivity.this, false);


//{"status":true,"code":200,"message":"User Profile","data"


                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("my_msg")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


//                            JSONObject jsonObject = new JSONObject(data);


//                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(data);
                            chate.setAdapter(null);
                            ordersModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                MsgModules msgModules = gson.fromJson(mJson, MsgModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                ordersModules.add(msgModules);


                            }

                            chate.setAdapter(new RecyclerView_chat_main(ChateActivity.this, ordersModules));


                            if (ordersModules.size() != 0) {
                                nodata.setVisibility(View.GONE);
                            } else {
                                nodata.setVisibility(View.VISIBLE);

                            }


                        }

                    } else {

                        String message = response.getString("message");


                        WebService.Make_Toast_color(ChateActivity.this, message, "error");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(ChateActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(ChateActivity.this, message, "error");


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(ChateActivity.this, false);

            }


        };


    }

}