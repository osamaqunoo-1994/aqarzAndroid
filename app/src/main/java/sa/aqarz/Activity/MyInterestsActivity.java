package sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.loopj.android.http.RequestParams;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Activity.profile.MyOffersActivity;
import sa.aqarz.Activity.profile.ProfileDetailsActivity;
import sa.aqarz.Adapter.RecyclerVie_member_service;
import sa.aqarz.Adapter.RecyclerVie_member_service_m;
import sa.aqarz.Adapter.RecyclerView_Course;
import sa.aqarz.Adapter.RecyclerView_city;
import sa.aqarz.Adapter.RecyclerView_experince;
import sa.aqarz.Adapter.RecyclerView_select_neb;
import sa.aqarz.DataBase.FeedReaderContract;
import sa.aqarz.DataBase.FeedReaderDbHelper;
import sa.aqarz.Dialog.BottomSheetDialogFragment_QR;
import sa.aqarz.Modules.AlLNebModules;
import sa.aqarz.Modules.AllCityList;
import sa.aqarz.Modules.AllCityModules;
import sa.aqarz.Modules.CityLocation;
import sa.aqarz.Modules.MyItem;
import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.CustomInfoWindowGoogleMapEstatMaps;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MyInterestsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static GoogleMap mMap;
    static IResult mResultCallback;
    private FusedLocationProviderClient fusedLocationClient;
    private List<AlLNebModules.neb> all_neb = new ArrayList<>();
    private final List<AlLNebModules.neb> all_nebSelected = new ArrayList<>();
    RecyclerView all_city;
    LinearLayout open_city_list;
    LinearLayout list_liner_city;

    TextView selected_text_city;

    Button search_ok_btn;

    EditText serch_edt;

    List<AllCityModules.City> dataCities = new ArrayList<>();
    List<Polygon> polygons = new ArrayList<>();

    RecyclerView allneb;

    RecyclerView_select_neb recyclerView_select_neb;

    Button send_to_server;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_interests);

        if (Hawk.contains("lang")) {


            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", "ar");

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

        }


        if (Hawk.contains("lang")) {


            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", "ar");

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

        }
        all_city = findViewById(R.id.all_city);
        back = findViewById(R.id.back);
        open_city_list = findViewById(R.id.open_city_list);
        list_liner_city = findViewById(R.id.list_liner_city);
        selected_text_city = findViewById(R.id.selected_text_city);
        search_ok_btn = findViewById(R.id.search_ok_btn);
        serch_edt = findViewById(R.id.serch_edt);
        allneb = findViewById(R.id.allneb);
        send_to_server = findViewById(R.id.send_to_server);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.contains("lang")) {


                    Locale locale = new Locale(Hawk.get("lang").toString());
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                } else {

                    Hawk.put("lang", "ar");

                    Locale locale = new Locale(Hawk.get("lang").toString());
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());

                }
                if (Hawk.contains("lang")) {


                    Locale locale = new Locale(Hawk.get("lang").toString());
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                } else {

                    Hawk.put("lang", "ar");

                    Locale locale = new Locale(Hawk.get("lang").toString());
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());

                }

                finish();
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        if (Hawk.contains("lang")) {
//            Hawk.put("lang", "ar");
//
//        } else {
//            Hawk.put("lang", "ar");
//        }
////
//        Locale locale = new Locale(Hawk.get("lang").toString());
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());
//        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(37.35, -122.2);
        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


//        WebService.loading(MyInterestsActivity.this, true);

//        clusterManager = new ClusterManager<MyItem>(MyInterestsActivity.this, mMap);


//        mMap.setOnCameraIdleListener(clusterManager);
//        mMap.setOnMarkerClickListener(clusterManager);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(MyInterestsActivity.this, LinearLayoutManager.VERTICAL, false);
        all_city.setLayoutManager(layoutManager1);
//        init_volley();
//
//        try {
//            VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
////            mVolleyService.getDataVolley("user", WebService.user + id + "");
//            mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "");
//        } catch (Exception e) {
//
//        }


