package aqarz.revival.sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import aqarz.revival.sa.aqarz.Activity.OprationNew.RentActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import aqarz.revival.sa.aqarz.Adapter.home_viewPager_Adapter;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules_aqares;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

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
        try {
            id_or_aq = getIntent().getStringExtra("id_aqarz");

        } catch (Exception e) {

        }


        init_volley();
        WebService.loading(DetailsActivity.this, true);

        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity.this);
        mVolleyService.getDataVolley("single_request", WebService.single_estat + id_or_aq + "/request");


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

                        JsonParser parser = new JsonParser();
                        JsonElement mJson = parser.parse(data);

                        Gson gson = new Gson();

                        HomeModules homeModules_aqares = gson.fromJson(mJson, HomeModules.class);


                        operation_type_name.setText(homeModules_aqares.getOperation_type_name());
                        estate_type_name.setText(homeModules_aqares.getEstate_type_name());
                        price.setText(homeModules_aqares.getPrice_from() + " - " + homeModules_aqares.getPrice_to());
                        address.setText("----");
                        note.setText(homeModules_aqares.getNote() + "");
                        type_.setText(homeModules_aqares.getRequest_type() + "");
                        name.setText(homeModules_aqares.getEstate_type_name() + "");
                        area.setText(homeModules_aqares.getArea_from() + " - " + homeModules_aqares.getArea_to());
                        room.setText(homeModules_aqares.getRoom_numbers() + "");
                        name_owner.setText(homeModules_aqares.getOwner_name() + "");
                        last_update.setText(homeModules_aqares.getCreated_at() + "");
                        ads_number.setText(homeModules_aqares.getId() + "");
                        views_nummm.setText(homeModules_aqares.getSeen_count() + "");

                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try{
                                    String phone = ""+homeModules_aqares.getOwner_mobile();
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                                    startActivity(intent);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
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