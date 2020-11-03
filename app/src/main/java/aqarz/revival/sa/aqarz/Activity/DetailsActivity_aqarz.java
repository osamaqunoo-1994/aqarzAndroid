package aqarz.revival.sa.aqarz.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Comfort_in_details;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import aqarz.revival.sa.aqarz.Adapter.home_viewPager_Adapter;
import aqarz.revival.sa.aqarz.Fragment.OrdersFragment;
import aqarz.revival.sa.aqarz.Modules.ComfortModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules_aqares;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class DetailsActivity_aqarz extends AppCompatActivity {
    ViewPagerIndicator view_pager_indicator;
    ViewPager home_viewPager;
    IResult mResultCallback;
    int oi = 0;

    TextView operation_type_name;
    TextView estate_type_name;
    TextView price;
    TextView address;
    TextView name;
    TextView note;
    TextView type_;
    TextView area;
    TextView room;
    TextView age;
    TextView view_;
    TextView metter_price;
    TextView bathroom;
    TextView purpose;
    TextView name_owner;
    TextView last_update;
    TextView ads_number;
    TextView views_nummm;
    LinearLayout call;
    RecyclerView comfort_rec;
    List<ComfortModules> comfort_list = new ArrayList<>();
    String id_or_aq = "12";
    //
    private ArrayList<String> items_ViewPager = new ArrayList<String>();
//
//    RecyclerView type_RecyclerView;
//    List<TypeModules> typeModules_list = new ArrayList<>();


    ImageView favorit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details_new);

        init();
    }

    public void init() {
//
        home_viewPager = findViewById(R.id.home_viewPager);
        view_pager_indicator = findViewById(R.id.view_pager_indicator);
        operation_type_name = findViewById(R.id.operation_type_name);
        estate_type_name = findViewById(R.id.estate_type_name);
        price = findViewById(R.id.price);
        address = findViewById(R.id.address);
        name = findViewById(R.id.name);
        note = findViewById(R.id.note);
        call = findViewById(R.id.call);
        favorit = findViewById(R.id.favorit);


        type_ = findViewById(R.id.type_);
        area = findViewById(R.id.area);
        room = findViewById(R.id.room);
        age = findViewById(R.id.age);
        view_ = findViewById(R.id.view_);
        metter_price = findViewById(R.id.metter_price);
        bathroom = findViewById(R.id.bathroom);
        purpose = findViewById(R.id.purpose);
        name_owner = findViewById(R.id.name_owner);
        last_update = findViewById(R.id.last_update);
        ads_number = findViewById(R.id.ads_number);
        views_nummm = findViewById(R.id.views_nummm);
        comfort_rec = findViewById(R.id.comfort_rec);

        comfort_rec.setLayoutManager(new GridLayoutManager(this, 2));


        try {
            id_or_aq = getIntent().getStringExtra("id_aqarz");

        } catch (Exception e) {

        }


//        type_RecyclerView = findViewById(R.id.type_RecyclerView);
//
//
//        typeModules_list = Settings.getSettings().getOprationType().getOriginal().getData();
//        LinearLayoutManager layoutManagers
//                = new LinearLayoutManager(DetailsActivity_aqarz.this, LinearLayoutManager.HORIZONTAL, false);
//        type_RecyclerView.setLayoutManager(layoutManagers);
//
//        RecyclerView_All_Type_in_order recyclerView_all_type_in_order = new RecyclerView_All_Type_in_order(DetailsActivity_aqarz.this, typeModules_list);
//        recyclerView_all_type_in_order.addItemClickListener(new RecyclerView_All_Type_in_order.ItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
////                set_fragment(typeModules_list.get(position).getId());
//            }
//        });
//        type_RecyclerView.setAdapter(recyclerView_all_type_in_order);
//
//        items_ViewPager.add("");
//        items_ViewPager.add("");
//        items_ViewPager.add("");
//


//        home_viewPager.setClipToPadding(false);
//        // set padding manually, the more you set the padding the more you see of prev & next page
//        home_viewPager.setPadding(0, 0, 110, 0);
//        // sets a margin b/w individual pages to ensure that there is a gap b/w them
//        home_viewPager.setPageMargin(20);
        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.checkLogin()) {
                    init_volley();
                    WebService.loading(DetailsActivity_aqarz.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity_aqarz.this);
                    try {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("estate_id", "" + id_or_aq);
                        mVolleyService.postDataVolley("favorite", WebService.favorite, jsonObject);


                    } catch (Exception e) {

                    }


                } else {

                    new AlertDialog.Builder(DetailsActivity_aqarz.this)
                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(DetailsActivity_aqarz.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no), null)
                            .show();
                }


            }
        });

        init_volley();
        WebService.loading(DetailsActivity_aqarz.this, true);

        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity_aqarz.this);
        mVolleyService.getDataVolley("single_estat", WebService.single_estat + id_or_aq + "/estate");


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(DetailsActivity_aqarz.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
//                        String message = response.getString("message");


                        if (requestType.equals("favorite")) {

//                            WebService.Make_Toast_color(DetailsActivity_aqarz.this, message, "error");

                            favorit.setImageDrawable(getDrawable(R.drawable.ic_heart));


                        } else {
                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(data);

                            Gson gson = new Gson();

                            HomeModules_aqares homeModules_aqares = gson.fromJson(mJson, HomeModules_aqares.class);


                            operation_type_name.setText(homeModules_aqares.getOperationTypeName());
                            estate_type_name.setText(homeModules_aqares.getEstate_type_name());
                            price.setText(homeModules_aqares.getTotalPrice());


                            if (homeModules_aqares.getAddress() == null) {
                                if (homeModules_aqares.getCity_name() != null) {
                                    address.setText(homeModules_aqares.getCity_name() + " - " + homeModules_aqares.getNeighborhood_name());

                                }

                            } else {
                                address.setText(homeModules_aqares.getAddress());

                            }


                            name.setText(homeModules_aqares.getEstate_type_name() + "");
                            note.setText(homeModules_aqares.getNote() + "");
                            type_.setText(homeModules_aqares.getEstate_type_name() + "");
                            area.setText(homeModules_aqares.getTotalArea() + "");
                            room.setText(homeModules_aqares.getRoomsNumber() + "");
                            age.setText(homeModules_aqares.getEstateAge() + "");
                            view_.setText(homeModules_aqares.getInterface() + "");
                            metter_price.setText(homeModules_aqares.getMeterPrice() + "");
                            bathroom.setText(homeModules_aqares.getBathroomsNumber() + "");
                            purpose.setText(homeModules_aqares.getBathroomsNumber() + "");
                            name_owner.setText(homeModules_aqares.getOwnerName() + "");


                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

                                Date date = format.parse(homeModules_aqares.getCreatedAt().substring(0,19) + "");

                                String dateTime = dateFormat.format(date);
                                last_update.setText(dateTime+ "");

                                System.out.println(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }



//                            last_update.setText(homeModules_aqares.getCreatedAt() + "");
                            ads_number.setText(homeModules_aqares.getId() + "");
                            views_nummm.setText(homeModules_aqares.getSeen_count() + "");

                            call.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    try {
                                        String phone = "0" + homeModules_aqares.getOwnerMobile();
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                                        startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                            for (int i = 0; i < homeModules_aqares.getEstate_file().size(); i++) {


                                items_ViewPager.add(homeModules_aqares.getEstate_file().get(i).getFile());
                            }

                            home_viewPager.setAdapter(new home_viewPager_Adapter(DetailsActivity_aqarz.this, items_ViewPager));
                            view_pager_indicator.setupWithViewPager(home_viewPager);

                            try {
                                if (items_ViewPager.size() > 1) {
                                    oi = 0;

                                    Handler handler = new Handler();

                                    Runnable runnable = new Runnable() {
                                        public void run() {

                                            if (oi == items_ViewPager.size()) {
                                                oi = 0;
                                            }

                                            home_viewPager.setCurrentItem(oi);
                                            oi++;
                                            handler.postDelayed(this, 1000);
                                        }
                                    };
                                    handler.postDelayed(runnable, 1000);

                                }

                            } catch (Exception e) {

                            }


                            RecyclerView_All_Comfort_in_details recyclerView_all_comfort_in_fragment = new RecyclerView_All_Comfort_in_details(DetailsActivity_aqarz.this, homeModules_aqares.getComforts());//
                            comfort_rec.setAdapter(recyclerView_all_comfort_in_fragment);

                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetailsActivity_aqarz.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(DetailsActivity_aqarz.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetailsActivity_aqarz.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(DetailsActivity_aqarz.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetailsActivity_aqarz.this, false);

                WebService.Make_Toast_color(DetailsActivity_aqarz.this, error, "error");


            }
        };


    }

}