//        try {
//            String getCityId = getIntent().getStringExtra("getCityId");
//
//            init_volley();
//            WebService.loading(MyInterestsActivity.this, true);
//
//            try {
//                VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
////            mVolleyService.getDataVolley("user", WebService.user + id + "");
//                mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + getCityId + "/neb");
//
//            } catch (Exception e) {
//
//            }
//        } catch (Exception e) {
//
//        }


        allneb.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView_select_neb = new RecyclerView_select_neb(MyInterestsActivity.this, all_nebSelected);
        recyclerView_select_neb.addItemClickListener(new RecyclerView_select_neb.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

//                polygons.get(position).setFillColor(Color.TRANSPARENT);
//                all_neb.get(Integer.valueOf(polygons.get(position).getTag().toString())).setInMyInterset("0");


                for (int i = 0; i < all_neb.size(); i++) {

                    if (all_neb.get(i).getDistrictId().equals(all_nebSelected.get(position).getDistrictId())) {

                        AlLNebModules.neb neb = all_neb.get(i);
                        Polygon polygon = polygons.get(i);


                        System.out.println("neb.getInMyInterset()" + neb.getInMyInterset());

                        if (neb.getInMyInterset().equals("1")) {
                            polygon.setFillColor(Color.TRANSPARENT);
                            all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("0");


                        }


                        all_nebSelected.remove(position);
                        recyclerView_select_neb.Refr();

                        break;
                    }


                }


            }
        });
        allneb.setAdapter(recyclerView_select_neb);
        open_city_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (list_liner_city.getVisibility() == View.VISIBLE) {
                    list_liner_city.setVisibility(View.GONE);
                } else {
                    list_liner_city.setVisibility(View.VISIBLE);
                }


            }
        });
        send_to_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String all_ids = "";

                if (all_nebSelected.size() != 0) {

                    for (int i = 0; i < all_nebSelected.size(); i++) {

                        if (all_ids.equals("")) {
                            all_ids = all_nebSelected.get(i).getDistrictId() + "";
                        } else {
                            all_ids = all_ids + "," + all_nebSelected.get(i).getDistrictId() + "";

                        }


                    }

                    init_volley();
                    WebService.loading(MyInterestsActivity.this, true);

                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("neb_ids", all_ids + "");

                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                        mVolleyService.postDataVolley("add_neb_interest", WebService.add_neb_interest, jsonObject);
                    } catch (Exception e) {

                    }

                }


            }
        });

        search_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!serch_edt.getText().toString().equals("")) {
                    init_volley();
                    WebService.loading(MyInterestsActivity.this, true);

                    try {
                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                        mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "?name=" + serch_edt.getText().toString());
                    } catch (Exception e) {

                    }
                } else {
                    list_liner_city.setVisibility(View.GONE);


                }

            }
        });

        serch_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    init_volley();
                    WebService.loading(MyInterestsActivity.this, true);

                    try {
                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                        mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "?name=" + serch_edt.getText().toString());
                    } catch (Exception e) {

                    }


                    return true;
                }
                return false;
            }
        });


        on_click_maps_marker();


        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                AlLNebModules.neb neb = all_neb.get(Integer.valueOf(polygon.getTag().toString()));


                if (neb.getInMyInterset().equals("1")) {
                    polygon.setFillColor(Color.TRANSPARENT);
                    all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("0");


                    for (int i = 0; i < all_nebSelected.size(); i++) {
                        if (all_nebSelected.get(i).getDistrictId().equals(neb.getDistrictId())) {
                            all_nebSelected.remove(i);
                            recyclerView_select_neb.Refr();

                        }
                    }

//                    init_volley();
////                    WebService.loading(MyInterestsActivity.this, true);
//
//                    try {
//
//                        RequestParams requestParams = new RequestParams();
//                        requestParams.put("neb_ids", all_neb.get(Integer.valueOf(polygon.getTag().toString())).getDistrictId() + "");
//                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
////            mVolleyService.getDataVolley("user", WebService.user + id + "");
//                        mVolleyService.postDataasync_with_file("remove_neb_interest", WebService.remove_neb_interest, requestParams);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                } else {
                    polygon.setFillColor(color);
                    all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("1");

                    all_nebSelected.add(neb);
                    recyclerView_select_neb.Refr();


                }


            }
        });


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //
            //                                          int[] grantResults)
            ActivityCompat.requestPermissions(MyInterestsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12);

            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
