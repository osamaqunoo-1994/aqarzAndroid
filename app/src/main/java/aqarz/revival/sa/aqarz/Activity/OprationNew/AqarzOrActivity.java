package aqarz.revival.sa.aqarz.Activity.OprationNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_number_room;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class AqarzOrActivity extends AppCompatActivity {
    List<TypeModules> type_list = new ArrayList<>();
    String opration_select = "";
    IResult mResultCallback;

    RecyclerView opration_2__RecyclerView;
    RecyclerView number_roomRecyclerView;
    CardView map_select;
    PlacesClient placesClient;

    RecyclerView opration_RecyclerView;

    List<OprationModules> oprationModules_list = new ArrayList<>();

    String tenant_job_type = "governmental";
    TextView governmental;
    TextView Special;
    TextView Soldier;
    List<String> lost_romm = new ArrayList<>();

    String Id_eastate = "";
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;

    ImageView back;
    EditText Les_space, Maximum_space, Les_price, Maximum_price, Communication_Officer, Communication_number, description;
    RadioButton radio_show;
    Button btn_send;


    String lat = "";
    String lng = "";
    String room_number = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aqarz_or);
//        Id_eastate = getArguments().getString("Id_eastate");

        mMapView = (MapView) findViewById(R.id.mapViewxx);

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        init();
    }

    public void init() {
        Les_space = findViewById(R.id.Les_space);
        Maximum_space = findViewById(R.id.Maximum_space);
        Les_price = findViewById(R.id.Les_price);
        Communication_Officer = findViewById(R.id.Communication_Officer);
        Communication_number = findViewById(R.id.Communication_number);
        description = findViewById(R.id.description);
        radio_show = findViewById(R.id.radio_show);
        btn_send = findViewById(R.id.btn_send);
        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);
        Soldier = findViewById(R.id.Soldier);
        Special = findViewById(R.id.Special);
        governmental = findViewById(R.id.governmental);
        Maximum_price = findViewById(R.id.Maximum_price);
        back = findViewById(R.id.back);


        number_roomRecyclerView = findViewById(R.id.number_roomRecyclerView);

        LinearLayoutManager layoutManages
                = new LinearLayoutManager(AqarzOrActivity.this, LinearLayoutManager.HORIZONTAL, false);
        number_roomRecyclerView.setLayoutManager(layoutManages);

        lost_romm.add("1");
        lost_romm.add("2");
        lost_romm.add("3");
        lost_romm.add("4");
        lost_romm.add("5");
        lost_romm.add("6");
        lost_romm.add("7");
        lost_romm.add("8");

        RecyclerView_All_number_room recyclerView_all_number_room = new RecyclerView_All_number_room(AqarzOrActivity.this, lost_romm);
        recyclerView_all_number_room.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                room_number = lost_romm.get(position);
            }
        });

        number_roomRecyclerView.setAdapter(recyclerView_all_number_room);


        try {
            MapsInitializer.initialize(AqarzOrActivity.this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//                googleMap.getUiSettings().setRotateGesturesEnabled(true);


                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ContextCompat.checkSelfPermission(AqarzOrActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                                // No explanation needed, we can request the permission.

                                ActivityCompat.requestPermissions(AqarzOrActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        11);

                            } else {

                                PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
                                builder.setAndroidApiKey("AIzaSyDWtJ0cgqHXouF5I5YdPjLzztWQzXM4zQc")
                                        .setMapsApiKey("AIzaSyDWtJ0cgqHXouF5I5YdPjLzztWQzXM4zQc");

                                // If you want to set a initial location rather then the current device location.
                                // NOTE: enable_nearby_search MUST be true.
                                // builder.setLatLng(new LatLng(37.4219999, -122.0862462))

                                try {
                                    Intent placeIntent = builder.build(AqarzOrActivity.this);
                                    startActivityForResult(placeIntent, 11);
                                } catch (Exception ex) {
                                    // Google Play services is not available...
                                }
                            }
                        } else {


                        }
                    }
                });

            }
        });
        Places.initialize(AqarzOrActivity.this, "AIzaSyA6E2L_Feqp6HMD85eQ1RP06WnykHJj7Mc");
        placesClient = Places.createClient(AqarzOrActivity.this);

        //---------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------

        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        System.out.println("type_list" + type_list.size());


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(AqarzOrActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);
        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(AqarzOrActivity.this, type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {


                opration_select = type_list.get(position).getId().toString() + "";


            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_type_in_fragment);

//---------------------------------------------------------------------------------------

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Les_space.getText().toString().equals("") |
                        Maximum_space.getText().toString().equals("") |
                        Les_price.getText().toString().equals("") |
                        Maximum_price.getText().toString().equals("") |
                        Communication_Officer.getText().toString().equals("") |
                        Communication_number.getText().toString().equals("") |
                        description.getText().toString().equals("")
                ) {
                    WebService.Make_Toast(AqarzOrActivity.this, getResources().getString(R.string.AllFiledsREquered));

                } else {

                    WebService.loading(AqarzOrActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, AqarzOrActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("operation_type_id", Id_eastate);//form operation list api in setting
                        sendObj.put("request_type", "1");//form estate type list api in setting
                        sendObj.put("estate_type_id", opration_select);

                        sendObj.put("area_from", Les_space.getText().toString());
                        sendObj.put("area_to", Maximum_space.getText().toString());
                        sendObj.put("lat", lat);
                        sendObj.put("lan", lng);
                        sendObj.put("price_from", Les_price.getText().toString());
                        sendObj.put("price_to", Maximum_price.getText().toString());
                        sendObj.put("room_numbers", room_number);
                        sendObj.put("owner_name", Communication_Officer.getText().toString());
                        sendObj.put("owner_mobile", Communication_number.getText().toString());
                        sendObj.put("display_owner_mobile", "1");
                        sendObj.put("note", description.getText().toString());


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("estate_Request", WebService.estate_Request, sendObj);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


            }
        });
