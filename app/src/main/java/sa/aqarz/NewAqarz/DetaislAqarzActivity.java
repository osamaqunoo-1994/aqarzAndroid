package sa.aqarz.NewAqarz;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.willy.ratingbar.ScaleRatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.ReportAqarezActivity;
import sa.aqarz.Activity.check_login;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_details;
import sa.aqarz.Adapter.RecyclerView_coments;
import sa.aqarz.Adapter.RecyclerView_samilar;
import sa.aqarz.Adapter.home_viewPager_Adapter;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.imagemodules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class DetaislAqarzActivity extends AppCompatActivity {
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
    ImageView map_location;

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

                } else {


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

        rec_list_all = findViewById(R.id.rec_list_all);
        home_viewPager = findViewById(R.id.home_viewPager);
        view_pager_indicator = findViewById(R.id.view_pager_indicator);
        price = findViewById(R.id.price);
        estate_type_name = findViewById(R.id.estate_type_name);
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


        try {

            id_or_aq = getIntent().getStringExtra("id_aqarz");

            if (id_or_aq != null | !id_or_aq.equals("null")) {
                System.out.println("0000000000000");
                init_volley();
                WebService.loading(DetaislAqarzActivity.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivity.this);
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
                WebService.loading(DetaislAqarzActivity.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivity.this);
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
                WebService.loading(DetaislAqarzActivity.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivity.this);
                mVolleyService.getDataVolley("single_estat", WebService.single_estat + number + "/estate");


            } catch (Exception e1) {

            }
        }
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetaislAqarzActivity.this, ReportAqarezActivity.class);
                intent.putExtra("id", id_or_aq + "");
                startActivity(intent);


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
            init_volley();
            WebService.loading(DetaislAqarzActivity.this, true);

            VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivity.this);
            mVolleyService.getDataVolley("hide", WebService.hide + "/" + id_or_aq + "/estate");

            return true;
        }
        if (id == R.id.love) {
            if (Settings.checkLogin()) {
                init_volley();
                WebService.loading(DetaislAqarzActivity.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivity.this);
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
                startActivity(new Intent(DetaislAqarzActivity.this, check_login.class));

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

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(DetaislAqarzActivity.this, false);
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
                                    = new LinearLayoutManager(DetaislAqarzActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            rec_list_all.setLayoutManager(layoutManager1);


                            rec_list_all.setAdapter(new RecyclerView_samilar(DetaislAqarzActivity.this, homeModules_aqares_list));


                        } else if (requestType.equals("favorite")) {

                            WebService.Make_Toast_color(DetaislAqarzActivity.this, message, "error");


                        } else if (requestType.equals("hide")) {

                            WebService.Make_Toast_color(DetaislAqarzActivity.this, message, "error");


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

//                            if (homeModules_aqares.getAddress() == null) {
//
//                            } else {
//
//                            }


//
//                            name.setText(homeModules_aqares.getEstate_type_name() + "");
                            if (homeModules_aqares.getRate() != null) {
                                if (!homeModules_aqares.getRate().equals("null")) {
                                    rate.setText(homeModules_aqares.getRate() + "");

                                }

                            }
//                            note.setText(homeModules_aqares.getNote() + "");
//                            type_.setText(homeModules_aqares.getEstate_type_name() + "");
                            kitchens.setText(homeModules_aqares.getKitchenNumber() + "");
                            interfacex.setText(homeModules_aqares.getInterface() + "");
                            loungs.setText(homeModules_aqares.getLoungesNumber() + "");
                            dining_room.setText(homeModules_aqares.getDiningRoomsNumber() + "");
                            area.setText(homeModules_aqares.getTotalArea() + "");
                            room.setText(homeModules_aqares.getRoomsNumber() + "");
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
                            name_owner.setText(homeModules_aqares.getOwnerName() + "");
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
//                            if (homeModules_aqares.getInterface() != null) {
//
//                                if (!homeModules_aqares.getInterface().equals("null")) {
//                                    view_.setText(homeModules_aqares.getInterface() + "");
//
//                                } else {
//                                    view_.setText("-");
//
//                                }
//                            } else {
//                                view_.setText("-");
//
//                            }
//                            if (homeModules_aqares.getMeterPrice() != null) {
//
//                                if (!homeModules_aqares.getMeterPrice().equals("null")) {
//                                    metter_price.setText(homeModules_aqares.getMeterPrice() + "");
//
//                                } else {
//                                    metter_price.setText("-");
//
//                                }
//                            } else {
//                                metter_price.setText("-");
//
//                            }
//                            try {
//                                if (homeModules_aqares.getUser() != null) {
//                                    rate_user.setRating(Float.valueOf(homeModules_aqares.getUser().getRate()));
//
//                                }
//                            } catch (Exception e) {
//
//                            }
//
//                            if (homeModules_aqares.getRate() != null) {
//
//
//                                if (homeModules_aqares.getRate().equals("0")) {
//                                    rate_aqarez.setVisibility(View.VISIBLE);
//
//                                } else {
//                                    rate_aqarez.setVisibility(View.GONE);
//
//                                }
//
//
//                            } else {
//                                rate_aqarez.setVisibility(View.VISIBLE);
//
//                            }
//                            if (homeModules_aqares.getRate() != null) {
//
//
//                                if (homeModules_aqares.getRate().equals("0")) {
//                                    rate_aqarez.setVisibility(View.VISIBLE);
//
//                                } else {
//                                    rate_aqarez.setVisibility(View.GONE);
//
//                                }
//
//
//                            } else {
//                                rate_aqarez.setVisibility(View.VISIBLE);
//
//                            }
//
//                            try {
//                                list_coments.setAdapter(new RecyclerView_coments(DetailsActivity_aqarz.this, homeModules_aqares.getRates()));
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                simpleRatingBar.setRating(Float.valueOf(homeModules_aqares.getRate()));
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }

                            Image_user.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    Intent intent = new Intent(DetaislAqarzActivity.this, OtherProfileActivity.class);
                                    intent.putExtra("id", homeModules_aqares.getUserId() + "");
                                    startActivity(intent);
//                                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                                }
                            });
                            name_owner.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    Intent intent = new Intent(DetaislAqarzActivity.this, OtherProfileActivity.class);
                                    intent.putExtra("id", homeModules_aqares.getUserId() + "");
                                    startActivity(intent);
