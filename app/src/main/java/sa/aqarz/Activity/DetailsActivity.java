package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import sa.aqarz.Adapter.home_viewPager_Adapter;
import sa.aqarz.Modules.HomeModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class DetailsActivity extends AppCompatActivity {

    IResult mResultCallback;

    TextView operation_type_name;
    TextView estate_type_name;
    TextView price;
    TextView address;
    TextView note;
    TextView type_;
    TextView area;
    TextView room;
    TextView name_owner;
    TextView last_update;
    TextView ads_number;
    TextView views_nummm;
    TextView name;
    String id_or_aq = "1";
    LinearLayout call;
    ImageView favorit;
    HomeModules homeModules_aqares;
    LinearLayout profile;
    LinearLayout chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details_requst_new);

        init();
    }

    public void init() {
        operation_type_name = findViewById(R.id.operation_type_name);
        estate_type_name = findViewById(R.id.estate_type_name);
        price = findViewById(R.id.price);
        address = findViewById(R.id.address);
        note = findViewById(R.id.note);
        type_ = findViewById(R.id.type_);
        area = findViewById(R.id.area);
        room = findViewById(R.id.room);
        name_owner = findViewById(R.id.name_owner);
        last_update = findViewById(R.id.last_update);
        ads_number = findViewById(R.id.ads_number);
        views_nummm = findViewById(R.id.views_nummm);
        name = findViewById(R.id.name);
        call = findViewById(R.id.call);
        favorit = findViewById(R.id.favorit);
        profile = findViewById(R.id.profile);
        chat = findViewById(R.id.chat);
        try {
            id_or_aq = getIntent().getStringExtra("id_aqarz");

        } catch (Exception e) {

        }


        init_volley();
        WebService.loading(DetailsActivity.this, true);

        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity.this);
        mVolleyService.getDataVolley("single_request", WebService.single_estat + id_or_aq + "/request");
        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.checkLogin()) {
                    init_volley();
                    WebService.loading(DetailsActivity.this, true);
                    if (homeModules_aqares.getIn_fav().equals("1")) {
                        favorit.setImageDrawable(getDrawable(R.drawable.ic_like));
                        homeModules_aqares.setIn_fav("0");
                    } else {
                        favorit.setImageDrawable(getDrawable(R.drawable.ic_heart));

                        homeModules_aqares.setIn_fav("1");

                    }
                    VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity.this);
                    try {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type_id", "" + id_or_aq);
                        jsonObject.put("type", "" + "offer");
                        mVolleyService.postDataVolley("favorite", WebService.favorite, jsonObject);


                    } catch (Exception e) {

                    }


                } else {

                    new AlertDialog.Builder(DetailsActivity.this)
                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no), null)
                            .show();
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
                WebService.loading(DetailsActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
                        if (requestType.equals("favorite")) {

//                            WebService.Make_Toast_color(DetailsActivity_aqarz.this, message, "error");

//                            favorit.setImageDrawable(getDrawable(R.drawable.ic_heart));


                        } else {
                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(data);

                            Gson gson = new Gson();

                            homeModules_aqares = gson.fromJson(mJson, HomeModules.class);


                            operation_type_name.setText(homeModules_aqares.getOperation_type_name());
                            estate_type_name.setText(homeModules_aqares.getEstate_type_name());
                            price.setText(homeModules_aqares.getPrice_from() + " - " + homeModules_aqares.getPrice_to());


                            if (homeModules_aqares.getAddress() == null) {
                                if (homeModules_aqares.getCity_name() != null) {
                                    address.setText(homeModules_aqares.getCity_name() + " - " + homeModules_aqares.getNeighborhood_name());

                                }

                            } else {
                                address.setText(homeModules_aqares.getAddress());

                            }
                            note.setText(homeModules_aqares.getNote() + "");
                            type_.setText(homeModules_aqares.getRequest_type() + "");
                            name.setText(homeModules_aqares.getEstate_type_name() + "");
                            area.setText(homeModules_aqares.getArea_from() + " - " + homeModules_aqares.getArea_to());
                            room.setText(homeModules_aqares.getRoom_numbers() + "");
                            name_owner.setText(homeModules_aqares.getOwner_name() + "");


                            profile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    Intent intent = new Intent(DetailsActivity.this, OtherProfileActivity.class);
                                    intent.putExtra("id", homeModules_aqares.getUser_id() + "");
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                                }
                            });
                            chat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(DetailsActivity.this, ChatRoomActivity.class);
                                    intent.putExtra("user_id", homeModules_aqares.getUser_id() + "");
                                    intent.putExtra("parent_id", "-1");
                                    intent.putExtra("nameUser", homeModules_aqares.getOwner_name() + "");
                                    intent.putExtra("imageUser", "");
                                    startActivity(intent);
                                }
                            });
                            chat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(DetailsActivity.this, ChatRoomActivity.class);
                                    intent.putExtra("user_id", homeModules_aqares.getUser_id() + "");
                                    intent.putExtra("parent_id", "-1");
                                    intent.putExtra("nameUser", homeModules_aqares.getOwner_name() + "");
                                    intent.putExtra("imageUser", "");
                                    startActivity(intent);
                                }
                            });

                            if (homeModules_aqares.getIn_fav().equals("1")) {
                                favorit.setImageDrawable(getDrawable(R.drawable.ic_heart));

                            } else {
                                favorit.setImageDrawable(getDrawable(R.drawable.ic_like));

                            }

                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

                                Date date = format.parse(homeModules_aqares.getCreated_at().substring(0, 19) + "");

                                String dateTime = dateFormat.format(date);
                                last_update.setText(dateTime + "");

                                System.out.println(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


//                            last_update.setText(homeModules_aqares.getCreated_at() + "");
                            ads_number.setText(homeModules_aqares.getId() + "");
                            views_nummm.setText(homeModules_aqares.getSeen_count() + "");

                            call.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        String phone = "0" + homeModules_aqares.getOwner_mobile();
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                                        startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetailsActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(DetailsActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetailsActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(DetailsActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetailsActivity.this, false);

                WebService.Make_Toast_color(DetailsActivity.this, error, "error");


            }
        };


    }

}