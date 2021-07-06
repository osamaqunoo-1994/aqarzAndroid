package sa.aqarz.Activity;

import android.app.Activity;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.OprationNew.FinanceActivity;
import sa.aqarz.Activity.OprationNew.RateActivity;
import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_details;
import sa.aqarz.Adapter.RecyclerView_coments;
import sa.aqarz.Adapter.RecyclerView_samilar;
import sa.aqarz.Adapter.home_viewPager_Adapter;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Rate;
import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class DetailsActivity_aqarz extends AppCompatActivity {
    ViewPagerIndicator view_pager_indicator;
    ViewPager home_viewPager;
    IResult mResultCallback;
    int oi = 0;
    public static Activity activity;
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
    TextView link;
    TextView views_nummm;
    LinearLayout call;
    RecyclerView comfort_rec;
    List<ComfortModules> comfort_list = new ArrayList<>();
    String id_or_aq = "12";
    LinearLayout profile;
    LinearLayout chat;
    //

    RecyclerView rec_list_all;
    public static final ArrayList<String> items_ViewPager = new ArrayList<String>();
//
//    RecyclerView type_RecyclerView;
//    List<TypeModules> typeModules_list = new ArrayList<>();


    ImageView favorit;
    ImageView back;

    HomeModules_aqares homeModules_aqares;
    List<HomeModules_aqares> homeModules_aqares_list = new ArrayList<>();


    LinearLayout finince;
    LinearLayout rate;
    LinearLayout tent;
    LinearLayout rate_aqarez;
    LinearLayout report;
    RecyclerView list_coments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details_new);
        init();
        activity = this;
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
        profile = findViewById(R.id.profile);
        link = findViewById(R.id.link);
        chat = findViewById(R.id.chat);
        tent = findViewById(R.id.tent);
        rate = findViewById(R.id.rate);
        finince = findViewById(R.id.finince);
        rate_aqarez = findViewById(R.id.rate_aqarez);
        rec_list_all = findViewById(R.id.rec_list_all);
        list_coments = findViewById(R.id.list_coments);
        back = findViewById(R.id.back);
        report = findViewById(R.id.report);


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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {

            id_or_aq = getIntent().getStringExtra("id_aqarz");

            if (id_or_aq != null | !id_or_aq.equals("null")) {
                System.out.println("0000000000000");
                init_volley();
                WebService.loading(DetailsActivity_aqarz.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity_aqarz.this);
                mVolleyService.getDataVolley("single_estat", WebService.single_estat + id_or_aq + "/estate");

            } else {
                Intent intent = getIntent();
                String action = intent.getAction();
                Uri data = intent.getData();

                System.out.println("action" + action);
                System.out.println("data" + data);


                String[] separated = data.toString().split("/");

                String number = separated[3]; // this will contain " they taste good"

                System.out.println("$$$$$$$$$$$$$" + number);


                init_volley();
                WebService.loading(DetailsActivity_aqarz.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity_aqarz.this);
                mVolleyService.getDataVolley("single_estat", WebService.single_estat + number + "/estate");

            }


        } catch (Exception e) {
            e.printStackTrace();
            try {
                Intent intent = getIntent();
                String action = intent.getAction();
                Uri data = intent.getData();

                System.out.println("action" + action);
                System.out.println("data" + data);


                String[] separated = data.toString().split("/");

                String number = separated[4]; // this will contain " they taste good"

                System.out.println("$$$$$$$$$$$$$" + number);


                init_volley();
                WebService.loading(DetailsActivity_aqarz.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity_aqarz.this);
                mVolleyService.getDataVolley("single_estat", WebService.single_estat + number + "/estate");


            } catch (Exception e1) {

            }
        }


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(DetailsActivity_aqarz.this, LinearLayoutManager.HORIZONTAL, false);
        rec_list_all.setLayoutManager(layoutManager1);


        LinearLayoutManager layoutManager1s
                = new LinearLayoutManager(DetailsActivity_aqarz.this, LinearLayoutManager.VERTICAL, false);
        list_coments.setLayoutManager(layoutManager1s);


        tent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetailsActivity_aqarz.this, RentActivity.class);
                intent.putExtra("id", id_or_aq + "");
                startActivity(intent);


            }
        });
        finince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetailsActivity_aqarz.this, FinanceActivity.class);
                intent.putExtra("id", id_or_aq + "");
                startActivity(intent);


            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetailsActivity_aqarz.this, RateActivity.class);
                intent.putExtra("id", id_or_aq + "");
                startActivity(intent);


            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetailsActivity_aqarz.this, ReportAqarezActivity.class);
                intent.putExtra("id", id_or_aq + "");
                startActivity(intent);


            }
        });
        rate_aqarez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment_Rate bottomSheetDialogFragment_rate = new BottomSheetDialogFragment_Rate(id_or_aq + "");

                bottomSheetDialogFragment_rate.show(getSupportFragmentManager(), "");

            }
        });


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
                        if (homeModules_aqares.getIn_fav().equals("1")) {
                            favorit.setImageDrawable(getDrawable(R.drawable.ic_like));
                            homeModules_aqares.setIn_fav("0");
                        } else {
                            favorit.setImageDrawable(getDrawable(R.drawable.ic_heart));

                            homeModules_aqares.setIn_fav("1");

                        }
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type_id", "" + id_or_aq);

                        jsonObject.put("type", "" + "offer");
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


                        if (requestType.equals("smilier")) {


//                            JSONObject jsonObject_data = new JSONObject(data);
//
//                            String data_inside = jsonObject_data.getString("data");
                            JSONArray jsonArray = new JSONArray(data);

                            homeModules_aqares_list.clear();
                            rec_list_all.setAdapter(null);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                    homeModules_aqares_list.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            rec_list_all.setAdapter(new RecyclerView_samilar(DetailsActivity_aqarz.this, homeModules_aqares_list));


                        } else if (requestType.equals("favorite")) {

//                            WebService.Make_Toast_color(DetailsActivity_aqarz.this, message, "error");


                        } else {
                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(data);

                            Gson gson = new Gson();

                            homeModules_aqares = gson.fromJson(mJson, HomeModules_aqares.class);


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
                            link.setText(homeModules_aqares.getUser().getLink() + "");


                            if (homeModules_aqares.getRate() != null) {


                                if (homeModules_aqares.getRate().equals("0")) {
                                    rate_aqarez.setVisibility(View.VISIBLE);

                                } else {
                                    rate_aqarez.setVisibility(View.GONE);

                                }


                            } else {
                                rate_aqarez.setVisibility(View.VISIBLE);

                            }

                            try {
                                list_coments.setAdapter(new RecyclerView_coments(DetailsActivity_aqarz.this, homeModules_aqares.getRates()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            profile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    Intent intent = new Intent(DetailsActivity_aqarz.this, OtherProfileActivity.class);
                                    intent.putExtra("id", homeModules_aqares.getUserId() + "");
                                    startActivity(intent);
//                                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                                }
                            });
                            chat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(DetailsActivity_aqarz.this, ChatRoomActivity.class);
                                    intent.putExtra("user_id", homeModules_aqares.getUserId() + "");
                                    intent.putExtra("parent_id", "-1");
                                    intent.putExtra("nameUser", homeModules_aqares.getOwnerName() + "");
                                    intent.putExtra("imageUser", "");
                                    startActivity(intent);
                                }
                            });
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

                                Date date = format.parse(homeModules_aqares.getCreatedAt().substring(0, 19) + "");

                                String dateTime = dateFormat.format(date);
                                last_update.setText(dateTime + "");

                                System.out.println(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (homeModules_aqares.getIn_fav().equals("1")) {
                                favorit.setImageDrawable(getDrawable(R.drawable.ic_heart));

                            } else {
                                favorit.setImageDrawable(getDrawable(R.drawable.ic_like));

                            }


                            try {

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


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("items_ViewPageritems_ViewPager" + homeModules_aqares.getEstate_file().size());

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


                            init_volley();
                            VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity_aqarz.this);
                            mVolleyService.getDataVolley("smilier", WebService.smilier + "/" + id_or_aq + "/estate");//&request_type=pay


                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetailsActivity_aqarz.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
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