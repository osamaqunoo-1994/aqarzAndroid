package sa.aqarz.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class SelectNewLocationActivity extends AppCompatActivity {
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;
    Geocoder geocoder;
    List<Address> addresses;
    IResult mResultCallback;

    String lat = "";
    String lang = "";

    EditText text_search;
    PlaceAutocompleteFragment placeAutoComplete;
    private ItemClickListener mItemClickListener;

    Button select;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        mMapView = findViewById(R.id.mapViewxx);
        text_search = findViewById(R.id.text_search);
        select = findViewById(R.id.select);
        back = findViewById(R.id.back);


        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(SelectNewLocationActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            String lat = getIntent().getStringExtra("lat");
            String lng = getIntent().getStringExtra("lan");

            System.out.println("latlat" + lat + "lnglng" + lng);

            LatLng sydney = new LatLng(Double.valueOf(lat), Double.valueOf(lng));

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));


        } catch (Exception e) {

        }


        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.d("Maps", "Place selected: " + place.getName());
//                LatLng sydney = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
//
//                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//                // Zoom in, animating the camera.
//                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);


            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lat.equals("")) {
//                    if (mItemClickListener != null) {
////                        mItemClickListener.onItemClick(lat, lang, text_search.getText().toString());
////                    }
                    Intent resultIntent = new Intent();
// TODO Add extras or a data URI to this intent as appropriate.
                    resultIntent.putExtra("lat", lat);
                    resultIntent.putExtra("lang", lang);
                    resultIntent.putExtra("address", text_search.getText().toString());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();


                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.getUiSettings().setRotateGesturesEnabled(true);

                googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {

                        googleMap.clear();
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(latLng)).setTag("mylocation");

                        lat = cameraPosition.target.latitude + "";
                        lang = cameraPosition.target.longitude + "";


//                        geocoder = new Geocoder(SelectNewLocationActivity.this, Locale.getDefault());

                        try {
                            //-----------------------------------------------------------------------------------------
                            init_volley();
                            VolleyService mVolleyService = new VolleyService(mResultCallback, SelectNewLocationActivity.this);

                            mVolleyService.getDataVolley("comfort", WebService.check_location_data+"?lat="+lat+"&lan="+lang);

                            WebService.loading(SelectNewLocationActivity.this, false);

//                            WebService.loading(SelectNewLocationActivity.this, true);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
//                        googleMap.clear();
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(latLng)).setTag("mylocation");
//
//                        lat = latLng.latitude + "";
//                        lang = latLng.longitude + "";
//
//
//                        geocoder = new Geocoder(SelectLocationActivity.this, Locale.getDefault());
//
//                        try {
//
//                            WebService.loading(SelectLocationActivity.this, true);
//
//                            final Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                    try {
//                                        WebService.loading(SelectLocationActivity.this, false);
//
//
//                                        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
////                                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
////                                        String city = addresses.get(0).getLocality();
//                                        String state = addresses.get(0).getAdminArea();
//                                        String country = addresses.get(0).getCountryName();
////                                        String postalCode = addresses.get(0).getPostalCode();
////                                        String knownName = addresses.get(0).getFeatureName(); // Onl
//
//
//                                        text_search.setText(country + " - " + state);
//                                    } catch (Exception e) {
//
//                                    }
//
//                                }
//                            }, 500); // After 1 seconds
//
//
//                            //    state country
//
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }


                    }
                });

                LatLng mylocation = getLocation_sau();
                if (mylocation != null) {
//                            googleMap.addMarker(new MarkerOptions()
//                                    .position(mylocation)).setTag("mylocation");
//
//                            ;
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(mylocation).zoom(4).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


//                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 40));
//                            // Zoom in, animating the camera.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
                }


                if (ActivityCompat.checkSelfPermission(SelectNewLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SelectNewLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    return;
                }
                googleMap.setMyLocationEnabled(true);
                View locationButton = ((View) mMapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
// position on right bottom
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                rlp.setMargins(0, 180, 180, 0);


            }
        });

    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(String lat_, String lang_, String address);
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public LatLng getLocation_sau() {
        LatLng my_location = new LatLng(24.768516, 46.691505);
        return my_location;

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {


                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(SelectNewLocationActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                    }

                } catch (Exception e) {

                }
            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.toString());

                try {

                    NetworkResponse response = error.networkResponse;
                    String json = new String(response.data);


                } catch (Exception e) {

                }


                WebService.loading(SelectNewLocationActivity.this, false);
                WebService.Make_Toast_color(SelectNewLocationActivity.this, error.getMessage(), "error");


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}