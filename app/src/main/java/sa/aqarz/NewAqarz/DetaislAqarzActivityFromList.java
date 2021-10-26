package sa.aqarz.NewAqarz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.ChatRoomActivity;
import sa.aqarz.Activity.ReportAqarezActivity;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_details;
import sa.aqarz.Adapter.RecyclerView_samilar;
import sa.aqarz.Adapter.home_viewPager_Adapter;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.imagemodules;
import sa.aqarz.NewAqarz.AqqAqarz.AddRentalInstallmentActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class DetaislAqarzActivityFromList extends AppCompatActivity {
    ViewPagerIndicator view_pager_indicator;
    ViewPager home_viewPager;
    IResult mResultCallback;
    RecyclerView rec_list_all;
    String id_or_aq;
    HomeModules_aqares homeModules_aqares;
    List<HomeModules_aqares> homeModules_aqares_list = new ArrayList<>();
    public static final ArrayList<imagemodules> items_ViewPager = new ArrayList<imagemodules>();
    int oi = 0;
    public static Activity activity;

    ScaleRatingBar rate_user;
    TextView estate_type_name;
    TextView area;
    TextView address;
    CircleImageView Image_user;

    TextView bathroom;
    TextView room;
    TextView operation_type_name;
    TextView views_nummm;
    TextView last_update;
    TextView ads_number;
    TextView loungs;
    TextView dining_room;
    TextView kitchens;
    TextView boards;
    TextView why;
    TextView interfacex;
    TextView age;
    TextView view_Street;
    TextView note;
    TextView price;
    TextView rate;
    TextView name_owner;
    TextView report;
    LinearLayout call;
    LinearLayout chat;
    LinearLayout whatsapp;
    LinearLayout rate_;
    LinearLayout is_rent;
    ImageView map_location;
    RecyclerView all_comfort;
    RelativeLayout map;
    Button request;
    TextView type_;
    TextView rentPrice;
    LinearLayout emp_lay;
    LinearLayout emp_lay_1;
    LinearLayout emp_lay_2;
    CircleImageView company_enp_image;
    TextView company_enp_name;
    TextView name_emp;
    TextView name_emp1;
    LinearLayout contact_lay;

    String id_user = "";
    String name_user = "";
    TextView is_rent_installment;


    TextView Number_parking_add;
    TextView unit_number;
    TextView number_lifts_add;

    LinearLayout swipe;
    home_viewPager_Adapter home_viewPager_adapter;

    int postion = 0;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaisl_aqarz);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        activity = this;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("");
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    //  Collapsed

                    System.out.println("^^^^^^^^^^^^^^^^^^^^");
                    toolbar.setTitle(homeModules_aqares.getTotalPrice() + " " + getResources().getString(R.string.SAR));
                    toolbar.setTitleTextColor(getResources().getColor(R.color.black));


                    type_.setVisibility(View.GONE);

                } else {

                    type_.setVisibility(View.VISIBLE);

                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&");

                    //Expanded
                    toolbar.setTitle("");


                }
            }
        });


        home_viewPager = findViewById(R.id.home_viewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (home_viewPager != null) {
                home_viewPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                finish();
            }
        });
        init();
    }

    public void init() {

        type_ = findViewById(R.id.type_);
        number_lifts_add = findViewById(R.id.number_lifts_add);
        unit_number = findViewById(R.id.unit_number);
        Number_parking_add = findViewById(R.id.Number_parking_add);


        emp_lay_1 = findViewById(R.id.emp_lay_1);
        swipe = findViewById(R.id.swipe);
        rate_ = findViewById(R.id.rate_);
        rec_list_all = findViewById(R.id.rec_list_all);
        company_enp_name = findViewById(R.id.company_enp_name);
        company_enp_image = findViewById(R.id.company_enp_image);
        emp_lay = findViewById(R.id.emp_lay);
        map = findViewById(R.id.map);
        home_viewPager = findViewById(R.id.home_viewPager);
        view_pager_indicator = findViewById(R.id.view_pager_indicator);
        price = findViewById(R.id.price);
        emp_lay_2 = findViewById(R.id.emp_lay_2);
        name_emp = findViewById(R.id.name_emp);
        name_emp1 = findViewById(R.id.name_emp1);
        estate_type_name = findViewById(R.id.estate_type_name);
        request = findViewById(R.id.request);
        is_rent = findViewById(R.id.is_rent);
        area = findViewById(R.id.area);
        call = findViewById(R.id.call);
        rate = findViewById(R.id.rate);
        address = findViewById(R.id.address);
        report = findViewById(R.id.report);
        name_owner = findViewById(R.id.name_owner);
        rate_user = findViewById(R.id.rate_user);
        operation_type_name = findViewById(R.id.operation_type_name);
        Image_user = findViewById(R.id.Image_user);
        last_update = findViewById(R.id.last_update);
        ads_number = findViewById(R.id.ads_number);
        views_nummm = findViewById(R.id.views_nummm);
        all_comfort = findViewById(R.id.all_comfort);
        rentPrice = findViewById(R.id.rentPrice);
        contact_lay = findViewById(R.id.contact_lay);
        is_rent_installment = findViewById(R.id.is_rent_installment);

        bathroom = findViewById(R.id.bathroom);
        room = findViewById(R.id.room);
        loungs = findViewById(R.id.loungs);
        dining_room = findViewById(R.id.dining_room);
        kitchens = findViewById(R.id.kitchens);
        boards = findViewById(R.id.boards);
        why = findViewById(R.id.why);
        interfacex = findViewById(R.id.interfacex);
        age = findViewById(R.id.age);
        view_Street = findViewById(R.id.view_Street);
        note = findViewById(R.id.note);
        chat = findViewById(R.id.chat);
        whatsapp = findViewById(R.id.whatsapp);
        map_location = findViewById(R.id.map_location);

        all_comfort.setLayoutManager(new GridLayoutManager(this, 2));


        action_click();


        try {

            String postionz = getIntent().getStringExtra("postion");

            postion = Integer.valueOf(postionz + "");


            id_or_aq = getIntent().getStringExtra("id_aqarz");

            if (id_or_aq != null | !id_or_aq.equals("null")) {
                System.out.println("0000000000000");
                init_volley();
                WebService.loading(DetaislAqarzActivityFromList.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivityFromList.this);
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
                WebService.loading(DetaislAqarzActivityFromList.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivityFromList.this);
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
                WebService.loading(DetaislAqarzActivityFromList.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivityFromList.this);
                mVolleyService.getDataVolley("single_estat", WebService.single_estat + number + "/estate");


            } catch (Exception e1) {

            }
        }
        rate_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(DetaislAqarzActivityFromList.this, RateDetailsActivity.class);
                inte.putExtra("id_or_aq", id_or_aq);
                inte.putExtra("is_rate", homeModules_aqares.getIs_rate() + "");
                startActivity(inte);


            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetaislAqarzActivityFromList.this, ReportAqarezActivity.class);
                intent.putExtra("id", id_or_aq + "");
                startActivity(intent);


            }
        });

        swipe.setOnTouchListener(new OnSwipeTouchListener(DetaislAqarzActivityFromList.this) {
            public void onSwipeTop() {
//                Toast.makeText(DetaislAqarzActivityFromList.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {


                System.out.println("postion" + postion);
                System.out.println("XXXpostion" + ListAqarzActivity.homeModules_aqares_list.size());

                postion = postion + 1;

                if (postion < ListAqarzActivity.homeModules_aqares_list.size() - 1) {

                    if (handler != null) {
                        handler.removeCallbacksAndMessages(null);
                    }
                    System.out.println("0000000000000");
                    WebService.loading(DetaislAqarzActivityFromList.this, true);
                    init_volley();

                    VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivityFromList.this);
                    mVolleyService.getDataVolley("single_estat", WebService.single_estat + ListAqarzActivity.homeModules_aqares_list.get(postion).getId() + "/estate");
                }


//                Toast.makeText(DetaislAqarzActivityFromList.this, "right", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft() {
//                Toast.makeText(DetaislAqarzActivityFromList.this, "left", Toast.LENGTH_SHORT).show();
                System.out.println("postion" + postion);
                System.out.println("XXXpostion" + ListAqarzActivity.homeModules_aqares_list.size());

                postion = postion - 1;

                if (postion > 0) {
                    if (handler != null) {
                        handler.removeCallbacksAndMessages(null);
                    }
                    System.out.println("0000000000000");
                    WebService.loading(DetaislAqarzActivityFromList.this, true);
                    init_volley();

                    VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivityFromList.this);
                    mVolleyService.getDataVolley("single_estat", WebService.single_estat + ListAqarzActivity.homeModules_aqares_list.get(postion).getId() + "/estate");
                }

            }

            public void onSwipeBottom() {
//                Toast.makeText(DetaislAqarzActivityFromList.this, "bottom", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://aqarz.sa/estate/" + id_or_aq + "/show");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

            return true;
        }
        if (id == R.id.hide) {

            if (Settings.checkLogin()) {

                init_volley();
                WebService.loading(DetaislAqarzActivityFromList.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivityFromList.this);
                mVolleyService.getDataVolley("hide", WebService.hide + "/" + id_or_aq + "/estate");

            } else {
                startActivity(new Intent(DetaislAqarzActivityFromList.this, LoginActivity.class));

            }
            return true;
        }
        if (id == R.id.love) {
            if (Settings.checkLogin()) {
                init_volley();
                WebService.loading(DetaislAqarzActivityFromList.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivityFromList.this);
                try {
                    if (homeModules_aqares.getIn_fav().equals("1")) {
//                        favorit.setImageDrawable(getDrawable(R.drawable.ic_like));
                        homeModules_aqares.setIn_fav("0");
                    } else {
//                        favorit.setImageDrawable(getDrawable(R.drawable.ic_heart));

                        homeModules_aqares.setIn_fav("1");

                    }
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type_id", "" + id_or_aq);

                    jsonObject.put("type", "" + "offer");
                    mVolleyService.postDataVolley("favorite", WebService.favorite, jsonObject);


                } catch (Exception e) {

                }


            } else {
                startActivity(new Intent(DetaislAqarzActivityFromList.this, LoginActivity.class));

//                    new AlertDialog.Builder(DetailsActivity_aqarz.this)
//                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
//                            .setCancelable(false)
//                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    Intent intent = new Intent(DetailsActivity_aqarz.this, LoginActivity.class);
////                                intent.putExtra("from", "splash");
//                                    startActivity(intent);
//
//                                }
//                            })
//                            .setNegativeButton(getResources().getString(R.string.no), null)
//                            .show();


            }

            return true;
        }

        return super.

                onOptionsItemSelected(item);

    }


    public void action_click() {


        Image_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetaislAqarzActivityFromList.this, OtherProfileActivity.class);
                intent.putExtra("id", id_user + "");
                startActivity(intent);
