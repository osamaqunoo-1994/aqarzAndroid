package sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Filtter;
import sa.aqarz.Fragment.mapsHome.MapsFragmentNew;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class SelectLocationActivity extends AppCompatActivity {
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;
    Geocoder geocoder;
    List<Address> addresses;

    String lat = "";
    String lang = "";
    List<CityModules> cityModules_list_filtter = new ArrayList<>();

    EditText text_search;
    PlaceAutocompleteFragment placeAutoComplete;
    private ItemClickListener mItemClickListener;

    Button select;

    ImageView back;
    ImageView search_btn;
    IResult mResultCallback;
    RecyclerView allcity;
    LinearLayout result;


    LinearLayout selected;
    LinearLayout select_location;
    TextView text_t;
    ImageView closr_tecxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        mMapView = findViewById(R.id.mapViewxx);
        text_search = findViewById(R.id.text_search);
        result = findViewById(R.id.result);
        allcity = findViewById(R.id.allcity);
        select = findViewById(R.id.select);
        back = findViewById(R.id.back);
        search_btn = findViewById(R.id.search_btn);
        selected = findViewById(R.id.selected);
        text_t = findViewById(R.id.text_t);
        closr_tecxt = findViewById(R.id.closr_tecxt);
        select_location = findViewById(R.id.select_location);


        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(SelectLocationActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(SelectLocationActivity.this, LinearLayoutManager.VERTICAL, false);
        allcity.setLayoutManager(layoutManager1);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_volley();
                WebService.loading(SelectLocationActivity.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, SelectLocationActivity.this);
                mVolleyService.getDataVolley("cities_with_neb", WebService.cities_with_neb + "?name=" + text_search.getText().toString());//+ "&state_id=" + region_id_postion + "&city_id=" + city_id_postion


            }
        });

        text_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    init_volley();
                    WebService.loading(SelectLocationActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, SelectLocationActivity.this);
                    mVolleyService.getDataVolley("cities_with_neb", WebService.cities_with_neb + "?name=" + text_search.getText().toString());//+ "&state_id=" + region_id_postion + "&city_id=" + city_id_postion


                    return true;
                }
                return false;
            }
        });


//        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
//        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//
//                Log.d("Maps", "Place selected: " + place.getName());
////                LatLng sydney = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
////
////                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
////                // Zoom in, animating the camera.
////                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
////                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
////                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
//
//
//            }
//
//            @Override
//            public void onError(Status status) {
//                Log.d("Maps", "An error occurred: " + status);
//            }
//        });
//

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


                try {


                    String lat = getIntent().getStringExtra("lat");
                    String lng = getIntent().getStringExtra("lan");


                    if (lat.equals("0.0")) {

                        lat = "24.527282";
                        lng = "44.007305";

                        LatLng sydney = new LatLng(Double.valueOf(lat), Double.valueOf(lng));

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));

                    } else if (lat.equals("")) {
                        lat = "24.527282";
                        lng = "44.007305";

                        LatLng sydney = new LatLng(Double.valueOf(lat), Double.valueOf(lng));

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));

                    } else {

                        LatLng sydney = new LatLng(Double.valueOf(lat), Double.valueOf(lng));

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                select_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ActivityCompat.checkSelfPermission(SelectLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SelectLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                            }

                        } else {
                            googleMap.setMyLocationEnabled(true);

                        }

                    }
                });

                try {
                    String address = getIntent().getStringExtra("address");


                    if (address != null) {
                        text_search.setText(address + "");

                    }


                } catch (Exception e) {

                }


                if (ActivityCompat.checkSelfPermission(SelectLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SelectLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                    }

                } else {
                    googleMap.setMyLocationEnabled(true);

                }
                googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {

                        googleMap.clear();
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(latLng)).setTag("mylocation");


                        lat = cameraPosition.target.latitude + "";
                        lang = cameraPosition.target.longitude + "";


