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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rtchagas.pingplacepicker.PingPlacePicker;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import aqarz.revival.sa.aqarz.Activity.Auth.EditProfileActivity;
import aqarz.revival.sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
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


    TextView aw1;
    TextView aw2;
    TextView aw3;
    TextView aw4;
    TextView aw5;

    RecyclerView alldate;


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
        mMapView = (MapView) findViewById(R.id.mapViewxx);

        aw1 = findViewById(R.id.aw1);
        aw2 = findViewById(R.id.aw2);
        aw3 = findViewById(R.id.aw3);
        aw4 = findViewById(R.id.aw4);
        aw5 = findViewById(R.id.aw5);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(DetailsAqarzManActivity.this, LinearLayoutManager.VERTICAL, false);
        alldate.setLayoutManager(layoutManager1);
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


            }
        });
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

            if (!Settings.GetUser().getEmail().toString().equals("null")) {
                email.setText(Settings.GetUser().getEmail());

            } else {
                email.setText("---------");

            }

            if (!Settings.GetUser().getLogo().toString().equals("null")) {
                Picasso.get().load(Settings.GetUser().getLogo()).into(profile);

            }
            mobile.setText(Settings.GetUser().getMobile());
        } catch (Exception e) {

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


                        AlertDialog alertDialog;


                        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        final View popupView = layoutInflater.inflate(R.layout.upgrade_message, null);


                        final AlertDialog.Builder builder = new AlertDialog.Builder(DetailsAqarzManActivity.this);

//            alertDialog_country =
                        builder.setView(popupView);


                        alertDialog = builder.show();

                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetailsAqarzManActivity.this, message, "error");
                    }


                } catch (Exception e) {

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