//                            mMap.clear();

//                            lat = location.getLatitude();
//                            lan = location.getLongitude();
                            System.out.println("location " + location.getLatitude() + "^%^" + location.getLongitude());
                            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
//                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                        }
                    }
                });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");

                        if (requestType.equals("remove_neb_interest")) {


                            System.out.println(")))))");


                        } else if (requestType.equals("add_neb_interest")) {


                            try {

                                BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(MyInterestsActivity.this);
                                View parentView = getLayoutInflater().inflate(R.layout.success_message, null);
                                Button close = parentView.findViewById(R.id.close);
                                TextView text = parentView.findViewById(R.id.text);
                                text.setText(getResources().getString(R.string.neb_Selected_msg));
                                close.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bottomSheerDialog.dismiss();
                                        finish();
                                    }
                                });
                                bottomSheerDialog.setContentView(parentView);

                                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
                                bottomSheerDialog.show();
                            } catch (Exception e) {

                            }


                        } else if (requestType.equals("title_global_cities")) {

//                            String data = response.getString("data");

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();
                            AllCityModules allCityModules = gson.fromJson(mJson, AllCityModules.class);
                            dataCities = allCityModules.getData();

                            mMap.clear();
//                            for (int i = 0; i < allCityModules.getData().size(); i++) {
////                                double offset = i / 60d;
////                                lat = lat + offset;
////                                lng = lng + offset;
//                                MyItem offsetItem = new MyItem(Double.valueOf(allCityModules.getData().get(i).getCenter().getCoordinates().get(1)), Double.valueOf(allCityModules.getData().get(i).getCenter().getCoordinates().get(0)), allCityModules.getData().get(i).getNameAr() + "", "" + i);
//                                clusterManager.addItem(offsetItem);
//                            }
//
//                            LatLng latLng = new LatLng(Double.valueOf(allCityModules.getData().get(0).getCenter().getCoordinates().get(1)), Double.valueOf(allCityModules.getData().get(0).getCenter().getCoordinates().get(0)));
//
//                            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(4.0f).build();
//                            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//                            mMap.moveCamera(cameraUpdate);
//
//
//                            clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
//                                @Override
//                                public boolean onClusterItemClick(MyItem myItem) {
//
//
//                                    init_volley();
//                                    WebService.loading(MyInterestsActivity.this, true);
//
//                                    try {
//                                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
////            mVolleyService.getDataVolley("user", WebService.user + id + "");
//                                        mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + myItem.getSnippet() + "/neb");
//
//                                    } catch (Exception e) {
//
//                                    }
//                                    return false;
//                                }
//                            });

//                            FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(MyInterestsActivity.this);
//
//                            SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//
//                            dbHelper.onUpgrade(db, 1, 1);
//
//
//                            for (int i = 0; i < allCityModules.getData().size(); i++) {
//                                ContentValues values = new ContentValues();
//                                values.put(FeedReaderContract.FeedEntry.COLUMN_name_ar, allCityModules.getData().get(i).getNameAr() + "");
//                                values.put(FeedReaderContract.FeedEntry.COLUMN_city_id, allCityModules.getData().get(i).getCityId() + "");
//                                values.put(FeedReaderContract.FeedEntry.COLUMN_lat, allCityModules.getData().get(i).getCenter().getCoordinates().get(1) + "");
//                                values.put(FeedReaderContract.FeedEntry.COLUMN_long, allCityModules.getData().get(i).getCenter().getCoordinates().get(0) + "");
//
//                                long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
//
//                                System.out.println("newRowId" + newRowId);
//
//                            }


                            RecyclerView_city recyclerView_city = new RecyclerView_city(MyInterestsActivity.this, allCityModules.getData());
                            recyclerView_city.addItemClickListener(new RecyclerView_city.ItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    list_liner_city.setVisibility(View.GONE);
                                    selected_text_city.setText(allCityModules.getData().get(position).getNameAr() + "");
                                    init_volley();
                                    WebService.loading(MyInterestsActivity.this, true);

                                    try {
                                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                                        mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + allCityModules.getData().get(position).getCityId() + "/neb");

                                    } catch (Exception e) {

                                    }


                                }
                            });

                            all_city.setAdapter(recyclerView_city);