//---------------------------------------------------------------------------------
        governmental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                governmental.setBackground(getResources().getDrawable(R.drawable.button_login));

                governmental.setTextColor(getResources().getColor(R.color.white));


                Soldier.setBackground(null);

                Soldier.setTextColor(getResources().getColor(R.color.textColor));

                Special.setBackground(null);

                Special.setTextColor(getResources().getColor(R.color.textColor));
                tenant_job_type = "governmental";
            }
        });
        Soldier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soldier.setBackground(getResources().getDrawable(R.drawable.button_login));

                Soldier.setTextColor(getResources().getColor(R.color.white));


                governmental.setBackground(null);

                governmental.setTextColor(getResources().getColor(R.color.textColor));
                Special.setBackground(null);

                Special.setTextColor(getResources().getColor(R.color.textColor));
                tenant_job_type = "soldier";

            }
        });
        init_volley();
        Special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Special.setBackground(getResources().getDrawable(R.drawable.button_login));

                Special.setTextColor(getResources().getColor(R.color.white));


                governmental.setBackground(null);

                governmental.setTextColor(getResources().getColor(R.color.textColor));
                Soldier.setBackground(null);

                Soldier.setTextColor(getResources().getColor(R.color.textColor));
                tenant_job_type = "special";

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 11)) {
            Place place = PingPlacePicker.getPlace(data);
            if (place != null) {
                Toast.makeText(AqarzOrActivity.this, "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();


                LatLng sydney = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                googleMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title("Marker"));


                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
                // Zoom in, animating the camera.
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);


                lat = place.getLatLng().latitude + "";
                lng = place.getLatLng().longitude + "";


            }
        }
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(AqarzOrActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//                        Hawk.put("user", data);

                        String message = response.getString("message");

                        WebService.Make_Toast_color(AqarzOrActivity.this, message, "success");
                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(AqarzOrActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);


                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(AqarzOrActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(AqarzOrActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }
}