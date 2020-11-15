package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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

public class ChatRoomActivity extends AppCompatActivity {
    RecyclerView message;
    ImageView back;
    List<MsgModules> ordersModules = new ArrayList<>();
    IResult mResultCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        message = findViewById(R.id.message);
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(ChatRoomActivity.this, LinearLayoutManager.VERTICAL, false);
        message.setLayoutManager(layoutManager1);

        WebService.loading(ChatRoomActivity.this, true);

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, ChatRoomActivity.this);


        mVolleyService.getDataVolley("my_msg", WebService.my_msg);

//        msg/2/det


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(ChatRoomActivity.this, false);


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
                            message.setAdapter(null);
                            ordersModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                MsgModules msgModules = gson.fromJson(mJson, MsgModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                ordersModules.add(msgModules);


                            }

                            message.setAdapter(new RecyclerView_chat_main(ChatRoomActivity.this, ordersModules));

//
//                            if (ordersModules.size() != 0) {
//                                nodata.setVisibility(View.GONE);
//                            } else {
//                                nodata.setVisibility(View.VISIBLE);
//
//                            }


                        }

                    } else {

                        String message = response.getString("message");


                        WebService.Make_Toast_color(ChatRoomActivity.this, message, "error");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(ChatRoomActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(ChatRoomActivity.this, message, "error");


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(ChatRoomActivity.this, false);

            }


        };


    }

}