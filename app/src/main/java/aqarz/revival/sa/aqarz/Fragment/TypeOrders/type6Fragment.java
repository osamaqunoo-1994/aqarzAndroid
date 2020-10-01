package aqarz.revival.sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.loopj.android.http.RequestParams;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_number_room;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;


public class type6Fragment extends Fragment {
    List<TypeModules> type_list = new ArrayList<>();
    String opration_select = "";
    TextView For_sale, rent, investment;

    RecyclerView opration_2__RecyclerView;
    RecyclerView opration_RecyclerView;

    List<OprationModules> oprationModules_list = new ArrayList<>();

    CardView map_select;
    PlacesClient placesClient;

    List<String> lost_romm = new ArrayList<>();
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;
    String Id_eastate = "";

    IResult mResultCallback;

    EditText name, email, phone, description;
    Button btn_send;


    String lat = "";
    String lng = "";
    String Type_work_select = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_6, container, false);
        Id_eastate = getArguments().getString("Id_eastate");

        init(v);
        return v;
    }


    public void init(View v) {
        name = v.findViewById(R.id.name);
        email = v.findViewById(R.id.email);
        phone = v.findViewById(R.id.phone);
        description = v.findViewById(R.id.description);
        btn_send = v.findViewById(R.id.btn_send);
        opration_RecyclerView = v.findViewById(R.id.opration_RecyclerView);
        For_sale = v.findViewById(R.id.For_sale);
        rent = v.findViewById(R.id.rent);
        investment = v.findViewById(R.id.investment);
        mMapView = (MapView) v.findViewById(R.id.mapViewxx);

        mMapView.onCreate(getArguments());

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
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
                            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                                // No explanation needed, we can request the permission.

                                ActivityCompat.requestPermissions(getActivity(),
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
                                    Intent placeIntent = builder.build(getActivity());
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
        Places.initialize(getContext(), "AIzaSyA6E2L_Feqp6HMD85eQ1RP06WnykHJj7Mc");
        placesClient = Places.createClient(getContext());

        //---------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------

        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        System.out.println("type_list" + type_list.size());


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);
        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {


                opration_select = type_list.get(position).getId().toString() + "";


            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_type_in_fragment);

//-------------------------------------------------------------------------------------------------
        For_sale.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                For_sale.setBackground(getResources().getDrawable(R.drawable.button_login));

                For_sale.setTextColor(getResources().getColor(R.color.white));


                rent.setBackground(getResources().getDrawable(R.drawable.search_background));

                rent.setTextColor(getResources().getColor(R.color.textColor));


                investment.setBackground(getResources().getDrawable(R.drawable.search_background));

                investment.setTextColor(getResources().getColor(R.color.textColor));


                rent.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                investment.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                For_sale.getCompoundDrawables()[0].setTint(Color.WHITE);

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


                For_sale.setBackground(getResources().getDrawable(R.drawable.search_background));

                For_sale.setTextColor(getResources().getColor(R.color.textColor));


                investment.setBackground(getResources().getDrawable(R.drawable.search_background));

                investment.setTextColor(getResources().getColor(R.color.textColor));


                For_sale.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                investment.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                rent.getCompoundDrawables()[0].setTint(Color.WHITE);

                Type_work_select = "2";

            }
        });
        investment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                investment.setBackground(getResources().getDrawable(R.drawable.button_login));

                investment.setTextColor(getResources().getColor(R.color.white));


                For_sale.setBackground(null);

                For_sale.setTextColor(getResources().getColor(R.color.textColor));


                rent.setBackground(null);

                rent.setTextColor(getResources().getColor(R.color.textColor));


                For_sale.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                rent.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                investment.getCompoundDrawables()[0].setTint(Color.WHITE);

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
                    WebService.Make_Toast(getActivity(), getResources().getString(R.string.AllFiledsREquered));

                } else {
                    WebService.loading(getActivity(), true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("operation_type_id", Id_eastate);//form operation list api in setting
//                        sendObj.put("estate_type_id", opration_select);//form estate type list api in setting
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


    public static type6Fragment newInstance(String text) {

        type6Fragment f = new type6Fragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == 11)) {
            Place place = PingPlacePicker.getPlace(data);
            if (place != null) {
                Toast.makeText(getContext(), "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();


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
                WebService.loading(getActivity(), false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
                        String message = response.getString("message");

//                        Hawk.put("user", data);

                        WebService.Make_Toast_color(getActivity(), message, "success");

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(getActivity(), message, "error");
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


                    WebService.Make_Toast_color(getActivity(), message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(getActivity(), false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}