//                            set_locationCity(allCityModules.getData());
                        } else if (requestType.equals("title_gloable")) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();
                            AlLNebModules alLNebModules = gson.fromJson(mJson, AlLNebModules.class);
                            all_neb = alLNebModules.getData();
                            mMap.clear();
                            for (int i = 0; i < alLNebModules.getData().size(); i++) {


                                if (i == 0) {

                                    LatLng latLng = new LatLng(Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(0).get(1).toString()), Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(0).get(0).toString()));

                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(12.0f).build();
                                    CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                                    mMap.moveCamera(cameraUpdate);

                                }


                                PolygonOptions rectOptions = new PolygonOptions();
                                rectOptions.clickable(true);
                                for (int l = 0; l < alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).size(); l++) {

                                    LatLng latLng = new LatLng(Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(l).get(1).toString()), Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(l).get(0).toString()));

                                    System.out.println("%^%^%^" + Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(l).get(1).toString()) + "dffkgflgjflg" + Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(l).get(0).toString()));


                                    rectOptions.add(latLng);

                                }

//                                rectOptions.add(new LatLng(37.35, -122.0),
//                                        new LatLng(37.45, -122.0),
//                                        new LatLng(37.45, -122.2),
//                                        new LatLng(37.35, -122.2),
//                                        new LatLng(37.35, -122.0));
// Get back the mutable Polygon


                                double[] centroid = centroid(alLNebModules.getData().get(i).getBoundaries().getCoordinates());
                                LatLng sydneya = new LatLng(centroid[0], centroid[1]);

                                if (mMap != null) {
                                    mMap.addMarker(new MarkerOptions()
                                            .position(sydneya)
                                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewText(alLNebModules.getData().get(i).getNameAr() + "")))).setTag("allneb/" + i);

                                }

                                Random rnd = new Random();
                                int color = Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


                                Polygon polygon = mMap.addPolygon(rectOptions);
                                polygon.setTag(i + "");

                                if (alLNebModules.getData().get(i).getInMyInterset().equals("1")) {
                                    polygon.setFillColor(color);
                                    all_nebSelected.add(alLNebModules.getData().get(i));
                                    recyclerView_select_neb.Refr();
                                }

                                polygon.setStrokeColor(Color.parseColor("#FE9457"));
                                polygon.setStrokeWidth(8);

                                polygons.add(polygon);
                            }


                        }
                    }
                    WebService.loading(MyInterestsActivity.this, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(MyInterestsActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(MyInterestsActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(MyInterestsActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(MyInterestsActivity.this, false);

                WebService.Make_Toast_color(MyInterestsActivity.this, error, "error");


            }
        };


    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static double[] centroid(List<List<List<Double>>> points) {
        double[] centroid = {0.0, 0.0};

        for (int i = 0; i < points.get(0).size(); i++) {
            centroid[0] += Double.valueOf(points.get(0).get(i).get(1).toString());
            centroid[1] += Double.valueOf(points.get(0).get(i).get(0).toString());
        }

        int totalPoints = points.get(0).size();
        centroid[0] = centroid[0] / totalPoints;
        centroid[1] = centroid[1] / totalPoints;

        return centroid;
    }

    public void set_locationCity(List<AllCityModules.City> locationCity) {


        if (mMap != null) {
            mMap.clear();
            for (int i = 0; i < 100; i++) {

                LatLng sydneya = new LatLng(Double.valueOf(locationCity.get(i).getCenter().getCoordinates().get(1) + ""), Double.valueOf(locationCity.get(i).getCenter().getCoordinates().get(0) + ""));

                if (mMap != null) {
                    mMap.addMarker(new MarkerOptions()
                            .position(sydneya)
                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewCity(locationCity.get(i).getNameAr() + "", i + "")))).setTag("allcity/" + i);

                }

            }
        }

    }

    private Bitmap getMarkerBitmapFromViewCity(String name, String numbers) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom_all, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
        TextView number_ = customMarkerView.findViewById(R.id.number_);
        ImageView back_location = customMarkerView.findViewById(R.id.back_location);
