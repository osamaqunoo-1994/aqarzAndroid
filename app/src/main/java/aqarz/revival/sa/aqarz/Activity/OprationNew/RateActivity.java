package aqarz.revival.sa.aqarz.Activity.OprationNew;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class RateActivity extends AppCompatActivity {
    List<TypeModules> type_list = new ArrayList<>();
    String opration_select = "";
    TextView For_sale, rent, investment;

    RecyclerView opration;
//    RecyclerView opration_RecyclerView;

    List<OprationModules> oprationModules_list = new ArrayList<>();

    CardView map_select;
    PlacesClient placesClient;

    List<String> lost_romm = new ArrayList<>();
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;


    IResult mResultCallback;

    EditText name, email, phone, description;
    Button btn_send;


    String lat = "";
    String lng = "";
    String Type_work_select = "1";

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);


        mMapView = (MapView) findViewById(R.id.mapViewxx);

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getApplicationContext());
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
                            if (ContextCompat.checkSelfPermission(RateActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                                // No explanation needed, we can request the permission.

                                ActivityCompat.requestPermissions(RateActivity.this,
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
                                    Intent placeIntent = builder.build(RateActivity.this);
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
        init();

    }

    public void init() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        description = findViewById(R.id.description);
        btn_send = findViewById(R.id.btn_send);
//        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);
        For_sale = findViewById(R.id.For_sale);
        rent = findViewById(R.id.rent);
        back = findViewById(R.id.back);
        investment = findViewById(R.id.investment);
        opration = findViewById(R.id.opration);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Places.initialize(RateActivity.this, "AIzaSyA6E2L_Feqp6HMD85eQ1RP06WnykHJj7Mc");
        placesClient = Places.createClient(RateActivity.this);

        //---------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------

//        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
//
//        System.out.println("type_list" + type_list.size());
//
//
//        LinearLayoutManager layoutManager1
//                = new LinearLayoutManager(RateActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        opration_RecyclerView.setLayoutManager(layoutManager1);
//        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(RateActivity.this, type_list);
//        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
//
//                opration_select = type_list.get(position).getId().toString() + "";
//
//
//            }
//        });
//        opration_RecyclerView.setAdapter(recyclerView_all_type_in_fragment);
///------------------------------------------------------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(RateActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(RateActivity.this, type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                opration_select = type_list.get(position).getId().toString() + "";
            }
        });
        opration.setAdapter(recyclerView_all_opration_bottom_sheet);

//-------------------------------------------------------------------------------------------------
        For_sale.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                For_sale.setBackground(getResources().getDrawable(R.drawable.button_login));

                For_sale.setTextColor(getResources().getColor(R.color.white));


                rent.setBackground(getResources().getDrawable(R.drawable.mash));

                rent.setTextColor(getResources().getColor(R.color.color_filter));


                investment.setBackground(getResources().getDrawable(R.drawable.mash));

                investment.setTextColor(getResources().getColor(R.color.color_filter));
//
//                rent.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                investment.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                For_sale.getCompoundDrawables()[0].setTint(Color.WHITE);

                Type_work_select = "1";
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                rent.setBackground(getResources().getDrawable(R.drawable.button_login));

                rent.setTextColor(getResources().getColor(R.color.white));


                For_sale.setBackground(getResources().getDrawable(R.drawable.mash));

                For_sale.setTextColor(getResources().getColor(R.color.color_filter));


                investment.setBackground(getResources().getDrawable(R.drawable.mash));

                investment.setTextColor(getResources().getColor(R.color.color_filter));
//
//
//                For_sale.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                investment.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                rent.getCompoundDrawables()[0].setTint(Color.WHITE);

                Type_work_select = "2";

            }
        });
        investment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                investment.setBackground(getResources().getDrawable(R.drawable.button_login));

                investment.setTextColor(getResources().getColor(R.color.white));


                For_sale.setBackground(getResources().getDrawable(R.drawable.circle));

                For_sale.setTextColor(getResources().getColor(R.color.textColor));


                rent.setBackground(getResources().getDrawable(R.drawable.circle));

                rent.setTextColor(getResources().getColor(R.color.textColor));

//


//                For_sale.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                rent.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                investment.getCompoundDrawables()[0].setTint(Color.WHITE);

                Type_work_select = "3";

            }
        });

        init_volley();
//--------------------------------------------------------------------------------------
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name.getText().toString().equals("") |
                        email.getText().toString().equals("") |
                        phone.getText().toString().equals("") |
                        lat.toString().equals("") |


                        description.getText().toString().equals("")
                ) {
                    WebService.Make_Toast(RateActivity.this, getResources().getString(R.string.AllFiledsREquered));

                } else {
                    WebService.loading(RateActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, RateActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("operation_type_id", "4");//form operation list api in setting
                        sendObj.put("estate_type_id", opration_select);//form estate type list api in setting
                        sendObj.put("name", name.getText().toString());//
                        sendObj.put("email", email.getText().toString());//
                        sendObj.put("mobile", phone.getText().toString());//
                        sendObj.put("note", description.getText().toString());//
                        sendObj.put("lat", lat);//
                        sendObj.put("lan", lng);//
                        sendObj.put("rate_request_types", Type_work_select);//


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("rateRequest", WebService.rate_Request, sendObj);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 11)) {
            Place place = PingPlacePicker.getPlace(data);
            if (place != null) {
                Toast.makeText(RateActivity.this, "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();


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
                WebService.loading(RateActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
                        String message = response.getString("message");

//                        Hawk.put("user", data);

//                        WebService.Make_Toast_color(RateActivity.this, message, "success");

                        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(RateActivity.this);
                        View parentView = getLayoutInflater().inflate(R.layout.success_message,null);
                        ImageView close=parentView.findViewById(R.id.close);
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });
                        bottomSheerDialog.setContentView(parentView);
//                        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(parentView.getParent());
//                        bottomSheetBehavior.setPeekHeight(int);
                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,getResources().getDisplayMetrics());
                        bottomSheerDialog.show();

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(RateActivity.this, message, "error");
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


                    WebService.Make_Toast_color(RateActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(RateActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }
}