package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Adapter.RecyclerView_ChatRoom;
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
    EditText text_mesage;
    ImageView send;
    String user_id = "";
    TextView name;
    CircleImageView profile;

    String lastMessage = "";
    String msg_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        message = findViewById(R.id.message);
        back = findViewById(R.id.back);
        text_mesage = findViewById(R.id.text_mesage);
        send = findViewById(R.id.send);
        name = findViewById(R.id.name);
        profile = findViewById(R.id.profile);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(ChatRoomActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager1.setStackFromEnd(true);

        message.setLayoutManager(layoutManager1);


        WebService.loading(ChatRoomActivity.this, true);

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, ChatRoomActivity.this);


        try {


            user_id = getIntent().getStringExtra("user_id");
            String parent_id = getIntent().getStringExtra("parent_id");


            if (parent_id.equals("-1")) {
                WebService.loading(ChatRoomActivity.this, true);

                mVolleyService.getDataVolley("my_msg", WebService.my_msg + "/" + user_id);

            } else {


                mVolleyService.getDataVolley("my_msg", WebService.my_msg + "/" + user_id);


            }

            String nameUser = getIntent().getStringExtra("nameUser");
            String imageUser = getIntent().getStringExtra("imageUser");
            name.setText(nameUser);
            if (!imageUser.toString().equals("null")) {
                if (!imageUser.toString().equals("")) {
                    Picasso.get().load(imageUser).error(R.drawable.ic_user_un).into(profile);

                } else {
                    Picasso.get().load(R.drawable.ic_user_un).error(R.drawable.ic_user_un).into(profile);

                }

            } else {
                Picasso.get().load(R.drawable.ic_user_un).error(R.drawable.ic_user_un).into(profile);

            }


        } catch (Exception e) {

        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!text_mesage.getText().toString().equals("")) {
                    VolleyService mVolleyService = new VolleyService(mResultCallback, ChatRoomActivity.this);


                    JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("user_id", user_id);
                        jsonObject.put("title", "##");
                        jsonObject.put("body", text_mesage.getText().toString());

                        if (msg_id != null) {
                            if (!msg_id.equals("")) {
                                jsonObject.put("msg_id", msg_id);

                            }

                        }
                        lastMessage = text_mesage.getText().toString();
//                        jsonObject.put("parent_id", "");
                    } catch (Exception e) {

                    }

                    WebService.loading(ChatRoomActivity.this, true);
                    System.out.println("jsonObjectjsonObjectjsonObject" + jsonObject.toString());
                    mVolleyService.postDataVolley("send_msg", WebService.send_msg, jsonObject);


                }


            }
        });

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
                                msg_id = msgModules.getMsg_id() + "";
//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                ordersModules.add(msgModules);


                            }

                            message.setAdapter(new RecyclerView_ChatRoom(ChatRoomActivity.this, ordersModules));

//
//                            if (ordersModules.size() != 0) {
//                                nodata.setVisibility(View.GONE);
//                            } else {
//                                nodata.setVisibility(View.VISIBLE);
//
//                            }

//                            WebService.loading(ChatRoomActivity.this, true);

                            init_volley();
                            VolleyService mVolleyService = new VolleyService(mResultCallback, ChatRoomActivity.this);

                            mVolleyService.getDataVolley("msg", WebService.msg + "/" + user_id + "/det");


                        } else if (requestType.equals("send_msg")) {

                            String messagecc = response.getString("message");


                            ordersModules.add(new MsgModules(lastMessage, 1));

                            message.setAdapter(new RecyclerView_ChatRoom(ChatRoomActivity.this, ordersModules));
                            text_mesage.setText("");
//                            WebService.Make_Toast_color(ChatRoomActivity.this, messagecc, "success");

                        } else {
                            System.out.println("ooorororroro");
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