//        markerImageView.setImageResource(resId);
        markerImageView.setText(name);


        //offer Market Real
        //city_location
        number_.setText(numbers + "");

//
//        if (type_selected.equals("Real")) {
//
//            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home1));
//            number_.setTextColor(activity.getResources().getColor(R.color.c1));
//
//        } else if (type_selected.equals("Market")) {
//            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home2));
//            number_.setTextColor(activity.getResources().getColor(R.color.c2));
////            number_.setText(regionModules_list.get(Integer.valueOf(numbers)).getRequests() + "");
//            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_app_request() + "");
//
//        } else if (type_selected.equals("offer")) {
//            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home));
//            number_.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
//            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_app_estate() + "");
//
//
//        }


        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    private Bitmap getMarkerBitmapFromViewText(String name) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom_text, null);
        TextView text = customMarkerView.findViewById(R.id.text);


        text.setText(name);


        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //
            //                                          int[] grantResults)
//            ActivityCompat.requestPermissions(MyInterestsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12);

            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
//                            mMap.clear();

//                            lat = location.getLatitude();
//                            lan = location.getLongitude();
                            System.out.println("location " + location.getLatitude() + "^%^" + location.getLongitude());
                            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
//                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                        }
                    }
                });
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void on_click_maps_marker() {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if (marker.getTag().toString().contains("allcity")) {

                    try {

                        String[] separated = marker.getTag().toString().split("/");

                        String number = separated[1]; // this will contain " they taste good"
//                        LatLng my_location = new LatLng(Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(1)), Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(0)));

//                        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

                        init_volley();
                        WebService.loading(MyInterestsActivity.this, true);

                        try {
                            VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                            mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + number + "/neb");

                        } catch (Exception e) {

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else if (marker.getTag().toString().contains("allneb")) {
                    String[] separated = marker.getTag().toString().split("/");
                    String number = separated[1];


                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                    AlLNebModules.neb neb = all_neb.get(Integer.valueOf(number));
                    Polygon polygon = polygons.get(Integer.valueOf(number));

                    if (neb.getInMyInterset().equals("1")) {
                        polygon.setFillColor(Color.TRANSPARENT);
                        all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("0");


                        for (int i = 0; i < all_nebSelected.size(); i++) {
                            if (all_nebSelected.get(i).getDistrictId().equals(neb.getDistrictId())) {
                                all_nebSelected.remove(i);
                                recyclerView_select_neb.Refr();

                            }
                        }


                    } else {
                        polygon.setFillColor(color);
                        all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("1");

                        all_nebSelected.add(neb);
                        recyclerView_select_neb.Refr();


                    }


                    return true;


                }
                return false;
            }
        });
    }

    public class CustomClusterRenderer extends DefaultClusterRenderer<MyItem> {

        public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected int getColor(int clusterSize) {
            return Color.BLUE;// Return any color you want here. You can base it on clusterSize.
        }
    }

    @Override
    protected void onResume() {
        if (Hawk.contains("lang")) {


            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", "ar");

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

        }
        if (Hawk.contains("lang")) {


            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", "ar");

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

        }
        super.onResume();
    }
}