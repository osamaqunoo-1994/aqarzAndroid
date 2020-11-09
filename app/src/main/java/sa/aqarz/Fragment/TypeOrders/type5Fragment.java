package sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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

import sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import sa.aqarz.Adapter.RecyclerView_All_number_room;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import sa.aqarz.Modules.OprationModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class type5Fragment extends Fragment {
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


    EditText Les_space, Maximum_space, Les_price, Maximum_price, Communication_Officer, Communication_number, description;
    RadioButton radio_show;
    Button btn_send;


    String lat = "";
    String lng = "";
    String room_number = "1";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_5, container, false);
        Id_eastate = getArguments().getString("Id_eastate");

        init(v);
        return v;
    }


    public void init(View v) {
        Les_space = v.findViewById(R.id.Les_space);
        Maximum_space = v.findViewById(R.id.Maximum_space);
        Les_price = v.findViewById(R.id.Les_price);
        Communication_Officer = v.findViewById(R.id.Communication_Officer);
        Communication_number = v.findViewById(R.id.Communication_number);
        description = v.findViewById(R.id.description);
        radio_show = v.findViewById(R.id.radio_show);
        btn_send = v.findViewById(R.id.btn_send);
        opration_RecyclerView = v.findViewById(R.id.opration_RecyclerView);
        Soldier = v.findViewById(R.id.Soldier);
        Special = v.findViewById(R.id.Special);
        governmental = v.findViewById(R.id.governmental);
        Maximum_price = v.findViewById(R.id.Maximum_price);


        number_roomRecyclerView = v.findViewById(R.id.number_roomRecyclerView);

        LinearLayoutManager layoutManages
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        number_roomRecyclerView.setLayoutManager(layoutManages);

        lost_romm.add("1");
        lost_romm.add("2");
        lost_romm.add("3");
        lost_romm.add("4");
        lost_romm.add("5");
        lost_romm.add("6");
        lost_romm.add("7");
        lost_romm.add("8");

        RecyclerView_All_number_room recyclerView_all_number_room = new RecyclerView_All_number_room(getContext(), lost_romm);
        recyclerView_all_number_room.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                room_number = lost_romm.get(position);
            }
        });

        number_roomRecyclerView.setAdapter(recyclerView_all_number_room);


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
                    WebService.Make_Toast(getActivity(), getResources().getString(R.string.AllFiledsREquered));

                } else {

                    WebService.loading(getActivity(), true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());


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
    }


    public static type5Fragment newInstance(String text) {

        type5Fragment f = new type5Fragment();
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

//                        Hawk.put("user", data);

                        String message = response.getString("message");

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