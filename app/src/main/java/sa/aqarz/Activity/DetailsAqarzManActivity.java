package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rtchagas.pingplacepicker.PingPlacePicker;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.Auth.EditProfileActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Adapter.RecyclerView_HomeList;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_favorit;
import sa.aqarz.Adapter.RecyclerView_MyState;
import sa.aqarz.Adapter.RecyclerView_member;
import sa.aqarz.Adapter.RecyclerView_member_profile;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requst;
import sa.aqarz.Modules.FavoritModules;
import sa.aqarz.Modules.HomeModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsAqarzManActivity extends AppCompatActivity {
    ImageView back;
    ImageView edit;
    TextView title;
    TextView address;
    TextView mobile;
    TextView site;
    TextView location;
    TextView upgrade;
    CircleImageView profile;
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    //    MapView mMapView;
    IResult mResultCallback;

    List<HomeModules_aqares> homeModules = new ArrayList<>();
    List<FavoritModules> favoritModules_l = new ArrayList<>();
    List<demandsModules> homeModules1 = new ArrayList<>();

    TextView aw1;
    TextView aw2;
    TextView aw3;
    TextView aw4;
    TextView aw5;

    LinearLayout select_offer;
    LinearLayout select_clints;
    List<demandsModules> demandsModules_list = new ArrayList<>();

    String service_types_te = "";

    RecyclerView alldate;
    RecyclerView member_list;
    LinearLayout layout_ffavorit;


    LinearLayout my_order_layout;
    LinearLayout Shopping_request_layout;
    LinearLayout Real_Estate_order_layout;
    LinearLayout share_linl;
    LinearLayout open_map;

    TextView my_order_text;
    TextView Shopping_request_text;
    TextView Real_Estate_order_text;

FloatingActionButton add_clint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_aqarz_man);


        add_clint = findViewById(R.id.add_clint);
        Real_Estate_order_layout = findViewById(R.id.Real_Estate_order_layout);
        Shopping_request_layout = findViewById(R.id.Shopping_request_layout);
        my_order_layout = findViewById(R.id.my_order_layout);


        my_order_text = findViewById(R.id.my_order_text);
        Shopping_request_text = findViewById(R.id.Shopping_request_text);
        Real_Estate_order_text = findViewById(R.id.Real_Estate_order_text);
        select_offer = findViewById(R.id.select_offer);
        select_clints = findViewById(R.id.select_clints);
        open_map = findViewById(R.id.open_map);
        share_linl = findViewById(R.id.share_linl);


        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
        title = findViewById(R.id.title);
        address = findViewById(R.id.address);
        mobile = findViewById(R.id.mobile);
        site = findViewById(R.id.site);
//        email = findViewById(R.id.email);
        profile = findViewById(R.id.profile);
        upgrade = findViewById(R.id.upgrade);
        alldate = findViewById(R.id.alldate);
        member_list = findViewById(R.id.member_list);
        layout_ffavorit = findViewById(R.id.layout_ffavorit);
        location = findViewById(R.id.location);
//        mMapView = (MapView) findViewById(R.id.mapViewxx);