//                        geocoder = new Geocoder(SelectLocationActivity.this, Locale.getDefault());

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
//                                        addresses = geocoder.getFromLocation(cameraPosition.target.latitude, cameraPosition.target.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
////                                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
////                                        String city = addresses.get(0).getLocality();
//                                        String state = addresses.get(0).getAdminArea();
//                                        String country = addresses.get(0).getCountryName();
//
////
////                                        try {
////                                            List<Address> addresses = geocoder.getFromLocationName(
////                                                    "السعودية", 5);
////                                            PolylineOptions polylineOptions = new PolylineOptions();
////
////                                            for (int i = 0; i < addresses.size(); i++) {
////
////                                                System.out.println("**&&^%%%" + addresses.get(i).getLatitude() + "--" + addresses.get(i).getLongitude());
////                                                polylineOptions.add(new LatLng(addresses.get(i).getLatitude(), addresses.get(i).getLongitude()));
////
////
////                                            }
////
////                                            Polyline polyline = googleMap.addPolyline(polylineOptions);
////
////
////                                            if (addresses.size() > 0) {
////                                                Log.d("TAG", "ADRESSE " + addresses.get(0) + ",LAT :" + addresses.get(0).getLatitude() + ", LONG :" + addresses.get(0).getLongitude());
////                                            }
////                                        } catch (IOException e) {
////                                            e.printStackTrace();
////                                        }
//
////                                        System.out.println("countrycountry" + country);
//////                                        String postalCode = addresses.get(0).getPostalCode();
//////                                        String knownName = addresses.get(0).getFeatureName(); // Onl
////
////
//////                                        text_search.setText(country + " - " + state);
////
////
////                                        if (country.equals("السعودية")) {
////
////                                            select.setVisibility(View.VISIBLE);
////
////                                        } else {
////                                            select.setVisibility(View.INVISIBLE);
////
////                                        }
//
//
//                                    } catch (Exception e) {
//
//                                    }
//
//                                }
//                            }, 500); // After 1 seconds
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }


                    }
                });

//                LatLng mylocation = getLocation_sau();
//                if (mylocation != null) {
//
////                    CameraPosition cameraPosition = new CameraPosition.Builder().target(mylocation).zoom(4).build();
////                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//
//                }


//                View locationButton = ((View) mMapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
//                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
//// position on right bottom
//                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
//                rlp.setMargins(0, 180, 180, 0);


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
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(SelectLocationActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
//                        String next_page_url = response.getString("next_page_url");
                        JSONObject jsonObject = new JSONObject(data);

                        if (requestType.equals("cities_with_neb")) {
                            String datadata = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datadata);
                            cityModules_list_filtter.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                CityModules homeModules_aqares = gson.fromJson(mJson, CityModules.class);

                                cityModules_list_filtter.add(homeModules_aqares);
                            }

                            RecyclerView_city_side_menu recyclerView_city_bootom_sheets = new RecyclerView_city_side_menu(SelectLocationActivity.this, cityModules_list_filtter);
                            recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_side_menu.ItemClickListener() {
                                @Override
                                public void onItemClick(int i) {
//                        cityModules_list = alldata;
//                                    convert_city_to_filter();
//                                    searh = false;

//                                    text_search.setText();

//                                    MapsFragmentNew.city_id_postion = cityModules_list_filtter.get(i).getId() + "";
//                                    MapsFragmentNew.lat = cityModules_list_filtter.get(i).getLat() + "";
//                                    MapsFragmentNew.lan = cityModules_list_filtter.get(i).getLan() + "";

                                    selected.setVisibility(View.VISIBLE);
                                    text_t.setText(cityModules_list_filtter.get(i).getSearch_name() + "");

                                    LatLng sydney = new LatLng(Double.valueOf(cityModules_list_filtter.get(i).getLat()), Double.valueOf(cityModules_list_filtter.get(i).getLan()));
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
                                    text_search.setText(cityModules_list_filtter.get(i).getSearch_name());//+ "-" + cityModules_list_filtter.get(i).getCity().getName()
                                    result.setVisibility(View.GONE);

                                }
                            });
                            allcity.setAdapter(recyclerView_city_bootom_sheets);
                            result.setVisibility(View.VISIBLE);
                            closr_tecxt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    selected.setVisibility(View.GONE);
                                    text_t.setText("");

                                }
                            });

                        }


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(SelectLocationActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(SelectLocationActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(SelectLocationActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(SelectLocationActivity.this, false);

                WebService.Make_Toast_color(SelectLocationActivity.this, error, "error");


            }
        };


    }


}