package aqarz.revival.sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;


public class type3Fragment extends Fragment {

    String opration_select = "";

    List<TypeModules> type_list = new ArrayList<>();

    RecyclerView opration_RecyclerView;

    List<OprationModules> oprationModules_list = new ArrayList<>();

    String Id_eastate = "";


    EditText description;
    CardView map_select;
    PlacesClient placesClient;

    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;
    Button btn_send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_3, container, false);
        Id_eastate = getArguments().getString("Id_eastate");

        init(v);
        return v;
    }


    public void init(View v) {
        opration_RecyclerView = v.findViewById(R.id.opration_RecyclerView);
        description = v.findViewById(R.id.description);
        map_select = v.findViewById(R.id.map_select);


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


    }


    public static type3Fragment newInstance(String text) {

        type3Fragment f = new type3Fragment();
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

            }
        }
    }

}