//                                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                                }
                            });
                            chat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!Settings.checkLogin()) {


                                        startActivity(new Intent(DetaislAqarzActivity.this, LoginActivity.class));

                                    } else {
                                        Intent intent = new Intent(DetaislAqarzActivity.this, ChatRoomActivity.class);
                                        intent.putExtra("user_id", homeModules_aqares.getUserId() + "");
                                        intent.putExtra("parent_id", "-1");
                                        intent.putExtra("nameUser", homeModules_aqares.getOwnerName() + "");
                                        intent.putExtra("imageUser", "");
                                        startActivity(intent);
                                    }
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
                                        String number = "0" + homeModules_aqares.getOwnerMobile();

                                        Uri uri = Uri.parse("smsto:" + number);
                                        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                        i.setPackage("com.whatsapp");
                                        startActivity(Intent.createChooser(i, ""));
                                    } catch (Exception e) {
                                        Toast.makeText(DetaislAqarzActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
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

//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
////                            System.out.println("items_ViewPageritems_ViewPager" + homeModules_aqares.getEstate_file().size());


                            items_ViewPager.clear();

                            if (homeModules_aqares.getVideo() != null) {
                                if (!homeModules_aqares.getVideo().equals("null")) {
                                    items_ViewPager.add(new imagemodules(homeModules_aqares.getVideo() + "", "video"));
                                }
                            }
                            for (int i = 0; i < homeModules_aqares.getEstate_file().size(); i++) {
                                items_ViewPager.add(new imagemodules(homeModules_aqares.getEstate_file().get(i).getFile() + "", "image"));
                            }

                            home_viewPager.setAdapter(new home_viewPager_Adapter(DetaislAqarzActivity.this, items_ViewPager));
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
                                            handler.postDelayed(this, 3000);
                                        }
                                    };
                                    handler.postDelayed(runnable, 3000);

                                }

                            } catch (Exception e) {

                            }


//                            RecyclerView_All_Comfort_in_details recyclerView_all_comfort_in_fragment = new RecyclerView_All_Comfort_in_details(DetailsActivity_aqarz.this, homeModules_aqares.getComforts());//
//                            comfort_rec.setAdapter(recyclerView_all_comfort_in_fragment);


                            init_volley();
                            VolleyService mVolleyService = new VolleyService(mResultCallback, DetaislAqarzActivity.this);
                            mVolleyService.getDataVolley("smilier", WebService.smilier + "/" + id_or_aq + "/estate");//&request_type=pay


                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetaislAqarzActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(DetaislAqarzActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetaislAqarzActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(DetaislAqarzActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetaislAqarzActivity.this, false);

                WebService.Make_Toast_color(DetaislAqarzActivity.this, error, "error");


            }
        };


    }

}