//

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (back != null) {
                back.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        aw1 = findViewById(R.id.aw1);
        aw2 = findViewById(R.id.aw2);
        aw3 = findViewById(R.id.aw3);
        aw4 = findViewById(R.id.aw4);
        aw5 = findViewById(R.id.aw5);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(DetailsAqarzManActivity.this, LinearLayoutManager.VERTICAL, false);
        alldate.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManagerf
                = new LinearLayoutManager(DetailsAqarzManActivity.this, LinearLayoutManager.HORIZONTAL, false);
        member_list.setLayoutManager(layoutManagerf);


//        mMapView.onCreate(savedInstanceState);
//
//        mMapView.onResume(); // needed to get the map to display immediately
//
//        try {
//            MapsInitializer.initialize(DetailsAqarzManActivity.this.getApplicationContext());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mMapView.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap mMap) {
//                googleMap = mMap;
//                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
////                googleMap.getUiSettings().setRotateGesturesEnabled(true);
//
//
//                try {
//
//                    if (Settings.GetUser().getLat() != null) {
//
//                        System.out.println("______" + Settings.GetUser().getLat());
//                        LatLng sydney = new LatLng(Double.valueOf(Settings.GetUser().getLat()), Double.valueOf(Settings.GetUser().getLan()));
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(sydney)
//                                .title("Marker"));
//
//                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//                        // Zoom in, animating the camera.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
//
//                    }
//
//                } catch (Exception e) {
//
//                }
//
//            }
//        });
//

        aw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                aw1.setBackground(getResources().getDrawable(R.drawable.button_login));
//                aw2.setBackground(null);
//                aw3.setBackground(null);
//                aw4.setBackground(null);
//                aw5.setBackground(null);
//
//                aw1.setTextColor(getResources().getColor(R.color.white));
//                aw2.setTextColor(getResources().getColor(R.color.textColor2));
//                aw3.setTextColor(getResources().getColor(R.color.textColor2));
//                aw4.setTextColor(getResources().getColor(R.color.textColor2));
//                aw5.setTextColor(getResources().getColor(R.color.textColor2));

                select_clints.setVisibility(View.INVISIBLE);
                select_offer.setVisibility(View.VISIBLE);


                layout_ffavorit.setVisibility(View.GONE);


                WebService.loading(DetailsAqarzManActivity.this, true);


                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsAqarzManActivity.this);

                mVolleyService.getDataVolley("my_estate", WebService.my_estate);

            }
        });
        WebService.loading(DetailsAqarzManActivity.this, true);

        init_volley();

        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsAqarzManActivity.this);

        mVolleyService.getDataVolley("my_estate", WebService.my_estate);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetailsAqarzManActivity.this, EditProfileActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }
        });   add_clint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(DetailsAqarzManActivity.this, AddClintesActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }
        });
        aw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aw2.setBackground(getResources().getDrawable(R.drawable.button_login));
                aw1.setBackground(null);
                aw3.setBackground(null);
                aw4.setBackground(null);
                aw5.setBackground(null);

                aw2.setTextColor(getResources().getColor(R.color.white));
                aw1.setTextColor(getResources().getColor(R.color.textColor2));
                aw3.setTextColor(getResources().getColor(R.color.textColor2));
                aw4.setTextColor(getResources().getColor(R.color.textColor2));
                aw5.setTextColor(getResources().getColor(R.color.textColor2));
                layout_ffavorit.setVisibility(View.GONE);

                WebService.loading(DetailsAqarzManActivity.this, true);
                alldate.setBackgroundColor(getResources().getColor(R.color.white));

                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsAqarzManActivity.this);

                mVolleyService.getDataVolley("my_request", WebService.my_request);

            }
        });
        aw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aw3.setBackground(getResources().getDrawable(R.drawable.button_login));
                aw2.setBackground(null);
                aw1.setBackground(null);
                aw4.setBackground(null);
                aw5.setBackground(null);

                aw3.setTextColor(getResources().getColor(R.color.white));
                aw2.setTextColor(getResources().getColor(R.color.textColor2));
                aw1.setTextColor(getResources().getColor(R.color.textColor2));
                aw4.setTextColor(getResources().getColor(R.color.textColor2));
                aw5.setTextColor(getResources().getColor(R.color.textColor2));

                layout_ffavorit.setVisibility(View.GONE);

            }
        });
        aw4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                aw4.setBackground(getResources().getDrawable(R.drawable.button_login));
