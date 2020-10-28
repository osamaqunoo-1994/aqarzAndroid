package aqarz.revival.sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
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
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rtchagas.pingplacepicker.PingPlacePicker;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.Auth.EditProfileActivity;
import aqarz.revival.sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_HomeList;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_MyState;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_member;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_member_profile;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_orders_my_requst;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules_aqares;
import aqarz.revival.sa.aqarz.Modules.demandsModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsAqarzManActivity extends AppCompatActivity {
    ImageView back;
    ImageView edit;
    TextView title;
    TextView address;
    TextView mobile;
    TextView email;
    TextView upgrade;
    CircleImageView profile;
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;
    IResult mResultCallback;

    List<HomeModules_aqares> homeModules = new ArrayList<>();
    List<demandsModules> homeModules1 = new ArrayList<>();

    TextView aw1;
    TextView aw2;
    TextView aw3;
    TextView aw4;
    TextView aw5;

    String service_types_te = "";

    RecyclerView alldate;
    RecyclerView member_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_aqarz_man);


        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
        title = findViewById(R.id.title);
        address = findViewById(R.id.address);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        profile = findViewById(R.id.profile);
        upgrade = findViewById(R.id.upgrade);
        alldate = findViewById(R.id.alldate);
        member_list = findViewById(R.id.member_list);
        mMapView = (MapView) findViewById(R.id.mapViewxx);

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


        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(DetailsAqarzManActivity.this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//                googleMap.getUiSettings().setRotateGesturesEnabled(true);


                try {

                    if (Settings.GetUser().getLat() != null) {

                        System.out.println("______" + Settings.GetUser().getLat());
                        LatLng sydney = new LatLng(Double.valueOf(Settings.GetUser().getLat()), Double.valueOf(Settings.GetUser().getLan()));
                        googleMap.addMarker(new MarkerOptions()
                                .position(sydney)
                                .title("Marker"));

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
                        // Zoom in, animating the camera.
                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);

                    }

                } catch (Exception e) {

                }

            }
        });


        aw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aw1.setBackground(getResources().getDrawable(R.drawable.button_login));
                aw2.setBackground(null);
                aw3.setBackground(null);
                aw4.setBackground(null);
                aw5.setBackground(null);

                aw1.setTextColor(getResources().getColor(R.color.white));
                aw2.setTextColor(getResources().getColor(R.color.textColor2));
                aw3.setTextColor(getResources().getColor(R.color.textColor2));
                aw4.setTextColor(getResources().getColor(R.color.textColor2));
                aw5.setTextColor(getResources().getColor(R.color.textColor2));

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


            }
        });
        aw4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aw4.setBackground(getResources().getDrawable(R.drawable.button_login));
                aw2.setBackground(null);
                aw3.setBackground(null);
                aw1.setBackground(null);
                aw5.setBackground(null);

                aw4.setTextColor(getResources().getColor(R.color.white));
                aw2.setTextColor(getResources().getColor(R.color.textColor2));
                aw3.setTextColor(getResources().getColor(R.color.textColor2));
                aw1.setTextColor(getResources().getColor(R.color.textColor2));
                aw5.setTextColor(getResources().getColor(R.color.textColor2));


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


            }
        });


        try {


            if (!Settings.GetUser().getName().toString().equals("null")) {
                title.setText(Settings.GetUser().getName());

            } else {
                title.setText("----------------");

            }
            mobile.setText(Settings.GetUser().getMobile() + "");

            if (!Settings.GetUser().getEmail().toString().equals("null")) {
                email.setText(Settings.GetUser().getEmail());

            } else {
                email.setText("---------");

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


            member_list.setAdapter(new RecyclerView_member_profile(DetailsAqarzManActivity.this, Settings.GetUser().getMember_name()));

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