//                                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });
        name_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetaislAqarzActivityFromList.this, OtherProfileActivity.class);
                intent.putExtra("id", id_user + "");
                startActivity(intent);
//                                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Settings.checkLogin()) {


                    startActivity(new Intent(DetaislAqarzActivityFromList.this, LoginActivity.class));

                } else {
                    Intent intent = new Intent(DetaislAqarzActivityFromList.this, ChatRoomActivity.class);
                    intent.putExtra("user_id", id_user + "");
                    intent.putExtra("parent_id", "-1");
                    intent.putExtra("nameUser", name_user + "");
                    intent.putExtra("imageUser", "");
                    startActivity(intent);
                }
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetaislAqarzActivityFromList.this, AddRentalInstallmentActivity.class);

                intent.putExtra("rent_price", homeModules_aqares.getRent_installment_price());

                startActivity(intent);
            }
        });
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
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String url = "https://api.whatsapp.com/send?phone=+966" + homeModules_aqares.getOwnerMobile();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
//                    String number = "0" + homeModules_aqares.getOwnerMobile();
//
//                    Uri uri = Uri.parse("smsto:" + number);
//                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
//                    i.setPackage("com.whatsapp");
//                    startActivity(Intent.createChooser(i, ""));
                } catch (Exception e) {
                    Toast.makeText(DetaislAqarzActivityFromList.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

        map_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    // Creates an Intent that will load a map of San Francisco
                    Uri gmmIntentUri = Uri.parse("geo:" + homeModules_aqares.getLat() + "," + homeModules_aqares.getLan());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("geo:" + homeModules_aqares.getLat() + "," + homeModules_aqares.getLan());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
//                                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
                        String message = response.getString("message");


                        if (requestType.equals("smilier")) {


//                            JSONObject jsonObject_data = new JSONObject(data);

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
                            LinearLayoutManager layoutManager1
                                    = new LinearLayoutManager(DetaislAqarzActivityFromList.this, LinearLayoutManager.HORIZONTAL, false);
                            rec_list_all.setLayoutManager(layoutManager1);


                            rec_list_all.setAdapter(new RecyclerView_samilar(DetaislAqarzActivityFromList.this, homeModules_aqares_list));


                        } else if (requestType.equals("favorite")) {

                            WebService.Make_Toast_color(DetaislAqarzActivityFromList.this, message, "error");


                        } else if (requestType.equals("hide")) {

                            WebService.Make_Toast_color(DetaislAqarzActivityFromList.this, message, "error");


                        } else {
                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(data);

                            Gson gson = new Gson();

                            homeModules_aqares = gson.fromJson(mJson, HomeModules_aqares.class);

//
                            operation_type_name.setText(homeModules_aqares.getOperationTypeName());
                            estate_type_name.setText(homeModules_aqares.getEstate_type_name());
                            price.setText(homeModules_aqares.getTotalPrice());
//
                            if (homeModules_aqares.getCity_name() != null) {
                                address.setText(homeModules_aqares.getCity_name() + " - " + homeModules_aqares.getNeighborhood_name());

                            } else {
                                address.setText(homeModules_aqares.getAddress());

                            }


                            if (homeModules_aqares.getIs_rent_installment() != null) {
                                if (!homeModules_aqares.getIs_rent_installment().equals("null")) {

                                    if (homeModules_aqares.getIs_rent_installment().equals("1")) {

                                        is_rent_installment.setVisibility(View.VISIBLE);

                                    } else {
                                        is_rent_installment.setVisibility(View.GONE);

                                    }
                                } else {
                                    is_rent_installment.setVisibility(View.GONE);

                                }
                            } else {
                                is_rent_installment.setVisibility(View.GONE);

                            }


//                            if (homeModules_aqares.getAddress() == null) {
//
//                            } else {
//
//                            }

                            try {


                                if (homeModules_aqares.getUser().getEmp() != null) {
                                    if (homeModules_aqares.getUser().getEmp() != null) {

                                        emp_lay_1.setVisibility(View.VISIBLE);
                                        emp_lay_2.setVisibility(View.GONE);


                                        name_owner.setText(homeModules_aqares.getUser().getEmp().getName() + "");

                                        name_emp.setText(homeModules_aqares.getUser().getName() + "");

                                        Glide.with(DetaislAqarzActivityFromList.this).load(homeModules_aqares.getUser().getEmp().getLogo() + "").into(Image_user);
                                        id_user = homeModules_aqares.getUser().getId() + "";
                                        name_user = homeModules_aqares.getUser().getName() + "";
                                    } else {
                                        emp_lay_1.setVisibility(View.GONE);
                                        emp_lay_2.setVisibility(View.VISIBLE);
                                        name_emp1.setText(homeModules_aqares.getUser().getOnwer_name() + "");

                                        name_owner.setText(homeModules_aqares.getUser().getName() + "");
                                        Glide.with(DetaislAqarzActivityFromList.this).load(homeModules_aqares.getUser().getLogo() + "").into(Image_user);
                                        id_user = homeModules_aqares.getUser().getId() + "";
                                        name_user = homeModules_aqares.getUser().getName() + "";

                                    }
                                } else {
                                    name_owner.setText(homeModules_aqares.getUser().getName() + "");
                                    name_emp1.setText(homeModules_aqares.getUser().getOnwer_name() + "");

                                    Glide.with(DetaislAqarzActivityFromList.this).load(homeModules_aqares.getUser().getLogo() + "").into(Image_user);

                                    emp_lay_1.setVisibility(View.GONE);
                                    emp_lay_2.setVisibility(View.VISIBLE);
                                    id_user = homeModules_aqares.getUser().getId() + "";
                                    name_user = homeModules_aqares.getUser().getName() + "";

                                }

                                if (id_user.equals(Settings.GetUser().getId() + "")) {
                                    contact_lay.setVisibility(View.GONE);
                                } else {
                                    contact_lay.setVisibility(View.VISIBLE);

                                }

                            } catch (Exception e) {

                            }


                            type_.setText(homeModules_aqares.getOperationTypeName() + "");
//
//                            name.setText(homeModules_aqares.getEstate_type_name() + "");
                            if (homeModules_aqares.getRate() != null) {
                                if (!homeModules_aqares.getRate().equals("null")) {
                                    rate.setText(homeModules_aqares.getRate() + "");

                                }

                            }

                            if (homeModules_aqares.getIs_rent_installment().equals("1")) {
                                is_rent.setVisibility(View.VISIBLE);

                            } else {
                                is_rent.setVisibility(View.GONE);

                            }

                            if (homeModules_aqares.getRent_installment_price() != null) {
                                if (!homeModules_aqares.getRent_installment_price().equals("null")) {

                                    int yy = Integer.valueOf(homeModules_aqares.getRent_installment_price());
                                    double sd = yy / 12;
                                    rentPrice.setText(sd + "");

                                }

                            }


//                            note.setText(homeModules_aqares.getNote() + "");
//                            type_.setText(homeModules_aqares.getEstate_type_name() + "");
                            kitchens.setText(homeModules_aqares.getKitchenNumber() + "");
                            interfacex.setText(homeModules_aqares.getInterface() + "");
                            loungs.setText(homeModules_aqares.getLoungesNumber() + "");
                            dining_room.setText(homeModules_aqares.getDiningRoomsNumber() + "");
                            area.setText(homeModules_aqares.getTotalArea() + "");
                            room.setText(homeModules_aqares.getBedroom_number() + "");



                            unit_number.setText(homeModules_aqares.getUnit_number() + "");


                            if (homeModules_aqares.getElevators_number() != null) {
                                if (!homeModules_aqares.getElevators_number().equals("null")) {
                                    number_lifts_add.setText(homeModules_aqares.getElevators_number() + "");

                                }

                            }
                            if (homeModules_aqares.getParking_spaces_numbers() != null) {
                                if (!homeModules_aqares.getParking_spaces_numbers().equals("null")) {
                                    Number_parking_add.setText(homeModules_aqares.getParking_spaces_numbers() + "");

                                }

                            }

                            if (homeModules_aqares.getEstate_use_type() != null) {
                                if (!homeModules_aqares.getEstate_use_type().equals("null")) {
                                    why.setText(homeModules_aqares.getEstate_use_type() + "");

                                }

                            }
                            if (homeModules_aqares.getStreetView() != null) {
                                if (!homeModules_aqares.getStreetView().equals("null")) {
                                    view_Street.setText(homeModules_aqares.getStreetView() + "");

                                }

                            }
                            if (homeModules_aqares.getEstateAge() != null) {
                                if (!homeModules_aqares.getEstateAge().equals("null")) {
                                    age.setText(homeModules_aqares.getEstateAge() + "");

                                }

                            }
                            boards.setText(homeModules_aqares.getBoardsNumber() + "");
////                            metter_price.setText(homeModules_aqares.getMeterPrice() + "");
                            bathroom.setText(homeModules_aqares.getBathroomsNumber() + "");
//                            purpose.setText(homeModules_aqares.getBathroomsNumber() + "");

                            try {
//                                name_owner.setText(homeModules_aqares.getUser().getName() + "");

                            } catch (Exception e) {

                            }
//                            link.setText(homeModules_aqares.getUser().getLink() + "");


                            try {
                                if (homeModules_aqares.getUser() != null) {
                                    rate_user.setRating(Float.valueOf(homeModules_aqares.getUser().getRate()));

                                }
                            } catch (Exception e) {

                            }
                            try {
                                if (homeModules_aqares.getUser() != null) {

//                                    Glide.with(DetaislAqarzActivity.this).load(homeModules_aqares.getUser().get)
                                }
                            } catch (Exception e) {

                            }
//
                            if (homeModules_aqares.getNote() != null) {
                                if (!homeModules_aqares.getNote().equals("null")) {
                                    note.setText(homeModules_aqares.getNote() + "");

                                } else {
                                    note.setText("لا يوجد تفاصيل");

                                }
                            } else {
                                note.setText("لا يوجد تفاصيل");

                            }
                            if (homeModules_aqares.getEstateAge() != null) {

                                if (!homeModules_aqares.getEstateAge().toString().equals("null")) {
                                    age.setText(homeModules_aqares.getEstateAge() + "");

                                } else {
                                    age.setText("-");

                                }
                            } else {
                                age.setText("-");

                            }
                            WebService.loading(DetaislAqarzActivityFromList.this, false);

//

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
//                            if (homeModules_aqares.getIn_fav().equals("1")) {
//                                favorit.setImageDrawable(getDrawable(R.drawable.ic_heart));
//
//                            } else {
//                                favorit.setImageDrawable(getDrawable(R.drawable.ic_like));
//
//                            }


//                            try {
//
////                            last_update.setText(homeModules_aqares.getCreatedAt() + "");
                            ads_number.setText(homeModules_aqares.getId() + "");
                            views_nummm.setText(homeModules_aqares.getSeen_count() + "");
//


//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
////                            System.out.println("items_ViewPageritems_ViewPager" + homeModules_aqares.getEstate_file().size());


//                            items_ViewPager.clear();
//                            for (int i = 0; i < homeModules_aqares.getEstate_file().size(); i++) {
//                                items_ViewPager.add(new imagemodules(homeModules_aqares.getEstate_file().get(i).getFile() + "", "image"));
//                            }
//                            if (homeModules_aqares.getVideo() != null) {
//                                if (!homeModules_aqares.getVideo().equals("null")) {
//                                    items_ViewPager.add(new imagemodules(homeModules_aqares.getVideo() + "", "video"));
//                                }
//                            }

                            items_ViewPager.clear();
                            for (int i = 0; i < homeModules_aqares.getEstate_file().size(); i++) {

                                if(homeModules_aqares.getEstate_file().get(i).getType().equals("images")){
                                    items_ViewPager.add(new imagemodules(homeModules_aqares.getEstate_file().get(i).getFile() + "", "image"));

                                }else{
                                    items_ViewPager.add(new imagemodules(homeModules_aqares.getEstate_file().get(i).getFile() + "", "video"));

                                }
                            }

//                            if (home_viewPager_adapter != null) {
//                                home_viewPager_adapter.Refa();
//                            }

//                            if(home_viewPager_adapter!=null){
//                                home_viewPager_adapter.Refa();
//
//                            }
                            try {
                                if (items_ViewPager.size() > 1) {
                                    oi = 0;

//                                    Handler handler = new Handler();
//
//                                    Runnable runnable = new Runnable() {
//                                        public void run() {
//
//
//
////
////                                            if (oi == items_ViewPager.size()) {
////                                                oi = 0;
////                                            }
////
////                                            home_viewPager.setCurrentItem(oi);
////                                            oi++;
//                                            handler.postDelayed(this, 1500);
//                                        }
//                                    };
//                                    handler.postDelayed(runnable, 3000);
//


                                    handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            // Actions to do after 10 seconds
                                            home_viewPager_adapter = new home_viewPager_Adapter(DetaislAqarzActivityFromList.this, items_ViewPager);
                                            home_viewPager.setAdapter(home_viewPager_adapter);
                                            view_pager_indicator.setupWithViewPager(home_viewPager);
//

                                        }
                                    }, 1500);
                                }

                            } catch (Exception e) {

                            }
//
//
                            RecyclerView_All_Comfort_in_details recyclerView_all_comfort_in_fragment = new RecyclerView_All_Comfort_in_details(DetaislAqarzActivityFromList.this, homeModules_aqares.getComforts());//
                            all_comfort.setAdapter(recyclerView_all_comfort_in_fragment);


//                            init_volley();
//                            VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivityFromList.this);
//                            mVolleyService.getDataVolley("smilier", WebService.smilier + "/" + id_or_aq + "/estate");//&request_type=pay


                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetaislAqarzActivityFromList.this, message, "error");
                    }

                    WebService.loading(DetaislAqarzActivityFromList.this, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(DetaislAqarzActivityFromList.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetaislAqarzActivityFromList.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(DetaislAqarzActivityFromList.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetaislAqarzActivityFromList.this, false);

                WebService.Make_Toast_color(DetaislAqarzActivityFromList.this, error, "error");


            }
        };


    }

    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context ctx) {
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }
    }
}