//                aw2.setBackground(null);
//                aw3.setBackground(null);
//                aw1.setBackground(null);
//                aw5.setBackground(null);
//
//                aw4.setTextColor(getResources().getColor(R.color.white));
//                aw2.setTextColor(getResources().getColor(R.color.textColor2));
//                aw3.setTextColor(getResources().getColor(R.color.textColor2));
//                aw1.setTextColor(getResources().getColor(R.color.textColor2));
//                aw5.setTextColor(getResources().getColor(R.color.textColor2));

                select_clints.setVisibility(View.VISIBLE);
                select_offer.setVisibility(View.INVISIBLE);


                layout_ffavorit.setVisibility(View.GONE);

            }
        });
        aw5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aw5.setBackground(getResources().getDrawable(R.drawable.button_login));
                aw2.setBackground(null);
                aw3.setBackground(null);
                aw4.setBackground(null);
                aw1.setBackground(null);

                aw5.setTextColor(getResources().getColor(R.color.white));
                aw2.setTextColor(getResources().getColor(R.color.textColor2));
                aw3.setTextColor(getResources().getColor(R.color.textColor2));
                aw4.setTextColor(getResources().getColor(R.color.textColor2));
                aw1.setTextColor(getResources().getColor(R.color.textColor2));

                alldate.setBackgroundColor(getResources().getColor(R.color.white));

                layout_ffavorit.setVisibility(View.VISIBLE);


                WebService.loading(DetailsAqarzManActivity.this, true);


                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsAqarzManActivity.this);

                mVolleyService.getDataVolley("my_favorite", WebService.my_favorite + "?type=request");

            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////////////


        my_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));


                my_order_text.setTextColor(getResources().getColor(R.color.white));
                Shopping_request_text.setTextColor(getResources().getColor(R.color.black));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.black));

                WebService.loading(DetailsAqarzManActivity.this, true);

                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsAqarzManActivity.this);

                mVolleyService.getDataVolley("my_favorite_request", WebService.my_favorite + "?type=request");


            }
        });


        Shopping_request_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_login));
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));


                my_order_text.setTextColor(getResources().getColor(R.color.black));
                Shopping_request_text.setTextColor(getResources().getColor(R.color.white));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.black));


                WebService.loading(DetailsAqarzManActivity.this, true);


                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsAqarzManActivity.this);

                mVolleyService.getDataVolley("my_favorite_offer", WebService.my_favorite + "?type=offer");

            }
        });


        Real_Estate_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));


                my_order_text.setTextColor(getResources().getColor(R.color.black));
                Shopping_request_text.setTextColor(getResources().getColor(R.color.black));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));


                WebService.loading(DetailsAqarzManActivity.this, true);


                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsAqarzManActivity.this);

                mVolleyService.getDataVolley("my_favorite_fund", WebService.my_favorite + "?type=fund");


            }
        });
        share_linl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Settings.GetUser().getLink().toString().equals("null")) {
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Settings.GetUser().getLink().toString()));
//                    startActivity(browserIntent);


                    Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
                    txtIntent.setType("text/plain");
                    txtIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Aqarez");
                    txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, Settings.GetUser().getLink().toString());
                    startActivity(Intent.createChooser(txtIntent, "Share"));


                }
            }
        });
        open_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + Settings.GetUser().getLat() + "," + Settings.GetUser().getLan() + "&daddr=" + Settings.GetUser().getLat() + "," + Settings.GetUser().getLan()));
                startActivity(intent);
            }
        });


        try {


            if (!Settings.GetUser().getName().toString().equals("null")) {
                title.setText(Settings.GetUser().getName());

            } else {
                title.setText("----------------");

            }
            if (!Settings.GetUser().getLink().toString().equals("null")) {
                site.setText(Settings.GetUser().getLink());

            } else {
                site.setText("----------------");

            }

            if (!Settings.GetUser().getEmail().toString().equals("null")) {
//                email.setText(Settings.GetUser().getEmail());

            } else {
//                email.setText("---------");

            }
            if (!Settings.GetUser().getAddress().toString().equals("null")) {
                location.setText(Settings.GetUser().getAddress());

            } else {
                location.setText("---------");

            }
            if (Settings.GetUser().getMobile() != null) {
                mobile.setText(Settings.GetUser().getMobile() + "");

            }

//            if (Settings.GetUser().getCity_name().toString().equals("null")) {
//                try {
//                    address.setText(Settings.GetUser().getAddress());
//
//                } catch (Exception e) {
//
//                }
//
//            } else {
//                email.setText("");
//
//            }


            service_types_te = "";
            for (int i = 0; i < Settings.GetUser().getService_name().size(); i++) {

                if (service_types_te.equals("")) {
                    service_types_te = Settings.GetUser().getService_name().get(i).getName() + "";

                } else {
                    service_types_te = service_types_te + "," + Settings.GetUser().getService_name().get(i).getName() + "";

                }


            }
            address.setText(service_types_te + "");


//            member_list.setAdapter(new RecyclerView_member_profile(DetailsAqarzManActivity.this, Settings.GetUser().getMember_name()));

            if (!Settings.GetUser().getLogo().toString().equals("null")) {
                Picasso.get().load(Settings.GetUser().getLogo()).into(profile);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog;


                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.upgrade_message, null);


                final AlertDialog.Builder builder = new AlertDialog.Builder(DetailsAqarzManActivity.this);

//            alertDialog_country =
                builder.setView(popupView);


                alertDialog = builder.show();

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


//                init_volley();
//                WebService.loading(DetailsAqarzManActivity.this, true);
//
//                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsAqarzManActivity.this);
//                mVolleyService.getDataVolley("upgrade", WebService.upgrade);
//

            }
        });

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(DetailsAqarzManActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");
                        if (requestType.equals("my_request")) {
                            String data = response.getString("data");
                            JSONObject jsonObjectdata = new JSONObject(data);

                            String datax = jsonObjectdata.getString("data");

                            JSONArray jsonArray = new JSONArray(datax);


                            alldate.setAdapter(null);
                            homeModules1.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                demandsModules bankModules = gson.fromJson(mJson, demandsModules.class);
                                homeModules1.add(bankModules);


                            }


                            alldate.setAdapter(new RecyclerView_orders_my_requst(DetailsAqarzManActivity.this, homeModules1));


                        } else if (requestType.equals("my_estate")) {
                            String data = response.getString("data");
//                        JSONObject jsonObjectdata = new JSONObject(data);
//
//                        String datax = jsonObjectdata.getString("data");

                            JSONArray jsonArray = new JSONArray(data);


                            homeModules.clear();
                            alldate.setAdapter(null);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                    homeModules.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }


                            alldate.setAdapter(new RecyclerView_HomeList_estat(DetailsAqarzManActivity.this, homeModules));


                        } else if (requestType.equals("my_favorite_fund")) {

                            String data = response.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
                            String first = jsonArray.get(0).toString();
                            JSONObject jsonObjectdata = new JSONObject(first);

                            String fund = jsonObjectdata.getString("fund");

                            JSONArray jsonArray_fund = new JSONArray(fund);
                            demandsModules_list.clear();
                            for (int i = 0; i < jsonArray_fund.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray_fund.getString(i));

                                    Gson gson = new Gson();

                                    demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);
                                    demandsModules_list.add(ordersModulesm);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                alldate.setAdapter(new RecyclerView_orders_demandsx(DetailsAqarzManActivity.this, demandsModules_list));

                            }


                        } else if (requestType.equals("my_favorite_offer")) {

                        } else if (requestType.equals("my_favorite_request")) {

                        } else if (requestType.equals("my_favorite")) {

                            String data = response.getString("data");
//                        JSONObject jsonObjectdata = new JSONObject(data);
//
//                        String datax = jsonObjectdata.getString("data");

                            JSONArray jsonArray = new JSONArray(data);


                            favoritModules_l.clear();
                            alldate.setAdapter(null);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    FavoritModules bankModules = gson.fromJson(mJson, FavoritModules.class);
                                    favoritModules_l.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }


                            alldate.setAdapter(new RecyclerView_HomeList_estat_favorit(DetailsAqarzManActivity.this, favoritModules_l));


                        } else {

//
//                            AlertDialog alertDialog;
//
//
//                            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                            final View popupView = layoutInflater.inflate(R.layout.upgrade_message, null);
//
//
//                            final AlertDialog.Builder builder = new AlertDialog.Builder(DetailsAqarzManActivity.this);
//
////            alertDialog_country =
//                            builder.setView(popupView);
//
//
//                            alertDialog = builder.show();
//
//                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        }
                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetailsAqarzManActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(DetailsAqarzManActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetailsAqarzManActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(DetailsAqarzManActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetailsAqarzManActivity.this, false);

                WebService.Make_Toast_color(DetailsAqarzManActivity.this, error, "error");


            }
        };


    }

    @Override
    protected void onResume() {

        if (Settings.CheckIsCompleate()) {
//            user_name.setText(Settings.GetUser().getName() + "");
            Picasso.get().load(Settings.GetUser().getLogo()).into(profile);
//            not_compleate.setVisibility(View.GONE);

        } else {
//            user_name.setText("..........");
//            not_compleate.setVisibility(View.VISIBLE);
        }

        super.onResume();
    }
}